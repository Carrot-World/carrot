package com.carrot.controller;

import com.carrot.domain.*;
import com.carrot.service.HartService;
import com.carrot.service.ItemPostService;
import com.carrot.service.LocationService;
import com.carrot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class ItemPostController {

    @Autowired
    private ItemPostService itemPostService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private HartService hartService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/page/detail", method = RequestMethod.GET)
    public String detailItem(int id, Model model) {
        if (userService.isAuthenticated()) {
            UserVO user = userService.getUserInfo();
            model.addAttribute("isHart", hartService.isCheck(user, id));
            model.addAttribute("user", user);
        }
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
    public String insertForm(String itemId, Model model) {
        if (itemId != null) {
            model.addAttribute("item", itemPostService.detail(Integer.parseInt(itemId)));
        }
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
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping("/api/item/search")
    public String search(SearchVO vo, Model model) {
        model.addAttribute("list", itemPostService.search(vo));
        return "searchResult";
    }

    @ResponseBody
    @RequestMapping("/api/item/hartPlus")
    public String hartPlus(HartVO vo) {
        if (!userService.isAuthenticated()) {
            return "/page/login";
        }
        vo.setUser_id(userService.getUserInfo().getId());
        if (hartService.plus(vo) == 2) {
            return "/page/detail?id=" + vo.getItem_post_id();
        } else {
            return "accessDenied";
        }
    }

    @ResponseBody
    @RequestMapping("/api/item/hartMinus")
    public String hartMinus(HartVO vo) {
        vo.setUser_id(userService.getUserInfo().getId());
        if (hartService.minus(vo) > 0) {
            return "/page/detail?id=" + vo.getItem_post_id();
        } else {
            return "accessDenied";
        }
    }

    @RequestMapping("/page/updateForm")
    public String updateForm(int item_id, String user_id, RedirectAttributes re) {
        if (user_id.equals(userService.getUserInfo().getId())) {
            re.addAttribute("itemId", String.valueOf(item_id));
            return "redirect:/page/insertItem";
        } else {
            return "accessDenied";
        }
    }

    @ResponseBody
    @RequestMapping("/api/item/update")
    public String update(ItemPostVO vo,
                         @RequestParam(value = "images", required = false) List<MultipartFile> imageList) throws IOException {

        if (itemPostService.update(vo, imageList) == 1) {
            return "/page/detail?id=" + vo.getId();
        } else {
            return "accessDenied";
        }
    }
}
