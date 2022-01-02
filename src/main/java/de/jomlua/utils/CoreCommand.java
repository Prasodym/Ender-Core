package de.jomlua.utils;

import de.jomlua.core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class CoreCommand implements CommandExecutor, TabCompleter {

    public static  void RegisterCommands(core core, CoreCommand @NotNull ... cmd){
        for (CoreCommand command : cmd){
            core.getCommand(command.getName()).setExecutor(command);
            if (command.hasCompleter()) core.getCommand(command.getName()).setTabCompleter(command);
        }
    }

    public abstract String getName();
    public abstract boolean hasCompleter();
    public abstract String getHelpCommand();
    //public abstract Player User();

    static Player User(CommandSender sender){
        Player player = (Player) sender;
        return player;
    }



    public boolean ValideUser(CommandSender sender, String permissions, String[] args, String usage){


        if (!sender.hasPermission(permissions)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText()));
            return false;
        }
        else if (args.length == 0){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatOutput.PREFIX.getText() + ChatOutput.COMMAND_HELP.getText()));
            sender.sendMessage("");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &e/&a" + getHelpCommand()));
            return true;
        }
        else if (Bukkit.getPlayerExact(args[0]) == null){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatOutput.PREFIX.getText()) + "&cDer Spieler $f" + args[0] + " &cist gerade nicht online.");
            return false;
        }
        return true;
    }
}
