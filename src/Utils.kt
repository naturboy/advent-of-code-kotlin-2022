import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInputLineByLine(name: String) = File("src", "$name.txt")
    .readLines()

fun readInputWithDelimiter(name: String, delimiter: String) = readInputAsString(name).split(delimiter)

fun readInputAsString(name: String) = File("src", "$name.txt").readText()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
