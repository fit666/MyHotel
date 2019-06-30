package com.hero.hotel.controller;

import com.hero.hotel.pojo.Order;
import com.hero.hotel.pojo.User;
import com.hero.hotel.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/crud")
public class CrudController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/showOrder")
    @ResponseBody
    public Order showOrder(HttpSession session){
        //获取用户的id
         User user = (User) session.getAttribute("user");
        Integer userid = user.getId();
        //根据uerid去找到订单相关所有信息
        Order order=orderService.findOrderByUserid(userid);

        System.out.println(order);

        return order;
    }
}
