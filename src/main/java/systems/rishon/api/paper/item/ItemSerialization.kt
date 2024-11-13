package systems.rishon.api.paper.item

import net.minecraft.world.item.ItemStack
import org.bukkit.util.io.BukkitObjectInputStream
import org.bukkit.util.io.BukkitObjectOutputStream
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.Base64;

object ItemSerialization {

    /**
     * Serialize an ItemStack to a base64 string
     * @param itemStack The item stack to serialize
     */
    @JvmStatic
    fun serializeItemStack(itemStack: ItemStack): String? {
        return try {
            val outputStream = ByteArrayOutputStream()
            val dataOutput = BukkitObjectOutputStream(outputStream)
            dataOutput.writeObject(itemStack)
            Base64.getEncoder().encodeToString(outputStream.toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Deserialize an ItemStack from a base64 string
     * @param data The serialized item stack
     */
    @JvmStatic
    fun deserializeItemStack(data: String): ItemStack? {
        return try {
            val bytes = Base64.getDecoder().decode(data)
            val inputStream = ByteArrayInputStream(bytes)
            val dataInput = BukkitObjectInputStream(inputStream)
            dataInput.readObject() as ItemStack
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            null
        }
    }
}