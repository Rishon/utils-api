package systems.rishon.selautils.formatting

object FormatUtil {

    @JvmStatic
    fun formatNumber(number: Number): String {
        return String.format("%,d", number)
    }

    @JvmStatic
    fun formatEnum(e: Enum<*>): String {
        return e.name.split("_").joinToString(" ") { word ->
            word.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }

    @JvmStatic
    fun formatString(string: String): String {
        return string.split("_").joinToString(" ") { word ->
            word.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }
}