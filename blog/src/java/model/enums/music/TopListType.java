package model.enums.music;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-17 17:48]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TopListType {

    A("云音乐新歌榜", "3779629", 1),
    B("云音乐热歌榜", "3778678", 2),
    C("网易原创歌曲榜", "2884035", 3),
    D("云音乐飙升榜", "19723756", 4),
    E("云音乐电音榜", "10520166", 5),
    F("UK排行榜周榜", "180106", 6),
    G("美国Billboard周榜", "60198", 7),
    H("KTV嗨榜", "21845217", 8),
    I("iTunes榜", "11641012", 9),
    J("Hit FM Top榜", "120001", 10),
    K("日本Oricon周榜", "60131", 11),
    L("韩国Melon排行榜周榜", "3733003", 12),
    M("韩国Mnet排行榜周榜", "60255", 13),
    N("韩国Melon原声周榜", "46772709", 14),
    O("中国TOP排行榜(港台榜)", "112504", 15),
    P("中国TOP排行榜(内地榜)", "64016", 16),
    Q("香港电台中文歌曲龙虎榜", "10169002", 17),
    R("华语金曲榜", "4395559", 18),
    S("中国嘻哈榜", "1899724", 19),
    T("法国 NRJ EuroHot 30周榜", "27135204", 20),
    V("台湾Hito排行榜", "112463", 21),
    W("Beatport全球电子舞曲榜", "3812895", 22);

    private String name;

    private String id;

    private int code;


    TopListType(String name, String id, int code) {
        this.name = name;
        this.id = id;
        this.code = code;
    }

    public static TopListType topListType(String name) {
        for (TopListType top : TopListType.values()) {
            if (name.equals(top.getName())) {
                return top;
            }
        }
        return null;

    }

    // 获取id
    public static String getId(int code) {
        for (TopListType c : TopListType.values()) {
            if (c.getCode() == code) {
                return c.id;
            }
        }
        return null;
    }

    // 获取id
    public static String getId(String name) {
        for (TopListType c : TopListType.values()) {
            if (c.getName() == name) {
                return c.id;
            }
        }
        return null;
    }

    // 获取name
    public static String getName(String id) {
        for (TopListType c : TopListType.values()) {
            if (c.getId().equals(id)) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "TopListType{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", code=" + code +
                '}';
    }
}
