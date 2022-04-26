package com.fraunhofer.iee.controller;

import com.fraunhofer.iee.entity.WindData;
import com.fraunhofer.iee.service.DataService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DataController {

    @Resource
    private DataService service;

    @GetMapping("/date")
    @ResponseBody
    public List<WindData> day(@RequestParam String startDate, @RequestParam String endDate){
        return service.dateRange(startDate, endDate);
    }

}
