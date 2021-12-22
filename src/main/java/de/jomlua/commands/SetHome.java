package de.jomlua.commands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.CommandHomeUtil;
import de.jomlua.utils.PrivatPermissions;
import de.jomlua.utils.config.CoreConfig;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

public class SetHome implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        Location location = player.getLocation();
        String prefix = ChatOutput.PREFIX.getText();
        int homes = CommandHomeUtil.getHomePoints(player).size();

        int homecount = CoreConfig.getintConfig("homecount");
        if (!(player.hasPermission(PrivatPermissions.SETHOME.getText()))){
            player.sendMessage(prefix + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatOutput.NO_PLAYER.getText());
            return true;
        }

        if (args.length == 0){
            try {
                CommandHomeUtil.CreateHome(player, location, "home");
                player.sendMessage(prefix+ChatOutput.COMMAND_HOME_SET.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (args.length == 1){
            if (homes == homecount && !player.hasPermission(PrivatPermissions.SETHOMEMULTI.getText())){
                player.sendMessage(prefix + "Du hast bereits §c" + homecount + "§fHomes, Lösche einen Home um ein neuen zu setzen.");
            }
            try {
                CommandHomeUtil.CreateHome(player,location,args[0]);
                player.sendMessage(prefix + "Dein Home §c" + args[0] + " §fwurde gesetzt.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
