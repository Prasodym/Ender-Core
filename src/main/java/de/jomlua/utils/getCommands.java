package de.jomlua.utils;


import de.jomlua.commands.*;
import de.jomlua.commands.Chat.Reply;
import de.jomlua.commands.StaffCommands.*;
import de.jomlua.commands.TpaCommand.Tpa;
import de.jomlua.commands.TpaCommand.Tpaacept;
import de.jomlua.commands.TpaCommand.Tpadeny;
import de.jomlua.commands.TpaCommand.Tpahere;
import de.jomlua.core;
import de.jomlua.utils.config.ChatOutputConfig;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class getCommands {
    public static ChatOutputConfig message;

    public static void setUp(){
        LoadConfigs();
        ListCommands();
    }

    public static void LoadConfigs(){
        message = new ChatOutputConfig(new File(core.plugin.getDataFolder(), "Chat.yml"));
    }

    private static void cmd(String command, CommandExecutor executor){
        core.plugin.getCommand(command).setExecutor(executor);
    }

    public static void ListCommands(){
        cmd("m", new Reply());
        cmd("r", new Reply());
        cmd("rename", new UnNameItem());
        cmd("head", new Head());
        cmd("speed", new Speed());
        cmd("heal", new heal());
        cmd("home", new home());
        cmd("sethome", new SetHome());
        cmd("delhome", new home());
        cmd("homes", new home());
        cmd("setspawn", new SetSpawn());
        cmd("spawn", new Spawn());
        cmd("tp", new Tp());
        cmd("tphere",new Tp());
        cmd("setz", new SetAxisBlocks());
        cmd("tpos", new Tp());
        cmd("discord", new discord());
        cmd("warp", new Warp());
        cmd("setwarp", new Warp());
        cmd("delwarp", new Warp());
        cmd("warps", new Warp());
        cmd("randomtp", new randomTeleport());
        cmd("worldborder", new randomTeleport());
        cmd("gethome", new GetHomes());
        cmd("atphome", new GetHomes());
        cmd("worldmanager", new WorldManager(core.getPlugin()));
        cmd("load", new ReloadServer());
        cmd("invsee", new Invsee());
        cmd("fly", new Fly());
        cmd("day", new SetTime());
        cmd("nigth", new SetTime());
        cmd("gm", new Gamemode());
        cmd("tpa", new Tpa());
        cmd("tpahere", new Tpahere());
        cmd("tpacept", new Tpaacept());
        cmd("tpadeny", new Tpadeny());
        cmd("createkit", new Kits());
        cmd("kit", new Kits());
        cmd("kits", new Kits());
        CoreCommand.RegisterCommands(core.getPlugin(), new test(), new rules(), new Back(), new Plugins(), new Vanish());

        //set Tabcompletion
        core.plugin.getCommand("warp").setTabCompleter(new Warp());
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
