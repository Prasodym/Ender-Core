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
    TPOS("jomlua.tpos"),
    WARP("jomlua.warp"),
    DELWARP("jomlua.warp.delete"),
    SETWARP("jomlua.warp.set");

    private String text;

    PrivatPermissions(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
