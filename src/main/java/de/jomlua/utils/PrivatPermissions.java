package de.jomlua.utils;

public enum PrivatPermissions {

    HEAL("jomlua.heal"),
    HOME("jomlua.home.use"),
    SETHOME("jomlua.sethome"),
    SETHOMEMULTI("jomlua.sethome.multi"),
    CHATLITENER("jomlua.chat.color"),
    SETSPAWN("jomlua.spawn.set"),
    SPAWN("jomlua.spawn"),
    SPAWNOUT("jomlua.spawn.outher"),
    TP("jomlua.tp"),
    TPOS("jomlua.tpos");

    private String text;

    PrivatPermissions(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
