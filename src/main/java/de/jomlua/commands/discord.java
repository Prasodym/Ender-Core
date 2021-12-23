package de.jomlua.commands;

import de.jomlua.utils.ChatOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class discord implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        String prefix = ChatOutput.PREFIX.getText();
        if (command.getName().equalsIgnoreCase("discord")){
            if (args.length == 0){
                // config getter
            }
        }
        return true;
    }
}
