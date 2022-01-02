package de.jomlua.commands;

import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.CoreCommand;
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

        if (sender instanceof Player){
            ChatUtils.msg(player, "&8[Debug] &4Dieser Test ist bestanden!!");
        }else{
            sender.sendMessage("[Debug] &4Dieser Test ist bestanden!!");
        }


        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
