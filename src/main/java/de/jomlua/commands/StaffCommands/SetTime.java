package de.jomlua.commands.StaffCommands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.PrivatPermissions;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class SetTime implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        World world = Bukkit.getWorld(player.getWorld().getName());
        if (!(player.hasPermission(PrivatPermissions.SETTIME.getText()))){
            ChatUtils.NoPerm(player);
            return true;
        }

        if (!(sender instanceof Player)){
            ChatUtils.setHexText(ChatOutput.PREFIXC.getText() + ChatOutput.NO_PLAYER.getText());
            return true;
        }

        if (command.getName().equalsIgnoreCase("day")){
            if (args.length == 0){
                world.setTime(1000);
                HashMap<String, String> time = new HashMap<String, String>();
                time.put("%time%", "Tag");
                ChatUtils.setHexText(ChatOutput.SETTIME.getText(time));
            }else{
                ChatUtils.setHexText(ChatOutput.PREFIX.getText() + "&7 - &e/&bday");
            }
        }
        if(command.getName().equalsIgnoreCase("night")){
            if(args.length == 0){
                world.setTime(0);
                HashMap<String, String> time = new HashMap<String, String>();
                time.put("%time%", "Nacht");
                ChatUtils.setHexText(ChatOutput.SETTIME.getText(time));
            }else{
                ChatUtils.setHexText(ChatOutput.PREFIX.getText() + "&7 - &e/&bnigth");
            }
        }
        return true;
    }
}
