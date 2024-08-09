package systems.rishon.api.paper.item

import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.SkullMeta

class ItemBuilder(private val material: Material) {
    private var item: ItemStack = ItemStack(material)

    fun build(): ItemStack {
        return this.item
    }

    fun from(itemStack: ItemStack): ItemBuilder {
        this.item = itemStack
        return this
    }

    fun setDisplayName(displayName: Component): ItemBuilder {
        val meta = this.item.itemMeta ?: return this
        meta.displayName(displayName)
        this.item.itemMeta = meta
        return this
    }

    fun setNBTItemString(key: String, value: String): ItemBuilder {
        val nbtItem = NBTItem(this.item)
        nbtItem.setString(key, value)
        this.item = nbtItem.item
        return this
    }

    fun setNBTItemInt(key: String, value: Int): ItemBuilder {
        val nbtItem = NBTItem(this.item)
        nbtItem.setInteger(key, value)
        this.item = nbtItem.item
        return this
    }

    fun setNBTItemDouble(key: String, value: Double): ItemBuilder {
        val nbtItem = NBTItem(this.item)
        nbtItem.setDouble(key, value)
        this.item = nbtItem.item
        return this
    }

    fun setNBTItemBoolean(key: String, value: Boolean): ItemBuilder {
        val nbtItem = NBTItem(this.item)
        nbtItem.setBoolean(key, value)
        this.item = nbtItem.item
        return this
    }

    fun setNBTItemLong(key: String, value: Long): ItemBuilder {
        val nbtItem = NBTItem(this.item)
        nbtItem.setLong(key, value)
        this.item = nbtItem.item
        return this
    }

    fun setNBTItemShort(key: String, value: Short): ItemBuilder {
        val nbtItem = NBTItem(this.item)
        nbtItem.setShort(key, value)
        this.item = nbtItem.item
        return this
    }

    fun setNBTItemByte(key: String, value: Byte): ItemBuilder {
        val nbtItem = NBTItem(this.item)
        nbtItem.setByte(key, value)
        this.item = nbtItem.item
        return this
    }

    fun setNBTItemFloat(key: String, value: Float): ItemBuilder {
        val nbtItem = NBTItem(this.item)
        nbtItem.setFloat(key, value)
        this.item = nbtItem.item
        return this
    }

    fun setNBTItemIntArray(key: String, value: IntArray): ItemBuilder {
        val nbtItem = NBTItem(this.item)
        nbtItem.setIntArray(key, value)
        this.item = nbtItem.item
        return this
    }

    fun setNBTItemLongArray(key: String, value: LongArray): ItemBuilder {
        val nbtItem = NBTItem(this.item)
        nbtItem.setLongArray(key, value)
        this.item = nbtItem.item
        return this
    }

    fun setNBTItemByteArray(key: String, value: ByteArray): ItemBuilder {
        val nbtItem = NBTItem(this.item)
        nbtItem.setByteArray(key, value)
        this.item = nbtItem.item
        return this
    }

    fun setLore(lore: List<Component>): ItemBuilder {
        val meta = this.item.itemMeta ?: return this
        meta.lore(lore)
        this.item.itemMeta = meta
        return this
    }

    fun setAmount(amount: Int): ItemBuilder {
        this.item.amount = amount
        return this
    }

    fun setUnbreakable(unbreakable: Boolean): ItemBuilder {
        val meta = this.item.itemMeta ?: return this
        meta.isUnbreakable = unbreakable
        this.item.itemMeta = meta
        return this
    }

    fun setPlayerHead(name: String): ItemBuilder {
        val meta = this.item.itemMeta ?: return this
        val skullMeta = meta as SkullMeta
        val offlinePlayer = Bukkit.getOfflinePlayer(name)
        skullMeta.owningPlayer = offlinePlayer
        this.item.itemMeta = meta
        return this
    }

    fun addLore(line: Component): ItemBuilder {
        val meta = this.item.itemMeta ?: return this
        var lore = if (meta.hasLore()) meta.lore() else null
        if (lore == null) lore = mutableListOf()
        lore.add(line)
        meta.lore(lore)
        this.item.itemMeta = meta
        return this
    }

    fun setModelData(modelData: Int): ItemBuilder {
        val meta = this.item.itemMeta ?: return this
        meta.setCustomModelData(modelData)
        this.item.itemMeta = meta
        return this
    }

    fun setLeatherColor(r: Int, g: Int, b: Int): ItemBuilder {
        val leatherMeta = this.item.itemMeta as? LeatherArmorMeta ?: return this
        leatherMeta.setColor(Color.fromRGB(r, g, b))
        this.item.itemMeta = leatherMeta
        return this
    }

    fun hideFlags(): ItemBuilder {
        val meta = this.item.itemMeta ?: return this
        meta.addItemFlags(*ItemFlag.entries.toTypedArray())
        this.item.itemMeta = meta
        return this
    }
}