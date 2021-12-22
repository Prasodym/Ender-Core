package de.jomlua.utils;

import java.util.HashMap;

public enum ChatOutput {

    PREFIX("prefix", "&8[&3Jo&amlua&8] &f"),
    NO_PERMISSIONS("error.permission.denid", "&cYou have no permission to do this."),
    LOADING_PLUGIN("loadplugin","&aLoading all dependecis"),
    NO_PLAYER("error.noplayer.exist", "&cDieser Spieler existiert nicht."),
    HEAL_TO("player.healto","&aDu wuredes von &c%target% &ageheilt."),
    HEAL_FOR_ME("msg.heal.forme", "§aDu hast §3§l%target%§a geheilt."),
    NO_PLAYER_ONLINE("error.noplayer.online", "&cDer Spieler &e%np_player% &cist derzeit nicht online."),
    COMMAND_HELP("help.header", "&aBefehle die dir Helfen könnten:"),
    COMMAND_HOME_TELEPORT("home.teleport", "Teleport.."),
    COMANND_HOME_DEFAULTEXIST("home.defaultexist", "Das Home &c%home% &fexistiert nicht."),
    COMMAND_HOME_SET("home.set", "Dein Home &cHome &fwurde gespeichert.");



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