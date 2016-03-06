import org.junit.Assert
import org.junit.Test

class TestAdvent6 {
    @Test fun testTurnOnFromString() {
        Assert.assertEquals(
                TurnOn(CoordinateRange(Coordinate(141, 242), Coordinate(932, 871))),
                TurnOn.fromString("turn on 141,242 through 932,871")
        )

        Assert.assertEquals(
                TurnOn(null),
                TurnOn.fromString("turn off 141,242 through 932,871")
        )
    }

    @Test fun testTurnOffFromString() {
        Assert.assertEquals(
                TurnOff(CoordinateRange(Coordinate(141, 242), Coordinate(932, 871))),
                TurnOff.fromString("turn off 141,242 through 932,871")
        )

        Assert.assertEquals(
                TurnOff(null),
                TurnOff.fromString("toggle 141,242 through 932,871")
        )
    }

    @Test fun testToggleFromString() {
        Assert.assertEquals(
                Toggle(CoordinateRange(Coordinate(141, 242), Coordinate(932, 871))),
                Toggle.fromString("toggle 141,242 through 932,871")
        )

        Assert.assertEquals(
                Toggle(null),
                Toggle.fromString("turn on 141,242 through 932,871")
        )
    }
}