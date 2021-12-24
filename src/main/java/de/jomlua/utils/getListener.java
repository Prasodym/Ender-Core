package de.jomlua.utils;

import de.jomlua.core;
import de.jomlua.listener.ChatListener;
import de.jomlua.listener.DeathListener;
import de.jomlua.listener.JoinListener;
import de.jomlua.listener.QuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class getListener {

    public static  void setUp(){
        loadListeners();
    }

    private static void loadListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        core r = core.plugin;
        pm.registerEvents(new ChatListener(), r);
        pm.registerEvents(new DeathListener(), r);
        pm.registerEvents(new QuitListener(), r);
        pm.registerEvents(new JoinListener(), r);
        //pm.registerEvents(new PermissionPicker(),r);
    }
}
