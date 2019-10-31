package com;

import org.junit.Test;

public class TheadTest {


    @Test
    public void test1() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new Counter().add(1);
                }
            }).start();
        }
    }

    public class Counter {
        protected long count = 0;

        public void add(long value) {
            this.count = this.count + value;

            System.out.println("count：" + this.count);
        }
    }

}
