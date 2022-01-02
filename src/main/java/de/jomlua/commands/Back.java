package de.jomlua.commands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.CoreCommand;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Back extends CoreCommand {
    public static HashMap<UUID, Location> back = new HashMap<>();
    @Override
    public String getName() {
        return "back";
    }

    @Override
    public boolean hasCompleter() {
        return false;
    }

    @Override
    public String getHelpCommand() {
        return "back";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0){
            Player player = (Player) sender;
            if (back.containsKey(player.getUniqueId())){
                player.teleport(back.get(player.getUniqueId()));
            }else{
                ChatUtils.msg(player, ChatOutput.PREFIX.getText() + "&cDu kommst nicht mehr zurück.");
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
