import java.security.MessageDigest
import java.math.BigInteger

val inputAdvent4 = """bgvyzdsv"""

fun generateMD5(s: String): String {
    val digest = MessageDigest.getInstance("MD5")
    digest.update(s.toByteArray(), 0, s.length)
    return BigInteger(1, digest.digest()).toString(16)
}

fun generateFrom(n: Int) = generateMD5("$inputAdvent4$n")

fun check5zeros(s: String) = s.length < 28

fun mainAdvent4(args: Array<String>) {
    println(
            (0..10000000).first { check5zeros(generateFrom(it)) }
    )
}