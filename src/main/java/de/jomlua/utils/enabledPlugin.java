package de.jomlua.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class enabledPlugin {
    public enabledPlugin() {
    }

    public static void StartLogo(){
        String serverversion = Bukkit.getVersion().toString();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIX.getText()) + "§eDas Plugin ist jetzt einsatzbereit.");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(ChatUtils.setHexText("&3      _  _  _                             _  _                                 "));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.setHexText("&3     (_)(_)(_)                           (_)(_)                                "));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.setHexText("&3        (_)    _  _  _      _  _   _  _     (_)   _         _     _  _  _      "));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.setHexText("&3        (_) _ (_)(_)(_) _  (_)(_)_(_)(_)    (_)  (_)       (_)   (_)(_)(_) _   "));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.setHexText("&3        (_)(_)         (_)(_)   (_)   (_)   (_)  (_)       (_)    _  _  _ (_)  "));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.setHexText("&3 _      (_)(_)         (_)(_)   (_)   (_)   (_)  (_)       (_)  _(_)(_)(_)(_)  "));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.setHexText("&3(_)  _  (_)(_) _  _  _ (_)(_)   (_)   (_) _ (_) _(_)_  _  _(_)_(_)_  _  _ (_)_ "));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.setHexText("&3 (_)(_)(_)    (_)(_)(_)   (_)   (_)   (_)(_)(_)(_) (_)(_)(_) (_) (_)(_)(_)  (_)"));
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIX.getText()) + "      §2Version: §c0.4.0 | §eServerversion: §c" + serverversion);
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIX.getText()) + "            §6Kompatibel ab 1.16, darunter kein Support.");
    }
}
