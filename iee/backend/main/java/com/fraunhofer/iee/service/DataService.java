package com.fraunhofer.iee.service;

import com.fraunhofer.iee.dao.DataDao;
import com.fraunhofer.iee.entity.WindData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class DataService {

    @Resource
    private DataDao dao;

    public DataService() throws IOException {
    }

    /** read .csv file in memory
     * @return records
     */
    public Iterable<CSVRecord> readCSV(String fileName) {
        InputStreamReader in;
        Iterable<CSVRecord> records = null;
        ClassPathResource classPathResource = new ClassPathResource("static/2022-02-01+00P1M_.csv");
        try {
            in = new InputStreamReader(classPathResource.getInputStream());
            records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    /** format and encapsulate the records in memory
     * @return list
     */
    public List<WindData> dataList(){
        Iterable<CSVRecord> records = readCSV(null);
        List<WindData> list = new ArrayList<>();
        for(CSVRecord record:records){
            WindData data = new WindData();

            data.setDate(record.get(0).substring(0,10));
            data.setTime(record.get(0).substring(11,19));
            data.setInPower(Float.parseFloat(record.get(1).replace(".","").replace(",",".")));
            data.setOutPower(Float.parseFloat(record.get(2).replace(".","").replace(",",".")));
            data.setSpeed(Float.parseFloat(record.get(3).replace(",",".")));

            list.add(data);
        }
        return list;
    }

    /** batch insert records into DB table (need to optimize!)
     * @return number of records
     */
    public int persist(){
        List<WindData> list = dataList();
        Iterator<WindData> it = list.iterator();

        List<WindData> subList = new ArrayList<>();
        int rows = 0;
        int batch = 10000;
        while (it.hasNext()) {
            rows++;
            subList.add(it.next());
            if (--batch == 0) {
                dao.batchInsert(subList);
                subList.clear();
                batch = 10000;
            }
            if (!it.hasNext()) dao.batchInsert(subList);//insert rest records not up to 10000
        }
        return rows;
    }

    /**
     * fetch data from DB which date between startDate and endDate
     * @return WindData within date range
     */
    public List<WindData> dateRange(String startDate, String endDate){
        return dao.dateRange(startDate, endDate);
    }

    /**************************************** fetch inPower from memory ****************************************/

    public Float[] inPowerArray() throws IOException {
        List<WindData> list = dataList();
        Iterator<WindData> it = list.iterator();
        Float[] powers = new Float[list.size()];
        int i = 0;
        while (it.hasNext()) {
            powers[i] = it.next().getInPower();
            i++;
        }
        return powers;
    }

    public Float[] sortedInPowerArray() throws IOException {
        Float[] a = inPowerArray();
        Arrays.sort(a);
        return a;
    }

    private Float[] a = sortedInPowerArray();

    public Float maxInPower() throws IOException {
        return a[a.length-1];
    }

    public Float minInPower() throws IOException {
        return a[0];
    }

    public Float avgInPower() throws IOException {
        Float sum = 0f;
        for (Float e:a) {
            sum += e;
        }
        return sum/a.length;
    }

    /**************************************** fetch outPower from memory ****************************************/

    public Float[] outPowerArray() throws IOException {
        List<WindData> list = dataList();
        Iterator<WindData> it = list.iterator();
        Float[] powers = new Float[list.size()];
        int i = 0;
        while (it.hasNext()) {
            powers[i] = it.next().getOutPower();
            i++;
        }
        return powers;
    }

    public Float[] sortedOutPowerArray() throws IOException {
        Float[] a = outPowerArray();
        Arrays.sort(a);
        return a;
    }

    private Float[] o = sortedOutPowerArray();

    public Float maxOutPower() throws IOException {
        return o[o.length-1];
    }

    public Float minOutPower() throws IOException {
        return o[0];
    }

    public Float avgOutPower() throws IOException {
        Float sum = 0f;
        for (Float e:o) {
            sum += e;
        }
        return sum/o.length;
    }

    /**************************************** fetch speed from memory ****************************************/

    public Float[] speedArray() throws IOException {
        List<WindData> list = dataList();
        Iterator<WindData> it = list.iterator();
        Float[] speeds = new Float[list.size()];
        int i = 0;
        while (it.hasNext()) {
            speeds[i] = it.next().getSpeed();
            i++;
        }
        return speeds;
    }

    public Float[] sortedSpeedArray() throws IOException {
        Float[] a = speedArray();
        Arrays.sort(a);
        return a;
    }

    private Float[] s = sortedSpeedArray();

    public Float maxSpeed() throws IOException {
        return s[s.length-1];
    }

    public Float minSpeed() throws IOException {
        return s[0];
    }

    public Float avgSpeed() throws IOException {
        Float sum = 0f;
        for (Float e:s) {
            sum += e;
        }
        return sum/s.length;
    }

}
