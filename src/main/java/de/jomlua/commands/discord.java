package de.jomlua.commands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.config.CoreConfig;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class discord implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        String prefix = ChatOutput.PREFIX.getText();
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("discord")){
            if (args.length == 0){
                ChatUtils.msg(player, ChatOutput.PREFIX.getText() + ChatOutput.COMMAND_DISCORD_HEADER.getText());
                ChatUtils.msg(player,"");

                TextComponent tc = ChatUtils.TcLink(CoreConfig.getDiscord(), CoreConfig.getDiscord(),"Klicke um die gruppe bei zu treten.");
                player.spigot().sendMessage(tc);

            }
        }
        return true;
    }
}
