package com.fraunhofer.iee;

import com.fraunhofer.iee.dao.DataDao;
import com.fraunhofer.iee.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class DataDaoTest {

    @Resource
    DataService service;
    @Resource
    DataDao dao;

    @Test
    void maxInPower(){
        System.out.println(dao.maxInPower("2022-02-01","2022-02-28"));//4671.812
    }

    @Test
    void avgInPower(){
        System.out.println(dao.avgInPower("2022-02-01","2022-02-28"));//1877.362
    }

    @Test
    void minInPower(){
        System.out.println(dao.minInPower("2022-02-01","2022-02-28"));//0
    }

    @Test
    void batchInsert(){
        System.out.println(service.persist());
    }

    @Test
    void list(){
        System.out.println(service.dataList().size());
    }
}
