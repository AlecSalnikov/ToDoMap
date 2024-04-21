package com.alec.spring.rest.rest_api.model;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UniqueId {
    static long id;

    public static long getId(Map<Long,Task> taskMap) {
        AtomicInteger count = new AtomicInteger(taskMap.size());
        id = count.getAndIncrement();
        while(taskMap.containsKey(id)){
        id ++;

    }


return id;}}



