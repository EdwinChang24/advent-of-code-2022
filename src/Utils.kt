import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input `.txt` file. The file will be read from `src/name/name.txt`.
 *
 * @param name The name of the file, with the `.txt` extension removed.
 */
fun readInput(name: String) = File(File("src", name), "$name.txt").readLines()

/**
 * Reads lines from the given input `.txt` file.
 *
 * @param dir The directory, under `src`, to get the file from.
 * @param name The name of the file, with the `.txt` extension removed.
 */
fun readInput(dir: String, name: String) = File(File("src", dir), "$name.txt").readLines()

/**
 * Gets the MD5 hash of this string.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
