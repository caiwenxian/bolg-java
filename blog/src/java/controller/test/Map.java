package controller.test;

import org.junit.Test;

import java.util.HashMap;

public class Map {

    @Test
    public void test1 () {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        for (String key : map.keySet()) {
            System.out.println("key:" + key + "; value:" + map.get(key));
        }
        for (java.util.Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key:" + entry.getKey().hashCode() + "; value:" + entry.getValue());
        }
    }
}
