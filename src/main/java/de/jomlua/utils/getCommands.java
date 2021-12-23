package de.jomlua.utils;

import com.sun.istack.internal.NotNull;
import de.jomlua.commands.*;
import de.jomlua.core;
import de.jomlua.utils.config.ChatOutputConfig;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class getCommands {
    public static ChatOutputConfig message;

    public static void setUp(){
        LoadConfigs();
        ListCommands();
    }

    public static void LoadConfigs(){
        message = new ChatOutputConfig(new File("plugins/jomlua-core", "Chat.yml"));
    }

    private static void cmd(String command, CommandExecutor executor){
        core.plugin.getCommand(command).setExecutor(executor);
    }

    public static void ListCommands(){
        cmd("heal", new heal());
        cmd("home", new home());
        cmd("sethome", new SetHome());
        cmd("homes", new home());
        cmd("setspawn", new SetSpawn());
        cmd("spawn", new Spawn());
        cmd("tp", new Tp());
        cmd("tpos", new Tp());

    }

    /**
     *
     * @param permissions
     * @param args
     * @param sender
     * @return Commnand helper
     */
    @NotNull
    public static final boolean useCommand(String permissions, Integer args, CommandSender sender){
        Player player = (Player) sender;
        if (player.hasPermission(permissions)){
            return true;
        }
        return false;
    }
}
