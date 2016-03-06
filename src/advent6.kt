val commands = listOf(TurnOn, TurnOff, Toggle)

fun main(args: Array<String>) {
    val s = mutableSetOf<Coordinate>()
    inputAdvent6.forEach { input ->
        commands.forEach {
            it.fromString(input).act(s)
        }
    }
    println(s.count())
}

data class Coordinate(val x: Int, val y: Int)

data class CoordinateRange(val start: Coordinate, val end: Coordinate) : Iterable<Coordinate> {
    companion object {
        fun fromString(s: String, prefix: String): CoordinateRange? {
            if (!s.startsWith(prefix)) return null

            val (start, end) = s
                    .substring(prefix.length + 1)
                    .split(" through ")
                    .map { it.split(",") }
                    .map { it.map { it.toInt() } }
                    .map { Coordinate(it[0], it[1]) }

            return CoordinateRange(start, end)
        }
    }

    override fun iterator() = CoordinateIterator(start, end)
}

class CoordinateIterator(val start: Coordinate, val end: Coordinate) : Iterator<Coordinate> {
    var current = start

    override fun hasNext() = current.x <= end.x

    override fun next(): Coordinate {
        val result = current

        current = Coordinate(current.x, current.y + 1)
        if (current.y > end.y)
            current = Coordinate(current.x + 1, start.y)

        return result
    }
}

interface Command {
    fun act(s: MutableSet<Coordinate>)
}

abstract class CommandCompanion<T> {
    abstract val prefix: String
    abstract fun factory(range: CoordinateRange?): T

    fun fromString(s: String) = factory(
            CoordinateRange.fromString(s, prefix)
    )
}

data class TurnOn(val range: CoordinateRange?) : Command {
    companion object : CommandCompanion<TurnOn>() {
        override val prefix = "turn on"
        override fun factory(range: CoordinateRange?) = TurnOn(range)
    }

    override fun act(s: MutableSet<Coordinate>) {
        range?.forEach { s.add(it) }
    }
}

data class TurnOff(val range: CoordinateRange?) : Command {
    companion object : CommandCompanion<TurnOff>() {
        override val prefix = "turn off"
        override fun factory(range: CoordinateRange?) = TurnOff(range)
    }

    override fun act(s: MutableSet<Coordinate>) {
        range?.forEach { s.remove(it) }
    }
}

data class Toggle(val range: CoordinateRange?) : Command {
    companion object : CommandCompanion<Toggle>() {
        override val prefix = "toggle"
        override fun factory(range: CoordinateRange?) = Toggle(range)
    }

    override fun act(s: MutableSet<Coordinate>) {
        range?.forEach {
            when (it) {
                in s -> s.remove(it)
                else -> s.add(it)
            }
        }
    }
}