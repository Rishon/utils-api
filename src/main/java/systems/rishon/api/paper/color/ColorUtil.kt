package systems.rishon.api.paper.color

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import net.md_5.bungee.api.ChatColor

object ColorUtil {

    private val mm: MiniMessage = MiniMessage.builder().build()

    // MiniMessage translation
    @JvmStatic
    fun translate(string: String): Component {
        return mm.deserialize(string).decoration(TextDecoration.ITALIC, false)
    }

    // MiniMessage translation with status colors
    @JvmStatic
    fun translate(string: String, color: Colors): Component {
        return mm.deserialize(color.color + string).decoration(TextDecoration.ITALIC, false)
    }

    @JvmStatic
    fun wrapMini(msg: String): Component {
        val pattern = "#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})"
        val r = Regex(pattern)
        val sb = StringBuffer()
        val m = r.findAll(msg)
        var lastIndex = 0

        for (match in m) {
            sb.append(msg, lastIndex, match.range.first)
            sb.append("<${match.value}>")
            lastIndex = match.range.last + 1
        }

        sb.append(msg.substring(lastIndex))
        return mm.deserialize(sb.toString())
    }


    // Legacy Hex (ex. #FFFFFF)
    @JvmStatic
    @Deprecated("Use translate instead")
    fun legacyHex(message: String): String {
        var modifiedMessage = message
        val pattern = Regex("#[a-fA-F0-9]{6}")
        var matcher = pattern.find(modifiedMessage)
        while (matcher != null) {
            val hexCode = modifiedMessage.substring(matcher.range)
            val replaceSharp = hexCode.replace('#', 'x')

            val builder = StringBuilder()
            for (c in replaceSharp) {
                builder.append("&").append(c)
            }

            modifiedMessage = modifiedMessage.replace(hexCode, builder.toString())
            matcher = pattern.find(modifiedMessage)
        }
        return ChatColor.translateAlternateColorCodes('&', modifiedMessage) // md_5 deprecated API
    }

}

enum class Colors(val color: String) {
    SUCCESS("<#9FDEB1>"),
    INFO("<#FFD964>"),
    DENIED("<#EA3526>"),
    ERROR("<#9A2433>"),
    NEGATIVE("<#D13226>"),
}