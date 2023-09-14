package com.carrot.controller;

import com.carrot.domain.LocationVO;
import com.carrot.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LocationController {

    @Autowired
    private LocationService locationService;

    @ResponseBody
    @RequestMapping("/api/loc/get2")
    public List<String> loc2Set(LocationVO vo) {
        return locationService.loc2Set(vo);
    }

    @ResponseBody
    @RequestMapping("/api/loc/get3")
    public List<String> loc3Set(LocationVO vo) {
        return locationService.loc3Set(vo);
    }
}
