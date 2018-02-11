package com.pers.yefei.LayIM.service;

import org.springframework.stereotype.Service;

@Service
public class IndexService {
    private static final String index1 = "this is indexService -> indexMethod";
    private static final String index2 = "this is indexService -> indexMethod 2";

    public String index(){
        return index1;
    }

    public String index2(){
        return index2;
    }

    public int plus(int a, int b){return a+b;}
}
