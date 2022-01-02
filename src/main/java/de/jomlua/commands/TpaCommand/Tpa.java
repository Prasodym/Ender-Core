package de.jomlua.commands.TpaCommand;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.modules.TeleportTyp;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class Tpa implements CommandExecutor {
    public static HashMap<UUID, TeleportTyp> tpType = new HashMap<UUID, TeleportTyp>();
    public static HashMap<UUID,UUID> tpa = new HashMap<UUID, UUID>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {




        Player player = (Player) sender;
        if (args.length==1){
            if (player.hasPermission("jomlua.tpa")){
                try {
                    Player target = Bukkit.getPlayer(args[0]);
                    tpa.put(target.getUniqueId(), player.getUniqueId());
                    tpType.put(target.getUniqueId(), TeleportTyp.NORMAL);
                    HashMap<String, String> replacements = new HashMap<String, String>();
                    replacements.put("%player%", player.getName());
                    //r


                    target.sendMessage(ChatOutput.TPA_DENY_E.getText(replacements));
                    TextComponent teleport;
                    TextComponent deny;
                    TextComponent build;

                    teleport = ChatUtils.TcCommand("§7- §2[§aAkzeptieren§2]", "/tpacept", "§aKlicke um die anfrage zu akzeptieren");
                    deny = ChatUtils.TcCommand("&7[&cAblehnen&7] §7-", "/tpadeny", "§cKlicke um die anfrage abzulehnen");
                    build = ChatUtils.TcText(" §7oder ");

                    teleport.addExtra(build);
                    build.addExtra(deny);


                    target.spigot().sendMessage(teleport);
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.TPA_DENY_G.getText());
                    target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_ATTACK_WEAK,1f,2f);
                }catch (Exception e){
                    e.printStackTrace();
                    HashMap<String, String> replacements = new HashMap<String, String>();
                    replacements.put("%target%", args[0]);
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.TPA_DENY_H.getText(replacements)); //r Args0
                }
            }

        }else{
            player.sendMessage("§a- §e/tpa <Player>");
        }
        return false;
    }
}
