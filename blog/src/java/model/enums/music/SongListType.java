package model.enums.music;

/**
 * 歌单类型
 *
 * @Author: [caiwenxian]
 * @Date: [2018-02-09 14:24]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum  SongListType {

    A1("全部", 101),
    A2("华语", 102),
    A3("欧美", 103),
    A4("日语", 104),
    A5("韩语", 105),
    A6("粤语", 106),
    A7("小语种", 107),

    B1("流行", 201),
    B2("摇滚", 202),
    B3("民谣", 203),
    B4("电子", 204),
    B5("舞曲", 205),
    B6("说唱", 206),
    B7("轻音乐", 207),
    B8("爵士", 208),
    B9("乡村", 209),
    B10("R&B/Soul", 210),
    B11("古典", 211),
    B12("民族", 212),
    B13("英伦", 213),
    B14("金属", 214),
    B15("朋克", 215),
    B16("蓝调", 216),
    B17("雷鬼", 217),
    B18("世界音乐", 218),
    B19("拉丁", 219),
    B20("另类/独立", 220),
    B21("New Age", 221),
    B22("古风", 222),
    B23("后摇",223),
    B24("Bossa Nova", 224),

    C1("清晨", 301),
    C2("夜晚", 302),
    C3("学习", 303),
    C4("工作", 304),
    C5("午休", 305),
    C6("下午茶",306),
    C7("地铁", 307),
    C8("驾车", 308),
    C9("运动", 309),
    C10("旅行", 310),
    C11("散步", 311),
    C12("酒吧", 312),

    D1("怀旧", 401),
    D2("清新", 402),
    D3("浪漫", 403),
    D4("性感", 404),
    D5("伤感", 405),
    D6("治愈", 406),
    D7("放松", 407),
    D8("孤独", 408),
    D9("感动", 409),
    D10("兴奋", 410),
    D11("快乐", 411),
    D12("安静", 412),
    D13("思念", 413),

    E1("影视原音", 501),
    E2("ACG", 502),
    E3("校园", 503),
    E4("游戏", 504),
    E5("70后", 505),
    E6("80后", 506),
    E7("90后", 507),
    E8("网络歌曲", 508),
    E9("KTV", 509),
    E10("经典", 510),
    E11("翻唱", 511),
    E12("吉他", 512),
    E13("钢琴", 513),
    E14("乐器", 514),
    E15("儿童", 515),
    E16("榜单", 516),
    E17("00后", 517);

    SongListType() {
    }

    SongListType(String name, int code) {
        this.name = name;
        this.code = code;
    }

    static public SongListType getSongListType(String name) {
        for (SongListType songListType : SongListType.values()) {
            if (name.equals(songListType.getName())) {
                return songListType;
            }
        }
        return A1;
    }

    String name;

    int code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
