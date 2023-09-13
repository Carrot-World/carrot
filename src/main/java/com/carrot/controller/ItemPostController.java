package com.carrot.controller;

import com.carrot.domain.ItemPostVO;
import com.carrot.domain.LocationVO;
import com.carrot.service.ItemPostService;
import com.carrot.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ItemPostController {

    @Autowired
    private ItemPostService itemPostService;

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

    @RequestMapping(value = "/page/detail", method = RequestMethod.GET)
    public String detailItem(int id, Model model) {
        model.addAttribute("item", itemPostService.detail(id));
        return "detailItem";
    }

    @RequestMapping("/page/listItem")
    public String listItem(Model model) {
        model.addAttribute("list", itemPostService.select());
        return "listItem";
    }

    @RequestMapping("/page/insertItem")
    public String insertForm() {
        return "insertItem";
    }

    @RequestMapping("/api/item/insert.do")
    public ResponseEntity<Void> insert(ItemPostVO vo,
                                       @RequestParam(value = "images", required = false) List<MultipartFile> imageList) throws IOException {

        if (itemPostService.insert(vo, imageList) == 1) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
