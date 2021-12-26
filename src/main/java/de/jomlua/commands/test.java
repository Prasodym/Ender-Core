package de.jomlua.commands;

import de.jomlua.utils.CoreCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
