package de.jomlua.utils;

import java.util.HashMap;

public enum ChatOutput {

    PREFIX("prefix", "&8[&3Jo&amlua&8] &f"),
    PREFIXC("PrefixConsole", "&8[&3Jomlua&8] "),
    PREFIXECO("PrefixEco", "&7[Jomlua &aE&3co&7] &8&l| "),
    DANGER("Danger", "&e[&4&lHEIGHT&e] &c"),
    WARNING("Warning", "&e[&e&lWARNING&e] &c"),
    ECO_PREFIX("EcoPrefix", "&8[&3E&aco&8] "),
    NO_PERMISSIONS("error.permission.denid", "&cYou have no permission to do this."),
    LOADING_PLUGIN("loadplugin","&aLoading all dependecis."),
    NO_PLAYER("error.noplayer.exist", "&cDieser Spieler existiert nicht."),
    HEAL_TO("player.healto","&aDu wuredes von &c%target% &ageheilt."),
    HEAL_FOR_ME("msg.heal.forme", "§aDu hast §3§l%target%§a geheilt."),
    TPA_DENY_A("TpaDenidTarget", "&cDu hast die TPA anfrage geblockt"),
    TPA_DENY_B("TpaDenidPlayer", "&c%target% &e hat deine TPA anfrage abgelehnt."),
    TPA_DENY_C("TpaTo", "&c%target% &eHat sich zu dir Teleportiert."),
    TPA_DENY_D("TpaNoRequest", "&cDu hast keine offene Tpa anfragen."),
    TPA_DENY_E("TpaRequest", "&7Du hast eine austehende Tpa anfrage von &c%player%"),
    TPA_DENY_G("TpaSendRequest", "&2Deine Anfrage wurde verschickt."),
    TPA_DENY_H("TpaPlayerNotOnline", "&cDer spieler &a%target% &cist gerade nicht online."),
    TPA_DENY_I("TpaNoRequest1","&cDu hast keine offene anfragen"),
    TPA_DENY_J("TpaTargetToMe", "&c%player% &7Möchte das du dich zu ihn Teleportierst"),
    NO_PLAYER_ONLINE("error.noplayer.online", "&cDer Spieler &e%np_player% &cist derzeit nicht online."),
    COMMAND_HELP("help.header", "&aBefehle die dir Helfen könnten:"),
    COMMAND_HOME_TELEPORT("home.teleport", "Teleport.."),
    COMANND_HOME_DEFAULTEXIST("home.defaultexist", "Das Home &c%home% &fexistiert nicht."),
    COMMAND_HOME_SET("home.set", "Dein Home &cHome &fwurde gespeichert."),
    COMMAND_DISCORD_HEADER("Discord.adress", "&7Folgende Discord Adresse:"),
    COMMAND_TS_HERDER("Teamspeak.adress", "&7Folgende Teamspeak adresse:"),
    SAVEKIT("SaveKit", "&aDu hast das Kit &c{0} &agespeichert"),
    SAVEKITEXIST("KitAlreadyExist", "&cDas Kit &e{0} &cgibt es schon."),
    GETKIT("GiveKit", "&eDu hast ein Kit &c{0} &ebekommen."),
    GETKITUSE("GetKitAlreadyUse", "&cDu hast bereits diesen Kit benutzt."),
    COMMAND_RULES("Rules.Header", "&aAlle Serverregeln, Lese sie bitte komplett durch."),
    COMMAND_RULES_FOOTER("Rules.Footer", "&7 - &c&nMit der eingabe dieses Commands Akzeptierst du die regeln.");



private String text;
private String configPath;

    /**
     *
     * @param configPath Config hiragie angeben
     * @param text Chat text eingeben
     *
     */
    ChatOutput(String configPath, String text){
    this.text = text;
    this.configPath = configPath;
}

public  String getConfigName(){
    return this.configPath;
}
public void setText(String text){
    this.text = text;
}
public String getText(){
    return this.text;
}
public String getText(HashMap<String, String> replacements){
    String replText = this.text;
    for (String key : replacements.keySet()){
        replText = replText.replace(key, replacements.get(key));
    }
        return replText;
    }
    public  static ChatOutput getConfig(){
        for (ChatOutput b : ChatOutput.values()){
            if (b.getConfigName().equalsIgnoreCase(b.configPath)){
                return b;
            }
        }
        return null;
    }
}