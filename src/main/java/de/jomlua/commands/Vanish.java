package de.jomlua.commands;

import de.jomlua.core;
import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.CoreCommand;
import de.jomlua.utils.PrivatPermissions;
import de.jomlua.utils.modules.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Vanish extends CoreCommand {
    @Override
    public String getName() {
        return "vanish";
    }

    @Override
    public boolean hasCompleter() {
        return false;
    }

    @Override
    public String getHelpCommand() {
        return "vanish";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        VanishManager vanishManager = core.getPlugin().getvManager();
        if (args.length == 1){
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null){
                sender.sendMessage(ChatOutput.PREFIX.getText() + "Der spieler ist nicht Online.");
                return true;
            }
            if (vanishManager.isVanish(target)){
                vanishManager.setVanished(target, false);
                sender.sendMessage(ChatOutput.PREFIX.getText() + "Der Spieler ist jetzt wieder sichtbar.");
            }else{
                vanishManager.setVanished(target, true);
                sender.sendMessage(ChatOutput.PREFIX.getText() + "Dist jetzt unsichtbar.");
            }
            return true;
        }else if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission(PrivatPermissions.VANISH.getText())){
                if (vanishManager.isVanish(player)){
                    vanishManager.setVanished(player, false);
                    sender.sendMessage(ChatOutput.PREFIX.getText() + "Du bist jetzt wieder sichtbar.");
                }else{
                    vanishManager.setVanished(player, true);
                    sender.sendMessage(ChatOutput.PREFIX.getText() + "Du bist jetzt unsichtbar.");
                }
                return true;
            }else{
                sender.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            }
            return true;
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
