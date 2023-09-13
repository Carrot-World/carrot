package com.carrot.controller;

import com.carrot.domain.CategoryVO;
import com.carrot.domain.ItemPostVO;
import com.carrot.domain.LocationVO;
import com.carrot.service.ItemPostService;
import com.carrot.service.LocationService;
import com.carrot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserService userService;

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
        if (userService.isAnonymous()) {
            model.addAttribute("user", userService.getUserInfo());
        }
        model.addAttribute("list", itemPostService.select());
        return "listItem";
    }

    @RequestMapping("/page/insertItem")
    public String insertForm(Model model) {
        model.addAttribute("user", userService.getUserInfo());
        return "insertItem";
    }

    @RequestMapping("/api/item/insert.do")
    public ResponseEntity<Void> insert(ItemPostVO vo,
                                       @RequestParam(value = "images", required = false) List<MultipartFile> imageList) throws IOException {

        System.out.println("itemPostVO" + vo);
        System.out.println("imageList" + imageList);
        if (itemPostService.insert(vo, imageList) == 1) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public String search(CategoryVO categoryVO, LocationVO locationVO) {
        System.out.println("category : " + categoryVO);
        System.out.println();
        return null;
    }
}
