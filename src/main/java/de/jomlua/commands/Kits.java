package de.jomlua.commands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.modules.KitModule;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Kits implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;

        if (!(player.hasPermission("jomlua.kits"))){
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (!(sender instanceof Player)){
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
            return true;
        }

        if (command.getName().equalsIgnoreCase("kit")) {
            if (args.length == 1){
                String kitname = args[0];
                HashMap<String, String> repl1 = new HashMap<String, String>();
                repl1.put("{0}", kitname);
                if (player.hasPermission("jomlua.kit.*") || player.hasPermission("jomlua.kit." + kitname.toLowerCase())){


                    if (!(KitModule.KitExist(kitname.toLowerCase()))){

                        player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.SAVEKITEXIST.getText(repl1));
                        return true;
                    }

                    if (!(KitModule.getAlradyKitUse(player,kitname))){
                        player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.GETKITUSE.getText());
                        return true;
                    }
                    KitModule.getKit(kitname,player);

                    try {
                        KitModule.setAlradyKitUse(player,kitname);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.GETKIT.getText(repl1));

                    return true;
                }else{
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
                }
            }
            return true;
        }

        if (command.getName().equalsIgnoreCase("createkit")){
            if (!(player.hasPermission("jomlua.kit.create"))){
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
                return true;
            }
            if (args.length == 1){
                //Long delay = Long.parseLong(args[1]);
                String kitname = args[0];
                ItemStack[] items = player.getInventory().getContents();
                Inventory inv = null;

                ArrayList<ItemStack> list = new ArrayList<>();
                HashMap<String, String> repl1 = new HashMap<String, String>();
                repl1.put("{0}", kitname);

                if (KitModule.KitExist(kitname.toLowerCase())){

                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.SAVEKITEXIST.getText(repl1));
                    return true;
                }

                for (int i = 0; i<items.length; i++){
                    ItemStack is = items[i];
                    if (is != null && is.getType() != null && is.getType() != Material.AIR){

                        list.add(is);
                    }
                }
                try {
                    KitModule.SaveKit(kitname.toLowerCase(),1, list);
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.SAVEKIT.getText(repl1));
                } catch (IOException e) {
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.DANGER.getText() + "Fehler!");
                    e.printStackTrace();
                }
            }else{
                player.sendMessage(ChatOutput.PREFIX.getText() + "Probiere doch mit /createkit <name> <delay>");
                return true;
            }
        }
        if (command.getName().equalsIgnoreCase("kits")){
            player.sendMessage(ChatOutput.PREFIX.getText() + "§aAlle Kits §7(§c" + KitModule.getAmoutKit().size() + "§7) ");
            player.sendMessage("");

            KitModule.ListKit(player);
            return true;
        }

        return true;
    }
}
