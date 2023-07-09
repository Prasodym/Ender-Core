package de.jomlua.utils.modules;

import de.jomlua.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ItemModule {
    public ItemModule() {
    }

    /**
     * Ändert den Namen des Items
     * @param player
     * @param itemname
     */
    public static void RenameItemInHand(Player player, String itemname){

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (itemStack == null || itemStack.getType().equals(Material.AIR)){
            ChatUtils.SendRgbMsg(player, "&cDu hast kein Item in deiner Hand.");
            return;
        }

        ItemMeta im = itemStack.getItemMeta();
        im.setDisplayName(ChatUtils.setHexText(itemname));
        itemStack.setItemMeta(im);
        player.getInventory().setItem(EquipmentSlot.HAND,itemStack);
    }

    public static void setLore(Player player, String lore, int line){
        List<String> list = new ArrayList<String>();
        list.add(line, ChatUtils.setHexText(lore));
        Material material = player.getInventory().getItemInMainHand().getType();
        String materialName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        int slot = player.getInventory().getHeldItemSlot();
        player.getInventory().setItem(slot, new ItemBuilder(material).setLoreWiteLine(list).setName(materialName).build());
        ChatUtils.msg(player, "true");
    }
    public static void setName(Player player, String name){
        Material material = player.getInventory().getItemInMainHand().getType();
        String lore = player.getInventory().getItemInMainHand().getItemMeta().getLore().get(0);
        int slot = player.getInventory().getHeldItemSlot();

        if (lore == null){
            player.getInventory().setItem(slot, new ItemBuilder(material).setName(name).build());
            ChatUtils.msg(player, "true");
        }else{
            player.getInventory().setItem(slot, new ItemBuilder(material).setLore(lore).setName(name).build());
            ChatUtils.msg(player, "true");
        }

    }
    public static void lore(Player p, String[] args) {
        if (p.getItemInHand().getType() != Material.AIR && p.getItemInHand().getType() != null) {
                ItemStack item = p.getItemInHand();
                ItemMeta meta = p.getItemInHand().getItemMeta();

                String lorestr = String.join(" ", args);
                String[] lorestrlist = lorestr.split("/n");
                List<String> lorelist = new ArrayList();

                for(int i = 0; i < lorestrlist.length; ++i) {
                    lorelist.add(ChatUtils.setHexText(lorestrlist[i]));
                }
                meta.setLore(lorelist);
                item.setItemMeta(meta);

            } else {
                p.sendMessage(ChatColor.RED + "Bitte nehme ein Item in der Hand!");
            }
    }

    public static ItemStack getHead(String accound){
        //boolean isNewVersion = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
        //Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEADS" : "SKULL_ITEM");
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        PlayerProfile profile = Bukkit.createPlayerProfile(accound);

        //if (!isNewVersion)item.setDurability((short) 3);

        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwnerProfile(profile);

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getThor(){
        ItemStack item = new ItemStack(Material.IRON_AXE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§4Thor´s Axt");
        meta.setLocalizedName("thor1");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getGM(){
        ItemStack item = new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE);

        List<String> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();

        lore.add("§eAchtung!");
        lore.add("§3Dieses Item sind nur dür das Adminteam");
        lore.add("§3gedacht. Solltes du eins finden gebe dies");
        lore.add("§3bitte an das Team ab.");
        lore.add("§7Bei nicht abgabe wird es im System rot ");
        lore.add("§7angezeigt so das wir drauf kommen wer die gerade benutzt.");

        meta.setDisplayName("§4§lGame§c§lChanger");
        meta.setLore(lore);
        meta.setLocalizedName("gm147");
        item.setItemMeta(meta);
        return item;
    }
}
