package de.jomlua.commands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.PrivatPermissions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Tp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        String prefix = ChatOutput.PREFIX.getText();

        if (!(sender instanceof Player)){
            sender.sendMessage(ChatOutput.NO_PLAYER.getText());
            return true;
        }

        if (command.getName().equalsIgnoreCase("tp")){
            if (!(player.hasPermission(PrivatPermissions.TP.getText()))){
                player.sendMessage(prefix + ChatOutput.NO_PERMISSIONS.getText());
                return true;
            }
            if (args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null){
                    if (player != target){
                        player.teleport(target);
                        player.sendMessage(ChatOutput.PREFIX.getText()+ "§aDu hast dich zu §c" + target.getName() + "§a teleportiert.");
                        target.sendMessage(ChatOutput.PREFIX.getText()+ "§c" + player.getName() + "§a hat sich zu dir teleportiert.");
                    }else{
                        player.sendMessage(ChatOutput.PREFIX.getText()+ "§cDu kannst dich nicht zu dir teleportieren.");
                        return true;
                    }
                }else{
                    player.sendMessage(ChatOutput.PREFIX.getText()+ "§cDer Spieler ist gerade nicht da.");
                    return true;
                }
            }else{
                player.sendMessage(ChatOutput.PREFIX.getText()+ "§cDu musst ein Spieler mit angeben.");
            }
        }

        if (command.getName().equalsIgnoreCase("tpos")){
            if (!(player.hasPermission(PrivatPermissions.TPOS.getText()))){
                player.sendMessage(prefix + ChatOutput.NO_PERMISSIONS.getText());
                return true;
            }
            if (args.length == 3){
                World world;

                Location location = new Location(player.getWorld(), Double.parseDouble(args[0]),Double.parseDouble(args[1]) ,Double.parseDouble(args[2]) );


                player.teleport(location);
                player.sendMessage(ChatOutput.PREFIX.getText()+ "§aDu hast dich zu §cX: " + Double.parseDouble(args[0]) + " Y: " + Double.parseDouble(args[3]) + " Z: " + Double.parseDouble(args[2]) + "§a teleportiert.");

                return true;
            }else{
                player.sendMessage(ChatOutput.COMMAND_HELP.getText());
                player.sendMessage(ChatOutput.PREFIX.getText()+ "§a/tpo§e <x> <y> <z>");
            }
        }

        if (command.getName().equalsIgnoreCase("tphere")){
            if (sender instanceof Player){

                if(player.hasPermission("jomlua.tphere")){
                    if (args.length == 1){
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null){
                            if (target != player){
                                target.teleport(player);

                                player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu hast dich zu §c" + target.getName() + "§a teleportiert.");
                                target.sendMessage(ChatOutput.PREFIX.getText() + "§c" + target.getName() + "§a hat sich zu dir teleportiert.");
                                return true;
                            }else{
                                player.sendMessage(ChatOutput.PREFIX.getText() + "§cDu kannst dich nicht zu dir teleportieren.");
                                return true;
                            }
                        }else{
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§cDer Spieler ist gerade nicht da.");
                            return true;
                        }

                    }else{
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§cDu musst ein Spieler mit angeben.");
                    }
                }else{
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
                }

            }else{
                sender.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
                return true;
            }
        }
        return true;
    }
}
