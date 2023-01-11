import okio.FileSystem
import okio.Path.Companion.toPath

var fullyContainedRanges = 0

fun main(args: Array<String>) {
    val path = args[0].toPath()

    FileSystem.SYSTEM.read(path) {
        while (true) {
            val line = readUtf8Line() ?: break
            if (line.isEmpty()) break

            val ranges = parseLine(line)
            val intersection = ranges.first.intersect(ranges.second)

            if (intersection.isEmpty()) continue
            if (intersection == ranges.first.toSet() || intersection == ranges.second.toSet()) {
                ++fullyContainedRanges
            }
        }
        println(fullyContainedRanges)
    }
}

fun parseLine(line: String): Pair<IntRange, IntRange> {
    val rangeStrings = line.split(",")
    return Pair(parseRange(rangeStrings[0]), parseRange(rangeStrings[1]))
}

fun parseRange(rangeString: String): IntRange {
    val rangeLimits = rangeString.split("-")
    val rangeStart = rangeLimits[0].toInt()
    val rangeEnd = rangeLimits[1].toInt()
    return rangeStart..rangeEnd
}
