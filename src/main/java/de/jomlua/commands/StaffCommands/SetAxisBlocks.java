package de.jomlua.commands.StaffCommands;

import de.jomlua.listener.SelectAxis;
import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.modules.RegionModule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SetAxisBlocks implements CommandExecutor, TabCompleter {
    private String Command = "setz";


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

//todo pERMISSION sETBLOCK ERSTELLEN
        if (!(player.hasPermission("jomlua.admin.setblock"))){
            ChatUtils.NoPerm(player);
            return true;
        }

        if (!(sender instanceof Player)){
            ChatUtils.ConsolenMsg("Du kannst nur im Spiel diesen Befehl nutzen.");
            return true;
        }

        if (command.getName().equalsIgnoreCase(Command)){
            if (args.length == 1){
                Location pos1 = SelectAxis.position1.get(player);
                Location pos2 = SelectAxis.position2.get(player);

                List<Location> list= RegionModule.SelectRegion(pos1, pos2);
                try {
                    int zahl = 0;
                    for (Location loc : list){
                        if (Material.valueOf(args[0].toUpperCase()) == null){
                            loc.getBlock().setType(Material.valueOf(args[0].toUpperCase()));
                            zahl = list.size();
                        }else{
                            sender.sendMessage("§7Dieser Block existiert nicht" + " (§c" + args[0] + ")");
                        }


                    }
                    //SetSelBlock.position1.remove(player);
                    //SetSelBlock.position2.remove(player);

                    ChatUtils.msg(player, ChatOutput.PREFIX.getText() + "&7Setze &a " + zahl +" §7mal &a" + args[0].toLowerCase());
                    ChatUtils.msg(player, list.toString());

                }catch (NullPointerException e){
                    ChatUtils.msg(player, ChatOutput.PREFIX.getText() + "&cMarkiere erst deine Region! ");
                    return true;
                }
                return true;
            }

        }


        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1){
            for (Material values : Material.values()){
                if (values.isBlock()){
                    list.add(values.toString().toLowerCase());
                }
            }
        }else if (args.length == 2){
            for (int i = 0; i < 1; i++){
                list.add(String.valueOf(i));
            }
        }

        ArrayList<String> complete = new ArrayList<>();
        String current = args[args.length-1].toLowerCase();

        for (String s : list){
            String s1 = s.toLowerCase();
            if (s1.startsWith(current)){
                complete.add(s);
            }
        }
        return complete;
    }
}
