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
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

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


    static void page() {
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
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread();
            thread.start();
            System.out.println(thread.getName());
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void testTransaction() {
        ApplicationContext appCtx = SpringContextUtil.getApplicationContext();
        ISongService songService = (ISongService) appCtx.getBean(ISongService.class);
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
        System.out.println("key:{}, value:{}" + element1.getObjectKey() + element1.getObjectValue());


        CacheUtil cacheUtil = new CacheUtil();
        cacheUtil.put("HelloWorldCache", "key2", "value2");
//        System.out.println(cacheUtil.get("HelloWorldCache", "key2"));

    }


    @org.junit.Test
    public void task() {
        System.out.println(new Date());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(1, 4);
        Future result = forkJoinPool.submit(task);
        try {
            System.out.println("result:" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(new Date());
        int count = 0;
        for (int i = 1; i <= 4; i++) {
            count = i + count;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("count:" + count);
        System.out.println(new Date());
    }

    /*分割任务*/
    class CountTask extends RecursiveTask {

        private static final int THRESHOLD = 2;//阈值

        private int start;
        private int end;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            //如果任务足够小就计算任务
            boolean canCompute = (end - start) <= THRESHOLD;
            if (canCompute) {
                for (int i = start; i <= end; i++) {
                    sum += i;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                //如果任务大于阀值，就分裂成两个子任务计算
                int middle = (start + end) / 2;
                CountTask leftTask = new CountTask(start, middle);
                CountTask rightTask = new CountTask(middle + 1, end);
                //执行子任务
//                leftTask.fork();
//                rightTask.fork();
                invokeAll(leftTask, rightTask);
                //等待子任务执行完，并得到其结果
                int leftResult = (Integer) leftTask.join();
                int rightResult = (Integer) rightTask.join();
                //合并子任务
                sum = leftResult + rightResult;
            }
            return sum;
        }
    }
}
