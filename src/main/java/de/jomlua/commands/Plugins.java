package de.jomlua.commands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.CoreCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Plugins extends CoreCommand {
    @Override
    public String getName() {
        return "plugins";
    }

    @Override
    public boolean hasCompleter() {
        return false;
    }

    @Override
    public String getHelpCommand() {
        return "plugins";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("plugins")){
            if (args.length == 0){
                sender.sendMessage(ChatOutput.PREFIX.getText() + "Das bleibt geheim.");
            }else{
                sender.sendMessage("/plugins");
            }
        } else if (command.getName().equalsIgnoreCase("pl")){
            if (args.length == 0){
                sender.sendMessage(ChatOutput.PREFIX.getText() + "Das bleibt geheim.");
            }else{
                sender.sendMessage("/plugins");
            }
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
