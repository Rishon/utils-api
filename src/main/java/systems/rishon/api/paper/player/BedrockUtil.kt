package systems.rishon.api.paper.player

import org.bukkit.entity.Player
import org.geysermc.floodgate.api.FloodgateApi
import java.util.UUID

object BedrockUtil {

    /**
     * Check if an uuid is a Floodgate player
     *
     * @param uuid The UUID of the player
     * @return True if the player is a Floodgate player
     */
    @JvmStatic
    fun isFloodgatePlayer(uuid: UUID): Boolean {
        val instance = FloodgateApi.getInstance();
        return instance.isFloodgatePlayer(uuid)
    }

    /**
     * Check if a player is a Floodgate player
     *
     * @param player The player
     * @return True if the player is a Floodgate player
     */
    @JvmStatic
    fun isFloodgatePlayer(player: Player): Boolean {
        return isFloodgatePlayer(player.uniqueId)
    }

}