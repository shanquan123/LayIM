package com.pers.yefei.test;

import com.pers.yefei.LayIM.service.IndexService;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class IndexServceTest {


    private IndexService indexService;

    @Before
    public void setUp(){
        indexService = new IndexService();
    }

    @Test
    public void indexTest(){

        String rs = indexService.index();
        Assert.assertNotNull(rs);
    }

    @Test
    public void indexTest2(){
        String rs = indexService.index2();
        Assert.assertNotNull(rs);
    }

    @Test
    public void plusTest(){
        int rs = indexService.plus(1,3);
        Assert.assertTrue(rs == 4);
    }
}
