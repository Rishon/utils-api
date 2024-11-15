package systems.rishon.api.paper.inventory

import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.util.io.BukkitObjectInputStream
import org.bukkit.util.io.BukkitObjectOutputStream
import org.spongepowered.configurate.yaml.internal.snakeyaml.external.biz.base64Coder.Base64Coder
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.EOFException

object InventorySerialization {

    /**
     * Serialize an inventory to a base64 string
     * @param inventory The inventory to serialize
     * @return The serialized inventory
     */
    @JvmStatic
    fun toBase64(inventory: Inventory): String {
        return try {
            val outputStream = ByteArrayOutputStream()
            val dataOutput = BukkitObjectOutputStream(outputStream)
            dataOutput.writeInt(inventory.size)
            for (i in 0 until inventory.size) {
                val item = inventory.getItem(i)
                dataOutput.writeObject(item)
            }
            dataOutput.close()
            Base64Coder.encodeLines(outputStream.toByteArray())
        } catch (exception: Exception) {
            throw IllegalStateException("Unable to save item stacks.", exception)
        }
    }

    /**
     * Deserialize an inventory from a base64 string
     * @param data The serialized inventory
     * @param player The player to deserialize the inventory to
     * @return The deserialized inventory
     */
    @JvmStatic
    fun fromBase64(data: String, player: Player): Inventory {
        return try {
            val inputStream = ByteArrayInputStream(Base64Coder.decodeLines(data))
            val dataInput = BukkitObjectInputStream(inputStream)
            val size = dataInput.readInt()
            val inventory = player.inventory

            for (i in 0 until size) {
                try {
                    val anyObject: Any = dataInput.readObject() ?: continue
                    val item = anyObject as ItemStack
                    inventory.setItem(i, item)
                } catch (ignored: EOFException) {
                }
            }
            dataInput.close()
            inventory
        } catch (exception: Exception) {
            throw IllegalStateException("Unable to load item stacks.", exception)
        }
    }
}