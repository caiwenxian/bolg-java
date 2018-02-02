package model.enums.music;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-17 17:48]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TopListType {

    A("云音乐新歌榜", "/api/playlist/detail?id=3779629", 1),
    B("云音乐热歌榜", "/api/playlist/detail?id=3778678", 2),
    C("网易原创歌曲榜", "/api/playlist/detail?id=2884035", 3),
    D("云音乐飙升榜", "/api/playlist/detail?id=19723756", 4),
    E("云音乐电音榜", "/api/playlist/detail?id=10520166", 5),
    F("UK排行榜周榜", "/api/playlist/detail?id=180106", 6),
    G("美国Billboard周榜", "/api/playlist/detail?id=60198", 7),
    H("KTV嗨榜", "/api/playlist/detail?id=21845217", 8),
    I("iTunes榜", "/api/playlist/detail?id=11641012", 9),
    J("Hit FM Top榜", "/api/playlist/detail?id=120001", 10),
    K("日本Oricon周榜", "/api/playlist/detail?id=60131", 11),
    L("韩国Melon排行榜周榜", "/api/playlist/detail?id=3733003", 12),
    M("韩国Mnet排行榜周榜", "/api/playlist/detail?id=60255", 13),
    N("韩国Melon原声周榜", "/api/playlist/detail?id=46772709", 14),
    O("中国TOP排行榜(港台榜)", "/api/playlist/detail?id=112504", 15),
    P("中国TOP排行榜(内地榜)", "/api/playlist/detail?id=64016", 16),
    Q("香港电台中文歌曲龙虎榜", "/api/playlist/detail?id=10169002", 17),
    R("华语金曲榜", "/api/playlist/detail?id=4395559", 18),
    S("中国嘻哈榜", "/api/playlist/detail?id=1899724", 19),
    T("法国 NRJ EuroHot 30周榜", "/api/playlist/detail?id=27135204", 20),
    V("台湾Hito排行榜", "/api/playlist/detail?id=112463", 21),
    W("Beatport全球电子舞曲榜", "/api/playlist/detail?id=3812895", 22);

    private String name;

    private String url;

    private int code;


    TopListType(String name, String url, int code){
        this.name = name;
        this.url = url;
        this.code = code;
    }

    public static TopListType topListType(String name){
        for (TopListType top : TopListType.values()) {
            if (name.equals(top.getName())) {
                return top;
            }
        }
        return null;

    }

    // 获取url
    public static String getUrl(int code) {
        for (TopListType c : TopListType.values()) {
            if (c.getCode() == code) {
                return c.url;
            }
        }
        return null;
    }

    // 获取url
    public static String getUrl(String name) {
        for (TopListType c : TopListType.values()) {
            if (c.getName() == name) {
                return c.url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
                ", url='" + url + '\'' +
                ", code=" + code +
                '}';
    }
}
