package com.fraunhofer.iee.dao;

import com.fraunhofer.iee.entity.WindData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataDao {

    int batchInsert(List<WindData> list);

    List<WindData> dateRange(String startDate, String endDate);

    /****************************** fetch inPower data form DB  ******************************/

    Float maxInPower(String startDate, String endDate);

    Float avgInPower(String startDate, String endDate);

    Float minInPower(String startDate, String endDate);

    /** omit rest methods of outPower, speed **/
}
