package controller.test;

import exception.SerException;
import model.po.common.Page;
import model.po.common.PaginationPO;
import model.po.music.SongInfoPO;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.springframework.context.ApplicationContext;
import service.music.ISongService;
import utils.CacheUtil;
import utils.SpringContextUtil;

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
//
//        System.out.println(Calendar.getInstance().getTimeInMillis() +
//        " " + RandomUtil.getUid());
//        page();
//        System.out.println(System.currentTimeMillis());
//
//        threadTest();

//        testTransaction();
//        cache();
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


    public class CheckThreadJVM {

        /**
         * 获取根线程组
         *
         * @return
         */
        public ThreadGroup getRootThreadGroup() {
            ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
            while (true) {
                if (rootGroup.getParent() != null) {
                    rootGroup = rootGroup.getParent();
                } else {
                    break;
                }
            }
            return rootGroup;
        }

        /**
         * 获取线程组中的线程名
         *
         * @param group
         * @return
         */
        public List<String> getThreadsName(ThreadGroup group) {
            List<String> threadList = new ArrayList<String>();
            Thread[] threads = new Thread[group.activeCount()];
            int count = group.enumerate(threads, false);
            for (int i = 0; i < count; i++) {
                threadList.add(group.getName() + "线程组: " + threads[i].getName());
            }
            return threadList;
        }

        /**
         * 获取根数组下的子数组
         *
         * @param group
         * @return
         */
        public List<String> getThreadGroup(ThreadGroup group) {
            List<String> threadList = getThreadsName(group);
            ThreadGroup[] threads = new ThreadGroup[group.activeGroupCount()];
            int count = group.enumerate(threads, false);
            for (int i = 0; i < count; i++) {
                threadList.addAll(getThreadsName(threads[i]));
            }
            return threadList;

        }

        public void main(String[] args) {
            for (String string : getThreadGroup(getRootThreadGroup())) {
                System.out.println(string);
            }
        }

    }


    static void threadTest() {
        for (int i = 0; i < 100; i ++) {
            Thread thread = new Thread();
            thread.start();
            System.out.println(thread.getName());
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void testTransaction() {
        ApplicationContext appCtx = SpringContextUtil.getApplicationContext();
        ISongService songService = (ISongService)appCtx.getBean(ISongService.class);
        try {
            songService.update(new SongInfoPO("28196001", null, null, null, null, null, null));
        } catch (SerException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void cache() {
        CacheManager manager = CacheManager.newInstance("src/resources/spring-ehcache.xml");
        Cache cache = manager.getCache("HelloWorldCache");
        cache.put(new Element("key1", "value1"));
        Element element1 = cache.get("key1");
        System.out.println("key:{}, value:{}" + element1.getObjectKey() +  element1.getObjectValue());


        CacheUtil cacheUtil = new CacheUtil();
        cacheUtil.put("HelloWorldCache", "key2", "value2");
//        System.out.println(cacheUtil.get("HelloWorldCache", "key2"));

    }






}
