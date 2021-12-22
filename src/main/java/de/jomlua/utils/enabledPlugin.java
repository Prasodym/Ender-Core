package de.jomlua.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class enabledPlugin {
    public enabledPlugin() {
    }

    public static void StartLogo(){
        String serverversion = Bukkit.getVersion().toString();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIX.getText()) + "§eDas Plugin Startet jetzt..");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIX.getText()) + "");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIX.getText()) + "§3      _  _  _                             _  _                            ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIX.getText()) + "§3     (_)(_)(_)                           (_)(_)                           ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIX.getText()) + "§3        (_)    _  _  _      _  _   _  _     (_)   _         _     _  _  _ ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIX.getText()) + "§3        (_) _ (_)(_)(_) _  (_)(_)_(_)(_)    (_)  (_)       (_)   (_)(_)(_) _");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIX.getText()) + "§3        (_)(_)         (_)(_)   (_)   (_)   (_)  (_)       (_)    _  _  _ (_)");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIX.getText()) + "§3 _      (_)(_)         (_)(_)   (_)   (_)   (_)  (_)       (_)  _(_)(_)(_)(_)");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIX.getText()) + "§3(_)  _  (_)(_) _  _  _ (_)(_)   (_)   (_) _ (_) _(_)_  _  _(_)_(_)_  _  _ (_)_ ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIX.getText()) + "§3 (_)(_)(_)    (_)(_)(_)   (_)   (_)   (_)(_)(_)(_) (_)(_)(_) (_) (_)(_)(_)  (_)");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIX.getText()) + "");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIX.getText()) + "§2Version: §c0.3.8 | §eServerversion: §c" + serverversion);
    }
}
