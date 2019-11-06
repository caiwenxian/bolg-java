package com;

import exception.SerException;
import model.po.common.Page;
import model.po.common.PaginationPO;
import model.po.music.SongInfoPO;
import model.vo.mq.MessageVO;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.context.ApplicationContext;
import service.music.ISongService;
import utils.CacheUtil;
import utils.SpringContextUtil;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;
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

        testTransaction();
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

    @org.junit.Test
    public void test1() {
        String str1 = "aaa";
        String str2 = new String("aaa");
        System.out.println("result1:" + str1 == str2);
        System.out.println("result2:" + str1.equals(str2));
    }

    @org.junit.Test
    public void test2() throws IOException {
        FileWriter fileWriter = new FileWriter("com/test.txt", true);
        fileWriter.write("hello");
        fileWriter.close();

        FileReader fileReader = new FileReader("com/test.txt");
        /*char[] buf = new char[6];
        int num = 0;
        while ((num = fileReader.read(buf)) != -1) {
            String str = new String(buf, 0, num);
            System.out.println(str);
        }*/
        /*int str;
        while ((str = fileReader.read()) != -1) {
            System.out.println((char)str);
        }*/


        FileWriter fileWriter1 = new FileWriter("com/test1.txt");
        StringBuffer stringBuffer = new StringBuffer();
        int str;
        while ((str = fileReader.read()) != -1) {
            stringBuffer.append((char) str);
        }

        fileWriter1.append(stringBuffer);
        fileWriter1.close();

    }

    @org.junit.Test
    public void reflect() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        Proxy proxy = new Proxy();
        Method method = proxy.getClass().getDeclaredMethod("run");
        method.invoke(proxy);

        Method method1 = Proxy.class.getDeclaredMethod("run");
        method1.invoke(proxy);

        Method[] method2 = Proxy.class.getDeclaredMethods();
        for (Method method3 : method2) {
            System.out.println(method3.getName());
        }

        Constructor[] method4 = Proxy.class.getConstructors();
        System.out.println(method4[0].getName());
//        Proxy object = (Proxy) method4[0].newInstance();
//        object.run();

        Class aClass = Class.forName(Proxy.class.getName());
        Method method3 = aClass.getDeclaredMethod("run");
        method3.invoke(new Proxy());


        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1 == singleton2);

        Singleton2 singleton3 = Singleton2.getInstance();
        Singleton2 singleton4 = Singleton2.getInstance();
        System.out.println(singleton3 == singleton4);

        Proxy proxy1 = new Proxy();
        Proxy proxy2 = new Proxy();
        System.out.println(proxy1 == proxy2);


    }

    class Proxy {

        public Proxy() {
            System.out.println("this is proxy");
        }

        void run() {
            System.out.println("this is run method");
        }

        void start() {
            System.out.println("this is start method");
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }


    }

    public static class Singleton {
        private static Singleton instance;

        public Singleton() {
        }

        public static synchronized Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }

    public static class Singleton2 {
        private static Singleton2 instance = new Singleton2();

        public Singleton2() {
        }

        public static Singleton2 getInstance() {
            return instance;
        }
    }

    @org.junit.Test
    public void test3() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/blog?serverTimezone=GMT%2B8";
        String name = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, name, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from t_article");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("title"));
        }
    }

    @org.junit.Test
    public void test4() throws InterruptedException {
        System.out.println("inter");
        int i = 0;
        while (i < 10) {
            Thread.sleep(1000);
            i++;
        }
        System.out.println("inter2");
    }


    @org.junit.Test
    public void test8() {
        MessageVO vo = new MessageVO();
        String voMethodName = "setStatus";
        try {
            MessageVO.class.getMethod("setStatus", String.class).invoke(vo, new Object[] {3});
            System.out.println(vo.getStatus());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
