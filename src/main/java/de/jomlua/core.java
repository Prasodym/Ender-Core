package de.jomlua;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.config.CoreConfig;
import de.jomlua.utils.enabledPlugin;
import de.jomlua.utils.getCommands;
import de.jomlua.utils.getListener;
import de.jomlua.utils.modules.TablistModule;
import de.jomlua.utils.modules.VanishManager;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;
import java.util.List;
import java.util.logging.Level;

import static de.jomlua.utils.modules.BotBlocker.blockedIPs;
import static de.jomlua.utils.modules.BotBlocker.loadBlockedIPs;


public final class core extends JavaPlugin {
    public static File file = new File("plugins/Ender-Core", "worlds.yml");
    public static YamlConfiguration worldlist =YamlConfiguration.loadConfiguration(file);

    public static core plugin;
    private VanishManager vManager;
    public static Chat chat;
    public static boolean vaultEnabled = true;
    public static List<String> MAPS = worldlist.getStringList("Worlds");
    private TablistModule tablistmodule;
    public static core instance;

    @Override
    public void onEnable() {
        this.getLogger().log(Level.INFO, ChatColor.YELLOW + ChatOutput.LOADING_PLUGIN.getText());

        blockedIPs = loadBlockedIPs();
        loadingConfigs();

        plugin = this;
        instance = this;
        this.vManager = new VanishManager(this);

        getCommands.setUp();
        getListener.setUp();

        for (String map : MAPS) {
            WorldCreator w = WorldCreator.name(map);
            Bukkit.createWorld(w);
            Bukkit.getWorlds().add(Bukkit.getWorld(map));
        }
        CoreConfig.LoadConfig("rules.yml");
        CoreConfig.LoadConfig("kit.yml");
        tablistmodule = new TablistModule();

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Color.fromRGB(9,73,121).toString() + "Bis Bald");

    }

    public static core getPlugin(){
        return plugin;
    }

    public VanishManager getvManager(){
        return vManager;
    }

    private boolean setUpChat(){
        RegisteredServiceProvider<Chat> registeredServiceProvider = getServer().getServicesManager().getRegistration(Chat.class);
        assert registeredServiceProvider != null;
        chat = registeredServiceProvider.getProvider();
        return chat !=null;
    }

    private void loadingConfigs(){
        enabledPlugin.StartLogo();
        FileConfiguration config = this.getConfig();
        config.options().copyDefaults(true);
        config.options().copyHeader(true);
        this.saveConfig();

        if (!setUpChat()){
            this.getLogger().log(Level.WARNING, "The Vault plugin could not be found, intall it to add also the prefixes from the permission handler");
            vaultEnabled = false;
        }else{
            Bukkit.getConsoleSender().sendMessage("Vault konnte erfolgreich geladen werden.");
        }
    }


    public FileConfiguration CoreConfig(){
        File file = new File("plugins/Ender-Core", "config.yml");
        return YamlConfiguration.loadConfiguration(file);
    }
    public static core getInstance() {
        return instance;
    }


    public TablistModule getTablistManager() {
        return tablistmodule;
    }
}
