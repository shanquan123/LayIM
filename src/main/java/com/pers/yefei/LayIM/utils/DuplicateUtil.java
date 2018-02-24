package com.pers.yefei.LayIM.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateUtil<T> {

    public static <T> void unique(List<T> list){
        Set<T> set = new HashSet<>();
        for (T item : list){
            set.add(item);
        }
        list.clear();
        list.addAll((List<T>)Arrays.asList(set.toArray()));
    }
}
