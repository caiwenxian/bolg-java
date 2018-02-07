package controller.test;

import model.po.common.Page;
import model.po.common.PaginationPO;
import model.po.music.SongInfoPO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-27 11:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Test {


    void test() {
        System.out.println(LocalDateTime.parse("1517023762"));
    }

    public static void main(String[] args) {
//        System.out.println(LocalDateTime.parse("1517023762"));

//        SongInfoPO songInfoPO = new SongInfoPO();
//        songInfoPO.setSongId("111");
//        System.out.println(songInfoPO.toString());
//        List<Object> list = new ArrayList<Object>();
//        list.add(songInfoPO);
//        SongInfoPO songInfoPO1 = (SongInfoPO)list.get(0);
//        System.out.println(songInfoPO1.getSongId());


        page();
    }


    static void page () {
        int pageIndex = 1;
        int pageSize = 10;

        final PaginationPO paginationPO = new PaginationPO(new Page() {
            @Override
            public int init() {
                return 10;
            }

            @Override
            public List initData(int pageIndex, int pageSize) {
                return null;
            }


        });
    }

}
