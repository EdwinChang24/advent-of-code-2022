import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads the input file for the given [day]. The file must be named `DayXX.txt` under the package `dayXX`.
 *
 * @return A list containing each line in the file.
 */
fun readInput(day: Int) =
    File(File("src", "day${"$day".padStart(2, '0')}"), "Day${"$day".padStart(2, '0')}.txt").readLines()

/**
 * Gets the MD5 hash of this string.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16).padStart(32, '0')
