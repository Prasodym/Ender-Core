package de.jomlua.commands.StaffCommands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.PrivatPermissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Speed implements CommandExecutor {

    String Permission = PrivatPermissions.SPEED.getText();
    String CommandName = "speed";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (!(player.hasPermission(Permission))){
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (!(sender instanceof Player)){
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
            return true;
        }

        if (cmd.getName().equalsIgnoreCase(CommandName)){
            if (args.length == 1){
                float eingabe = Float.parseFloat(args[0]);
                try {
                if (eingabe > 0.8){
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatUtils.setHexText("&cBitte nehme rücksicht auf andere Spieler, das schelle Fliegen ist sehr Serverlastig"));
                    player.setFlySpeed(eingabe);
                    player.setWalkSpeed(eingabe);
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatUtils.setHexText("&fDu hast deine geschwindigkeit erhöht auf &c" + eingabe));
                    return true;
                }

                    player.setFlySpeed(Float.parseFloat(args[0]));
                    player.setWalkSpeed(Float.parseFloat(args[0]));
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatUtils.setHexText("&fDu hast deine geschwindigkeit erhöht auf &c" + args[0]));
                }catch (IllegalArgumentException e){
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatUtils.setHexText("&fDieser wert &c" + args[0] + ", &fist zu hoch. Erlaubte werte: &e0.1 &fbis &e1.0"));
                }


            }else{
                player.setFlySpeed((float) 0.1);
                player.setWalkSpeed((float) 0.2);
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatUtils.setHexText("&fDeine geschwindigkeit ist wieder normal."));
            }
        }
        return true;
    }
}
