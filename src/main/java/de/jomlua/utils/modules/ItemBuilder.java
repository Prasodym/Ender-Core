package de.jomlua.utils.modules;

import de.jomlua.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {
    private ItemStack item;
    private ItemMeta meta;

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material, 1);
        this.meta = this.item.getItemMeta();
    }

    public ItemBuilder setName(String name) {
        this.meta.setDisplayName(ChatUtils.setHexText(name));
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        this.meta.setLore(Arrays.asList(lore));
        return this;
    }
    public ItemBuilder setLoreWiteLine(List text){
        this.meta.setLore(text);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public ItemBuilder setEnchant(Enchantment magig, int level) {
        this.meta.addEnchant(magig, level, true);
        return this;
    }



    public ItemStack build() {
        this.item.setItemMeta(this.meta);
        return this.item;
    }
}
