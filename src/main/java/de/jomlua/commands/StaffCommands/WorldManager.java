package de.jomlua.commands.StaffCommands;

import de.jomlua.core;
import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.PrivatPermissions;
import de.jomlua.utils.modules.WorldManagerModule;
import de.jomlua.utils.modules.voidgenerator;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.bukkit.WorldType.*;

public class WorldManager implements CommandExecutor, TabCompleter {
    public static final String mw = "§7[§aMW§7] ";
    private core plugin;
    public WorldManager(core plugin){
        this.plugin = plugin;
    }

    public static File file = new File(core.plugin.getDataFolder(), "worlds.yml");
    public static YamlConfiguration cfg =YamlConfiguration.loadConfiguration(file);

    public static List<String> MAPS = cfg.getStringList("Worlds");
    private WorldManagerModule voidGen;
    private HashMap<Player, String> confirm = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(player.hasPermission(PrivatPermissions.WORLDMANAGER.getText()))){
            ChatUtils.msg(player, ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(mw + "§aBefehle die dir helfen könnten.");
            player.sendMessage("- §e/§bmw create §c<Normal, Flatworld ..>");
            player.sendMessage("- §e/§bmw tp §c<world>");
            player.sendMessage("- §e/§bmw delete §c<world>");
        } else if (args[0].equalsIgnoreCase("tp")) {
            // /maxworld tp <name>
                if (args.length == 2) {
                    String world = args[1];
                    if (!(Bukkit.getServer().getWorld(world) == null)) {
                        try {
                            player.sendMessage(mw + "§aTeleportiere zu §c" + world);
                            player.teleport(Bukkit.getWorld(world).getSpawnLocation());
                        }catch (IllegalArgumentException e){
                            player.sendMessage("§aDie Map §c" + world + " §a kann nicht geladen werden und ist fehlerhaft.");
                        }
                    } else {
                        player.sendMessage(mw + "§cDiese Welt existiert nicht!");
                    }
                }
        } else if (args[0].equalsIgnoreCase("create")) {
                String name = args[2];
                if (!MAPS.contains(name)) {
                    if (args[1].equalsIgnoreCase("normal")) {
                        player.sendMessage(mw + "§3Die Welt " + name + " wird erstellt!");
                        WorldCreator w = WorldCreator.name(name);
                        Bukkit.createWorld(w);
                        Bukkit.getWorlds().add(Bukkit.getWorld(name));
                        MAPS.add(name);
                        cfg.set("Worlds", MAPS);
                        player.sendMessage(mw + "§aDie Welt " + name + " wurde erfolgreich erstellt!");
                    } else if (args[1].equalsIgnoreCase("nether")) {
                        player.sendMessage(mw + "§3Die Welt " + name + " wird erstellt!");
                        WorldCreator w = WorldCreator.name(name);
                        w.environment(World.Environment.NETHER);
                        Bukkit.createWorld(w);
                        Bukkit.getWorlds().add(Bukkit.getWorld(name));
                        MAPS.add(name);
                        cfg.set("Worlds", MAPS);
                        player.sendMessage(mw + "§aDie Welt " + name + " wurde erfolgreich erstellt!");
                    } else if (args[1].equalsIgnoreCase("end")) {

                        player.sendMessage(mw + "§3Die Welt " + name + " wird erstellt!");
                        WorldCreator w = WorldCreator.name(name);
                        w.environment(World.Environment.THE_END);
                        Bukkit.createWorld(w);
                        Bukkit.getWorlds().add(Bukkit.getWorld(name));
                        MAPS.add(name);
                        cfg.set("Worlds", MAPS);
                        player.sendMessage(mw + "§aDie Welt " + name + " wurde erfolgreich erstellt!");
                    } else if (args[1].equalsIgnoreCase("flat")) {
                        player.sendMessage(mw + "§3Die Welt " + name + " wird erstellt!");
                        WorldCreator w = WorldCreator.name(name);
                        w.type(FLAT);
                        Bukkit.createWorld(w);
                        Bukkit.getWorlds().add(Bukkit.getWorld(name));
                        MAPS.add(name);
                        cfg.set("Worlds", MAPS);
                        player.sendMessage(mw + "§aDie Welt " + name + " wurde erfolgreich erstellt!");
                    } else if (args[1].equalsIgnoreCase("large_biomes")) {
                        player.sendMessage(mw + "§3Die Welt " + name + " wird erstellt!");
                        WorldCreator w = WorldCreator.name(name);
                        w.type(LARGE_BIOMES);
                        Bukkit.createWorld(w);
                        Bukkit.getWorlds().add(Bukkit.getWorld(name));
                        MAPS.add(name);
                        cfg.set("Worlds", MAPS);
                        player.sendMessage(mw + "§aDie Welt " + name + " wurde erfolgreich erstellt!");
                    } else if (args[1].equalsIgnoreCase("AMPLIFIED")) {
                        player.sendMessage(mw + "§3Die Welt " + name + " wird erstellt!");
                        WorldCreator w = WorldCreator.name(name);
                        w.type(AMPLIFIED);
                        Bukkit.createWorld(w);
                        Bukkit.getWorlds().add(Bukkit.getWorld(name));
                        MAPS.add(name);
                        cfg.set("Worlds", MAPS);
                        player.sendMessage(mw + "§aDie Welt " + name + " wurde erfolgreich erstellt!");
                    }else if (args[1].equalsIgnoreCase("void")){
                        player.sendMessage(mw + "§3Die Welt " + name + " wird erstellt!");
                        WorldCreator w = WorldCreator.name(name);
                        w.generator(new voidgenerator());


                        Bukkit.createWorld(w);
                        Bukkit.getWorlds().add(Bukkit.getWorld(name));
                        MAPS.add(name);
                        cfg.set("Worlds", MAPS);
                        Location loc = new Location(Bukkit.getWorld(name), 0,100,0);
                        WorldManagerModule.platform(loc, 3, 3);
                        //VoidStartBlock(name);
                        Bukkit.getWorld(name).setFullTime(13000);

                        Bukkit.getWorld(name).setSpawnLocation(0,101,0);
                        player.sendMessage(mw + "§aDie Welt " + name + " wurde erfolgreich erstellt!");
                    } else {
                        player.sendMessage(mw + "- /World create NORMAL/WORLD_NETHER/END/FLATMAP/Large_Biomes/AMPLIFIED <Name>");
                        player.sendMessage(mw + "- /World tp <Name>");
                    }
                    try {
                        cfg.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    player.sendMessage(mw + "Diese Welt gibt es bereits");
                }

        } else if (args[0].equalsIgnoreCase("delete")) {
            if (args.length == 2) {
                String name = args[1];
                if (MAPS.contains(name)) {
//                          player.teleport(Bukkit.getWorld("world").getSpawnLocation());
//                        cfg.getString(name, null);
                    player.sendMessage(mw + "§CUm das zu bestätigen gebe §e/mw confirm §cein.");
                    confirm.put(player, name);
                } else {
                    player.sendMessage(mw + "§cDiese Welt gibt es nicht.");
                }

            }
        }else if(args[0].equalsIgnoreCase("confirm")){
            if (!(confirm.containsKey(player))){
                // Keine anfrage msg
                player.sendMessage(ChatOutput.PREFIX.getText() + "Du muss erst eine anfrage schicken");
                return true;
            }

            String world = confirm.get(player);
            World delete = Bukkit.getWorld(world);
            File deleteFolder = delete.getWorldFolder();
            Bukkit.unloadWorld(delete,false);
            WorldManagerModule.deleteWorld(world, deleteFolder);
            MAPS.remove(world);
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.sendMessage(ChatOutput.PREFIX.getText() + "§fDie welt §c" + world + " §fwurde erfolgreich gelöscht");
            confirm.remove(player);


        } else if (args[0].equalsIgnoreCase("activ")) {
            if (args.length == 2) {
                String name = args[1];
                Bukkit.unloadWorld(name, true);
                player.sendMessage(mw + "§CDie welt wurde jetzt aktiviert.");
            }
        }else if (args[0].equalsIgnoreCase("import")){
            String world = args[1];

            File newFolder = new File(core.plugin.getServer().getWorldContainer(), world);
            if (newFolder.exists()){
                player.sendMessage("§aDie Map §c" + world + " §awird geladen.");

                try {
                    MAPS.add(world);
                    cfg.set("Worlds", MAPS);
                    WorldCreator w = WorldCreator.name(world);
                    Bukkit.createWorld(w);
                    Bukkit.getWorlds().add(Bukkit.getWorld(world));
                    player.sendMessage("§aDie Map §c" + world + " §aist nun bereit..");
                    try {
                        cfg.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }catch (IllegalArgumentException e){
                    player.sendMessage("§aDie Map §c" + world + " §a kann nicht geladen werden und ist fehlerhaft.");
                }
            }else{
                player.sendMessage(ChatOutput.PREFIX.getText() + "§cDieser Ordner Existiert nicht, Achte drauf das es sich um eine Map handelt.");
            }


        } else if (args[0].equalsIgnoreCase("info")) {
            String ani;
            String mons;
            String struck;
            if (player.getWorld().getAllowAnimals()) {
                ani = "Ja";

            } else {
                ani = "Nein";
            }
            if (player.getWorld().getAllowMonsters()) {
                mons = "Ja";

            } else {
                mons = "Nein";
            }

            if (player.getWorld().canGenerateStructures()){
                struck = "Aktiviert";
            }else{
                struck = "Deaktiviert";
            }
            if (player.hasPermission("jomlua.mv.list")) {
                player.sendMessage(mw + "§aInfomation von der welt in der du dich befindest:");
                player.sendMessage("§7 - [§a§l" + player.getWorld().getName() + "] §7-");
                player.sendMessage("§7--------------------");
                player.sendMessage("§7| Worldtyp: §c" + player.getWorld().getEnvironment());
                player.sendMessage("§7| World ID: §c" + player.getWorld().getEnvironment().getId());
                player.sendMessage("§7| PvP: §c" + player.getWorld().getPVP());
                player.sendMessage("§7| Seed: §c" + player.getWorld().getSeed());
                player.sendMessage("§7| Spawn Tiere: §c" + ani);
                player.sendMessage("§7| Spawn Monster: §c" + mons);
                player.sendMessage("§7| Strukturen: §c " + struck);
                player.sendMessage("§7--------------------");
            }
        } else if (args[0].equalsIgnoreCase("list")) {
                if (args.length == 1) {
                    if (sender instanceof Player) {
                        String[] worldNames = new String[Bukkit.getServer().getWorlds().size()];
                        int count = 0;
                        for (World w : Bukkit.getServer().getWorlds()) {
                            worldNames[count] = w.getName();
                            count++;
                        }

                        player.sendMessage(mw + "§3Alle aktiven Welten.");
                        int i = 1;
                        for (String list : worldNames) {

                            TextComponent teleport;
                            TextComponent delete;
                            TextComponent build;

                            teleport = ChatUtils.TcCommand("&7[&3Teleport&7] ", "/mw tp " + list, "&3Teleportiere zur " + list);
                            delete = ChatUtils.TcCommand("&7[&cDelete&7]", "/mw delete " + list.toString(), "§cKlicke um §a§l" + list.toString() + " §czu löschen.");
                            build = ChatUtils.TcText("§e§l" + i++ + " §a" + list + " §7>> ");

                            build.addExtra(teleport);
                            teleport.addExtra(delete);

                            player.spigot().sendMessage(build);
                        }
                    }else{
                        String[] worldNames = new String[Bukkit.getServer().getWorlds().size()];
                        int count = 0;
                        for (World w : Bukkit.getServer().getWorlds()) {
                            worldNames[count] = w.getName();
                            count++;
                        }

                        sender.sendMessage(mw + "§3Alle aktiven Welten.");
                        int i = 1;
                        for (String list : worldNames) {

                            sender.sendMessage("§e§l" + i++ + " §a" + list + " §7>> ");

                        }
                    }
                }
        }
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        //arr
        List<String> list = Arrays.asList("activ", "confirm", "list", "delete", "tp", "create", "info", "import");
        List<String> listtype = Arrays.asList("NORMAL", "nether", "end", "flat", "void");
        String[] worldNames = new String[Bukkit.getServer().getWorlds().size()];
        List<String> world = new ArrayList<>();


        // Var für /command <input>
        String input = args[0].toLowerCase();



        List<String> completion = null;

        if (!(sender.hasPermission(PrivatPermissions.WORLDMANAGER.getText()))){
            completion = null;
        }

        for (String stra: list){
            if (stra.startsWith(input)){
                if (completion == null){
                    completion = new ArrayList<>();
                }
                completion.add(stra);
            }
        }
        for (String type: listtype){
            if (args[0].equalsIgnoreCase("create")){
                if (completion == null){
                    completion = new ArrayList<>();
                }
                completion.add(type);
            }
        }
        for (World worlds : Bukkit.getServer().getWorlds()){
            if (args[0].equalsIgnoreCase("tp")){
                if (completion == null){
                    completion = new ArrayList<>();
                }
                completion.remove("tp");
                completion.add(worlds.getName());

            }
        }

        if (completion != null){
            Collections.sort(completion);
        }

        return completion;
    }

    private static void VoidStartBlock(String world){
        World world1 = Bukkit.getWorld(world);
        Location loc = new Location(world1, 0,100,0);

        assert world1 != null;
        world1.getBlockAt(loc).setType(Material.COBBLESTONE);
    }
    private boolean deleteWorld(File path) {
        if(path.exists()) {
            File[] files = path.listFiles();
            assert files != null;
            for (File value : files) {
                if (value.isDirectory()) {
                    deleteWorld(value);
                } else {
                    value.delete();
                }
            }
        }
        return(path.delete());
    }
}
