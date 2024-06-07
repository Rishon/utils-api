package systems.rishon.selautils.paper.color

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