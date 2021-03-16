package com;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName StreamTest
 * @Description TODO
 * @Author cwx
 * @Date 2021/3/16 10:49
 * @Version 1.0
 **/
@Slf4j
public class StreamTest {

    @Data
    @Builder
    public static class User {
        private int id;
        private String name;
    }

    @Test
    public void test1() {
        List<User> users = new ArrayList<>();
        users.add(User.builder().id(1).name("A-P").build());
        users.add(User.builder().id(2).name("B-P").build());
        users.add(User.builder().id(3).name("C-P").build());

        Stream<String> stringStream = Stream.of("A", "B", "C");
        log.info(stringStream.toString());
        stringStream.forEach(v -> log.info(v.toString()));
        log.info("=================");

        stringStream = Stream.of("A", "B", "C");
        List<String> collect = stringStream.filter(v -> v == "A").collect(Collectors.toList());
        collect.forEach(v -> log.info(v.toString()));
        log.info("=================");

        stringStream = Stream.of("A", "B", "C", "C");
        collect = stringStream.distinct().collect(Collectors.toList());
        collect.forEach(v -> log.info(v.toString()));
        log.info("=================");

        stringStream = Stream.of("A", "B", "C", "C");
        collect = stringStream.limit(2).collect(Collectors.toList());
        collect.forEach(v -> log.info(v.toString()));
        log.info("=================");

        stringStream = Stream.of("A", "B", "C", "C");
        collect = stringStream.skip(2).collect(Collectors.toList());
        collect.forEach(v -> log.info(v.toString()));
        log.info("=================");

        stringStream = Stream.of("1", "2", "3", "4");
        List<Integer> collect1 = stringStream.map(v -> Integer.valueOf(v)).collect(Collectors.toList());
        collect1.forEach(v -> log.info(v.toString()));
        log.info("=================");

        stringStream = Stream.of("1", "2", "3", "4");
        long count = collect1.stream().filter(v -> v > 2).count();
        log.info("count: {}", count);
        log.info("=================");

        stringStream = Stream.of("1", "2", "3", "4");
        boolean b = stringStream.allMatch(v -> v == "2");
        log.info("count: {}", b);
        log.info("=================");

        stringStream = Stream.of("1", "2", "3", "4");
        b = stringStream.anyMatch(v -> v == "2");
        log.info("count: {}", b);
        log.info("=================");

        stringStream = Stream.of("1", "2", "3", "4");
        b = stringStream.noneMatch(v -> v == "2");
        log.info("count: {}", b);
        log.info("=================");

        stringStream = Stream.of("1", "2", "3", "4");
        String a = stringStream.findFirst().orElse("0");
        log.info("first: {}", a);
        log.info("=================");

        stringStream = Stream.of("1", "2", "3", "4", "55");
        collect = stringStream.collect(Collectors.toList());
        collect.removeIf(v -> v.length() > 1);
        collect.forEach( v -> log.info(v.toString()));
        log.info("=================");
        
        Stream<Integer> stringStream2 = Stream.of(1, 2, 3, 4, 55);
        List<Integer> collect2 = stringStream2.collect(Collectors.toList());
        collect2.sort((v1, v2) -> v2 - v1);
        collect2.forEach( v -> log.info(v.toString()));
        log.info("=================");

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("a", "A");
        map.put("b", "B");
        map.put("c", "C");
        map.forEach((v1, v2) -> {
            log.info("{}=={}", v1, v2);
        });
        log.info(map.getOrDefault("d", "D"));
        log.info("=================");

        stringStream2 = Stream.of(1, 2, 9, 4, 55);
        collect2 = stringStream2.sorted((v1, v2) -> v2 - v1).collect(Collectors.toList());
        collect2.forEach( v -> log.info(v.toString()));
        log.info("=================");

        stringStream2 = Stream.of(1, 2, 9, 4, 55);
        Optional<Integer> reduce = stringStream2.reduce((v1, v2) -> v1 - v2 > 0 ? v1 : v2);
        log.info("reduce: {}", reduce.get());
        log.info("=================");

        stringStream2 = Stream.of(1, 2, 9, 4, 55);
        Map<Integer, Integer> collect3 = stringStream2.collect(Collectors.toMap(Function.identity(), Integer::intValue));
        collect3.forEach((v1, v2) -> {
            log.info("{}=={}", v1, v2);
        });
        Map<Integer, User> collect4 = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        collect4.forEach((v1, v2) -> {
            log.info("{}=={}", v1, v2.toString());
        });
        log.info("=================");

        Map<Boolean, List<User>> collect5 = users.stream().collect(Collectors.partitioningBy(v -> v.id > 1));
        collect5.forEach((v1, v2) -> {
            log.info("{}=={}", v1, v2.toString());
        });
        log.info("=================");

        Map<Integer, List<User>> collect6 = users.stream().collect(Collectors.groupingBy(User::getId));
        collect6.forEach((v1, v2) -> {
            log.info("{}=={}", v1, v2.toString());
        });
        String join = String.join("", collect);
        log.info("join: {}", join);
        List<String> collect8 = new ArrayList<>();
        String collect7 = collect.stream().collect(Collectors.joining());
        log.info(collect7);

        List<String> list = Lists.newArrayList("a", "b", "c", "a", "e", "f");
        String result = list.stream().collect(Collectors.joining());
        log.info("=================");
    }


}
