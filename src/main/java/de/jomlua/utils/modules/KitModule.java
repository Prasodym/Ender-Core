package de.jomlua.utils.modules;

import de.jomlua.core;
import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class KitModule {
    public KitModule() {
    }

    public static void getInv(Player player, String name) throws IOException, InvalidConfigurationException {
        File file = new File(core.plugin.getDataFolder(), "kits.yml");

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);


        try {
            config.load(file);
        } catch (FileNotFoundException e){

            new File(core.plugin.getDataFolder(),"kits.yml");
        }

        config.set(name +".inventory", Base64.toBase64(player.getInventory()));
        config.set(name + ".delay","0");

        config.save(file);
        player.getInventory().clear();
        player.sendMessage(ChatOutput.PREFIX.getText() + "§fDas Kit §c" + name + " §fwurde gespeichert.");

    }

    public static ItemStack[] setKit(Player player, String kit) throws IOException {
        File file = new File(core.plugin.getDataFolder(), "kits.yml");


        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        if (config.isSet(kit)) {

            String inv = config.getString(kit + ".inventory");
            //inv.replace("\\r\\n|\\n","");

            Base64.fromBase64(inv);


            player.sendMessage("§fDu hast das Kit §c" + kit + " §fbekommen.");

        }else{
            player.sendMessage(ChatOutput.PREFIX.getText() + "§cDieses Kit gibt es nicht.");
        }
        return new ItemStack[0];
    }



    public static void SaveKit(final String kitname, final long delay, final ArrayList<ItemStack> content)throws IOException{
        final File file = new File(core.plugin.getDataFolder(), "kit.yml");
        final FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set("kits." + kitname + ".delay", delay);
        config.set("kits." + kitname + ".items", content);

        config.save(file);

    }
    private FileConfiguration KitFile(){
        final File file = new File(core.plugin.getDataFolder(), "kit.yml");
        final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config;
    }

    public static boolean KitExist(String kitname){
        final File file = new File(core.plugin.getDataFolder(), "kit.yml");
        final FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        if (config.isSet("kits." + kitname)){
            return true;
        }else {
            return false;
        }
    }
    public static void getKit(String kitname, Player player){
        final File file = new File(core.plugin.getDataFolder(), "kit.yml");
        final FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        ItemStack[] Inv = ((List<ItemStack>) config.get("kits." + kitname.toLowerCase() + ".items")).toArray(new ItemStack[0]);
        player.getInventory().addItem(Inv);
    }

    public static boolean getAlradyKitUse(Player player, String kitname){
        File file = new File(core.plugin.getDataFolder()+ "/users",  player.getUniqueId() + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        if (config.getBoolean("kits."+ kitname)){
            return false;
        }else{
            return true;
        }
    }

    public static void setAlradyKitUse(Player player, String kitname) throws IOException {
        File file = new File(core.plugin.getDataFolder()+ "/users", player.getUniqueId() + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("kits." + kitname , true);
        config.save(file);
    }

    public static void ListKit(Player player){
        final File file = new File(core.plugin.getDataFolder(), "kit.yml");
        final FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        Iterator var10 = config.getConfigurationSection("kits.").getKeys(true).iterator();

        if(file.exists()){

        }
        while (var10.hasNext()) {
            String list = (String) var10.next();
            if (!list.contains(".")) {
                TextComponent warps;
                TextComponent delwarp;
                TextComponent worldliste;
                TextComponent permission;

                warps =  ChatUtils.TcCommand("§7[§2Nehmen§7]","/kit " + list,"§4Klicke um das kit zu Beanspruchen.");


                if (!(getAlradyKitUse(player,list))){
                    delwarp = ChatUtils.TcHover(" §7(§cBereits benutzt.§7)","§cDu hast bereits das Kit benutzt.");
                }else{
                    delwarp = ChatUtils.TcHover(" §7(§aOffen§7)","§aDu kannst das Kit holen.");
                }

                if (!(player.hasPermission("jomlua.kit." + list))){
                    permission = ChatUtils.TcHover(" §4§lX", "§cDiesen kit kannst du nicht nutzen da du keine berechtigung hast.");
                }else{
                    permission = ChatUtils.TcHover(" §a§l✔", "§aBerechtigung verfügbar.");
                }

                worldliste = ChatUtils.TcText("§a" + list.toLowerCase(Locale.ROOT) + " §7>> ");

                worldliste.addExtra(warps);
                warps.addExtra(delwarp);
                delwarp.addExtra(permission);

                player.spigot().sendMessage(worldliste);


            }
        }
    }

    public static Set<String> getAmoutKit(){
        final File file = new File(core.plugin.getDataFolder(), "kit.yml");

        if (file.exists()){
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            return config.getConfigurationSection("kits.").getKeys(false);
        }else{
            return new HashSet<String>();
        }
    }
}
