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
    TPHERE("jomlua.tphere"),
    TPA("jomlua.tpa"),
    WARP("jomlua.warp"),
    DELWARP("jomlua.warp.delete"),
    SETWARP("jomlua.warp.set"),
    GETHOME("jomlua.admin.gethome"),
    FLY("jomlua.admin.fly"),
    WORLDMANAGER("jomlua.admin.world"),
    ADMIN("jomlua.admin"),
    VANISH("jomlua.vanish"),
    GAMEMODE("jomlua.admin.gamemode"),
    SPEED("jomlua.admin.speed"),
    LOAD("jomlua.plugin.load"),
    ITEMUNNAME("jomlua.admin.itemunname"),
    COLOREDSIGNS("jomlua.sign.color"),
    WARPSIGNUSE("jomlua.sign.warpuse"),
    MSG("jomlua.msg"),
    WARPSIGNADD("jomlua.sign.warpadd"),
    INVSEE("jomlua.admin.invsee"),
    HEAD("jomlua.admin.head"),
    SETTIME("jomlua.admin.time");

    private String text;

    PrivatPermissions(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
