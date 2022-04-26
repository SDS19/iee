package com.fraunhofer.iee;

import com.fraunhofer.iee.entity.WindData;
import com.fraunhofer.iee.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
public class DataServiceTest {

    @Autowired
    public DataService service;

    @Test
    void readCSV() {
        List<WindData> list = service.dataList();
        Iterator<WindData> it = list.iterator();
        while (it.hasNext()) {
            WindData data = it.next();
            System.out.println(data.getInPower()+", "+data.getSpeed());
        }
    }

    /**************************************** inPower ****************************************/

    @Test
    void inPowerArray() throws IOException {
        Float[] inPowers = service.inPowerArray();
        for (Float inPower:inPowers) {
            System.out.println(inPower);
        }
    }

    @Test
    void maxInPower() throws IOException {
        Float max = service.maxInPower();
        System.out.println(max);//4671.812
    }

    @Test
    void minInPower() throws IOException {
        Float min = service.minInPower();
        System.out.println(min);//0
    }

    @Test
    void avgInPower() throws IOException {
        Float avg = service.avgInPower();
        System.out.println(avg);//1877.6553
    }

    /**************************************** outPower ****************************************/

    @Test
    void outPowerArray() throws IOException {
        Float[] outPowers = service.sortedOutPowerArray();
        for (Float outPower:outPowers) {
            System.out.println(outPower);
        }
    }

    @Test
    void maxOutPower() throws IOException {
        Float max = service.maxOutPower();
        System.out.println(max);//4600.0
    }

    @Test
    void minOutPower() throws IOException {
        Float min = service.minOutPower();
        System.out.println(min);//0.0
    }

    @Test
    void avgOutPower() throws IOException {
        Float avg = service.avgOutPower();
        System.out.println(avg);//1868.505
    }

    /**************************************** speed ****************************************/

    @Test
    void speedArray() throws IOException {
        Float[] speeds = service.sortedSpeedArray();
        for (Float speed:speeds) {
            System.out.println(speed);
        }
    }

    @Test
    void maxSpeed() throws IOException {
        Float max = service.maxSpeed();
        System.out.println(max);//27.167
    }

    @Test
    void minSpeed() throws IOException {
        Float min = service.minSpeed();
        System.out.println(min);//0.003
    }

    @Test
    void avgSpeed() throws IOException {
        Float avg = service.avgSpeed();
        System.out.println(avg);//9.232816
    }
}
