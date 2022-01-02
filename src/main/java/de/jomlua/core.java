package de.jomlua;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.config.CoreConfig;
import de.jomlua.utils.enabledPlugin;
import de.jomlua.utils.getCommands;
import de.jomlua.utils.getListener;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;



public final class core extends JavaPlugin {
    public static File file = new File("plugins/jomlua-core", "worlds.yml");
    public static YamlConfiguration worldlist =YamlConfiguration.loadConfiguration(file);

    public static core plugin;
    public static Chat chat;
    public static boolean vaultEnabled = true;
    public static List<String> MAPS = worldlist.getStringList("Worlds");


    @Override
    public void onEnable() {
        this.getLogger().log(Level.SEVERE, ChatOutput.LOADING_PLUGIN.getText());
        System.out.println(ChatOutput.LOADING_PLUGIN.getText());

        loadingConfigs();

        plugin = this;

        getCommands.setUp();
        getListener.setUp();

        for (String map : MAPS) {
            WorldCreator w = WorldCreator.name(map);
            Bukkit.createWorld(w);
            Bukkit.getWorlds().add(Bukkit.getWorld(map));
        }
        CoreConfig.LoadConfig("rules.yml");
        CoreConfig.LoadConfig("kit.yml");

    }







    @Override
    public void onDisable() {
        System.out.println("§eBis bald!");
    }

    public static core getPlugin(){
        return plugin;
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
        }
    }

    private boolean setUpChat(){
        RegisteredServiceProvider<Chat> registeredServiceProvider = getServer().getServicesManager().getRegistration(Chat.class);
        chat = registeredServiceProvider.getProvider();
        return chat !=null;
    }


    public FileConfiguration CoreConfig(){
        File file = new File("plugins/Core", "config.yml");
        return YamlConfiguration.loadConfiguration(file);
    }


}
