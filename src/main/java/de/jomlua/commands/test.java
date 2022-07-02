package de.jomlua.commands;

import de.jomlua.core;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.CoreCommand;
import de.jomlua.utils.Material;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class test extends CoreCommand {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public boolean hasCompleter() {
        return false;
    }

    @Override
    public String getHelpCommand() {
        return "test";
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player  = (Player) sender;
        String Oplayer = null;


        for (Player players : Bukkit.getServer().getOnlinePlayers()){
            Oplayer = players.getDisplayName();
        }
        String format = core.chat.getPlayerPrefix(Bukkit.getPlayer(Oplayer)) + Oplayer;

        for (int msg = 0; msg <  Bukkit.getServer().getOnlinePlayers().size(); msg++){

            ChatUtils.msg(player, format + ", ");

        }

        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
