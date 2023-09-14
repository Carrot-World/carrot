package com.carrot.controller;

import com.carrot.domain.ItemPostVO;
import com.carrot.domain.LocationVO;
import com.carrot.domain.SearchVO;
import com.carrot.domain.UserVO;
import com.carrot.service.ItemPostService;
import com.carrot.service.LocationService;
import com.carrot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/page/detail", method = RequestMethod.GET)
    public String detailItem(int id, Model model) {
        model.addAttribute("item", itemPostService.detail(id));
        return "detailItem";
    }

    @RequestMapping("/page/listItem")
    public String listItem(Model model) {
        if (userService.isAuthenticated()) {
            UserVO user = userService.getUserInfo();
            model.addAttribute("user", user);
            model.addAttribute("list", itemPostService.search(userService.setUserLocation()));
            model.addAttribute("loc1List", locationService.loc1Set());
            model.addAttribute("loc2List", locationService.loc2Set(new LocationVO(user.getLoc1())));
            model.addAttribute("loc3List", locationService.loc3Set(new LocationVO(user.getLoc1(), user.getLoc2())));
            return "listItem";
        }
        model.addAttribute("loc1List", locationService.loc1Set());
        model.addAttribute("list", itemPostService.search(new SearchVO()));
        return "listItem";
    }

    @RequestMapping("/page/insertItem")
    public String insertForm(Model model) {
        UserVO user = userService.getUserInfo();
        model.addAttribute("user", user);
        model.addAttribute("loc1List", locationService.loc1Set());
        model.addAttribute("loc2List", locationService.loc2Set(new LocationVO(user.getLoc1())));
        model.addAttribute("loc3List", locationService.loc3Set(new LocationVO(user.getLoc1(), user.getLoc2())));
        return "insertItem";
    }

    @RequestMapping("/api/item/insert")
    public ResponseEntity<Void> insert(ItemPostVO vo,
                                       @RequestParam(value = "images", required = false) List<MultipartFile> imageList) throws IOException {

        if (itemPostService.insert(userService.getUserInfo(), vo, imageList) == 1) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/api/item/search")
    public String search(SearchVO vo, Model model) {
        model.addAttribute("list", itemPostService.search(vo));
        return "searchResult";
    }
}
