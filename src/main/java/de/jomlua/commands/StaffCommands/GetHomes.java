package de.jomlua.commands.StaffCommands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.CommandHomeUtil;
import de.jomlua.utils.PrivatPermissions;
import de.jomlua.utils.modules.Chatinterfaces;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;


public class GetHomes implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        String prefix = ChatOutput.PREFIX.getText();
        if (!(player.hasPermission(PrivatPermissions.GETHOME.getText()))){
            player.sendMessage(prefix + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatOutput.NO_PLAYER.getText());
            return true;
        }
        if (command.getName().equalsIgnoreCase("gethome")){
            if (args.length == 1){
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                System.out.println(target.getUniqueId().toString());
                File fIle = new File("plugins/jomlua-core/users", target.getUniqueId() + ".yml");
                YamlConfiguration cnf = YamlConfiguration.loadConfiguration(fIle);
                if (fIle.exists()){
                    Chatinterfaces.ListetHomeTargetInterface(player, target);
                }else{
                    ChatUtils.msg(player, ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
                }

            }
        }

        // diser befehl ist nur aus Plugininterne gründen da.
        // -->   /atphome <target> <home>
        // -->   Permissions: jomlua.admin.gethome
        if (command.getName().equalsIgnoreCase("atphome")){
            if (args.length == 2){
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                String destination = args[1];

                CommandHomeUtil.TeleportTargetHome(player, destination,target);
            }
        }

        return true;
    }
}
