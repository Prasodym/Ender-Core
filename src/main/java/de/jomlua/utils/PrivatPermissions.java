package de.jomlua.utils;

public enum PrivatPermissions {

    HEAL("jomlua.heal"),
    HOME("jomlua.home.use"),
    SETHOME("jomlua.home.set"),
    SETHOMEMULTI("jomlua.sethome.multi"),
    CHATLITENER("jomlua.chat.color"),
    SETSPAWN("jomlua.spawn.set"),
    SPAWN("jomlua.spawn"),
    SPAWNOUT("jomlua.spawn.outher"),
    TP("jomlua.tp"),
    TPOS("jomlua.tpos"),
    WARP("jomlua.warp"),
    DELWARP("jomlua.warp.delete"),
    SETWARP("jomlua.warp.set"),
    GETHOME("jomlua.admin.gethome"),
    FLY("jomlua.admin.fly"),
    WORLDMANAGER("jomlua.admin.world"),
    ADMIN("jomlua.admin"),
    VANISH("jomlua.vanish"),
    GAMEMODE("jomlua.admin.gamemode");

    private String text;

    PrivatPermissions(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
