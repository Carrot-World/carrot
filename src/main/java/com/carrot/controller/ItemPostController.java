package com.carrot.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.carrot.domain.HartVO;
import com.carrot.domain.ItemPostVO;
import com.carrot.domain.LocationVO;
import com.carrot.domain.SearchVO;
import com.carrot.domain.UserVO;
import com.carrot.service.ChatService;
import com.carrot.service.HartService;
import com.carrot.service.ItemPostService;
import com.carrot.service.LocationService;
import com.carrot.service.PagingService;
import com.carrot.service.UserService;

@Controller
public class ItemPostController {

    @Autowired
    private ItemPostService itemPostService;

    @Autowired
    private PagingService pagingService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private HartService hartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @Autowired
    SimpMessagingTemplate template;

    @RequestMapping(value = "/page/detail", method = RequestMethod.GET)
    public String detailItem(int id, Model model) {
        if (userService.isAuthenticated()) {
            UserVO user = userService.getUserInfo();
            model.addAttribute("isHart", hartService.isCheck(user, id));
            model.addAttribute("user", user);
        }
        model.addAttribute("item", itemPostService.detail(id));
        return "itemDetail";
    }

    @RequestMapping("/page/itemList")
    public String listItem(SearchVO vo, Model model) {
        if (vo.getPageNo() == 0) {
            UserVO user = userService.getUserInfo();
            model.addAttribute("user", user);
            model.addAttribute("list", itemPostService.search(pagingService.setPaging(userService.setUserLocation())));
            model.addAttribute("loc2List", locationService.loc2Set(new LocationVO(user.getLoc1())));
            model.addAttribute("loc3List", locationService.loc3Set(new LocationVO(user.getLoc1(), user.getLoc2())));
            model.addAttribute("page", pagingService.getPagingInfo(userService.setUserLocation()));
        } else {
            model.addAttribute("list", itemPostService.search(pagingService.setPaging(vo)));
            model.addAttribute("loc2List", locationService.loc2Set(new LocationVO(vo.getLoc1())));
            model.addAttribute("loc3List", locationService.loc3Set(new LocationVO(vo.getLoc1(), vo.getLoc2())));
            model.addAttribute("page", pagingService.getPagingInfo(pagingService.setPaging(vo)));
            model.addAttribute("searchInfo", vo);
        }
        model.addAttribute("loc1List", locationService.loc1Set());
        return "itemList";
    }

    @RequestMapping("/page/itemRegister")
    public String insertForm(Model model) {
        UserVO user = userService.getUserInfo();
        model.addAttribute("user", user);
        model.addAttribute("loc1List", locationService.loc1Set());
        model.addAttribute("loc2List", locationService.loc2Set(new LocationVO(user.getLoc1())));
        model.addAttribute("loc3List", locationService.loc3Set(new LocationVO(user.getLoc1(), user.getLoc2())));
        return "itemRegister";
    }

    @ResponseBody
    @RequestMapping("/api/item/insert")
    public String insert(ItemPostVO vo,
                         @RequestParam(value = "images", required = false) List<MultipartFile> imageList) throws IOException {

        if (itemPostService.insert(userService.getUserInfo(), vo, imageList) == 1) {
            return "/page/detail?id=" + vo.getId();
        } else {
            return "/page/itemRegister#";
        }
    }

    @ResponseBody
    @RequestMapping("/api/item/hartPlus")
    public String hartPlus(HartVO vo) {
        vo.setUser_id(userService.getUserInfo().getId());
        if (hartService.plus(vo) == 1) {
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

    @ResponseBody
    @RequestMapping("/api/item/getBuyer")
    public List<String> getBuyer(ItemPostVO vo) {
        return chatService.getBuyerList(vo.getId());
    }

    @RequestMapping("/api/item/complete")
    public String saleComplete() {

        return null;
    }

    @RequestMapping("/api/item/delete")
    public String delete(ItemPostVO vo) {
        if (!userService.getUserInfo().getId().equals(vo.getWriter())) {
            return "accessDenied";
        }
        if (itemPostService.delete(vo) == 1) {
            return "redirect:/page/mypageSell";
        } else {
            return "accessDenied";
        }
    }

    @RequestMapping("/page/modal/complete/{id}")
    public String completeModal(@PathVariable String id, Model model) {
        int itemId = Integer.parseInt(id);
        List<String> buyerList = chatService.getBuyerList(itemId);
        model.addAttribute("buyerList", buyerList);
        model.addAttribute("postId", itemId);
        return "completeModal";
    }

    @ResponseBody
    @RequestMapping("/api/item/trade/{postId}/{name}")
    public void createTrade(@PathVariable int postId, @PathVariable String name) {
        UserVO user = userService.getUserInfo();
        itemPostService.complete(postId);
        itemPostService.createTrade(postId, name);

        List<ChatRoomVO> chatRooms = chatService.getChatRoomByPostId(postId);
        if (chatRooms != null) {
            for (ChatRoomVO room : chatRooms) {
                ChatMessageVO message = new ChatMessageVO();
                message.setContent("물품이 판매되었습니다.");
                message.setWriterName(user.getNickname());
                template.convertAndSend("/socket/message/"+room.getId(),
                        chatService.sendMessage(message, room.getId(), user.getId()));
            }
        }
    }
}
