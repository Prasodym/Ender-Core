package de.jomlua.commands;

import de.jomlua.core;
import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.CoreCommand;
import de.jomlua.utils.config.CoreConfig;
import de.jomlua.utils.modules.Chatinterfaces;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class rules extends CoreCommand {
    @Override
    public String getName() {
        return "rules";
    }

    @Override
    public boolean hasCompleter() {
        return false;
    }

    @Override
    public String getHelpCommand() {
        return "test";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player  = (Player) sender;
        File file = new File(core.plugin.getDataFolder(),"rules.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);

        if (args.length == 0){
            ChatUtils.msg(player, ChatOutput.PREFIX.getText() + ChatOutput.COMMAND_RULES.getText());
            int o = 1;
            for (int i = 0; i < yml.getList("rules").size(); i++){
                String msg = "&e&l" + o++ + " &7-> §d" + yml.getList("rules").get(i).toString();
                ChatUtils.msg(player, msg);
            }
            ChatUtils.msg(player, ChatOutput.COMMAND_RULES_FOOTER.getText());
            try {
                CoreConfig.setServerRules(player);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
