package com.qf.web.servlet;


import com.qf.pojo.*;
import com.qf.service.CartService;
import com.qf.service.GoodsServiceImpl;
import com.qf.service.OrderService;
import com.qf.service.UserServiceImpl;
import com.qf.utils.RandomUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zyh
 * @date 2019/9/11 0011 23:09
 * 佛祖保佑
 */
@WebServlet("/orderservlet")
public class OrderServlet extends BaseServlet{
    GoodsServiceImpl goodsService = (GoodsServiceImpl) ContextLoader.getCurrentWebApplicationContext().getBean("goodsServiceImpl");
    OrderService orderService = (OrderService) ContextLoader.getCurrentWebApplicationContext().getBean("orderService");
    CartService cartService  = (CartService) ContextLoader.getCurrentWebApplicationContext().getBean("cartService");
    UserServiceImpl userservice = (UserServiceImpl) ContextLoader.getCurrentWebApplicationContext().getBean("userServiceImpl");
    public String getOrderView(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        User user = ((User) request.getSession().getAttribute("user"));
        if (user==null){
            response.getWriter().write("<script type=text/javascript>alert('你还没有登陆'); location='/myxiaomi/login.jsp';</script>");
            return null;
        }
        List<Cart> cartsById = cartService.findCartsById(user.getId());
        for (Cart cart : cartsById) {
            cart.setUser(user);
        }
        List<Address> address = userservice.findAddressByUid(user.getId());
        request.setAttribute("addList",address);
        request.setAttribute("cart",cartsById);
        return "/order.jsp";
    }
    public String addOrder(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        String aid = request.getParameter("aid");
        List<Cart> cartsById = cartService.findCartsById(user.getId());
        String s = RandomUtils.CreateUUId();
        List<OrderDetail> list = new ArrayList<>();
        BigDecimal sum = new BigDecimal(0+"");
        if (list!=null){
            for (Cart cart : cartsById) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setMoney(cart.getMoney());
                orderDetail.setNum(cart.getNum());
                orderDetail.setOid(s);
                orderDetail.setPid(cart.getGoods().getId());
                Goods goodsById = goodsService.getGoodsById(cart.getGoods().getId());
                orderDetail.setGoods(goodsById);
                sum=sum.add(cart.getMoney());
                list.add(orderDetail);
            }
            Order order = new Order();
            order.setAid(Integer.parseInt(aid));
            order.setId(s);
            order.setMoney(sum);
            order.setStatus("1");
            order.setTime(new Date());
            order.setUid(user.getId());
            orderService.add(order,list);
            order.setList(list);
            Address addressById = userservice.findAddressById(Integer.parseInt(aid));
            order.setAddress(addressById);
            System.out.println(order);
            request.setAttribute("od",order);
            return "/orderDetail.jsp";
        }

        return null;
    }
    public String finAllorder(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "/login.jsp";
        }
        List<Order> allOrder = orderService.findAllOrder(user.getId());
        request.setAttribute("orderList",allOrder);
        return "/orderList.jsp";
    }
    public String getOrderDetail(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "/login.jsp";
        }
        String oid = request.getParameter("oid");
        Order order = orderService.findOrderByOid(oid);
        System.out.println(order);
        request.setAttribute("od",order);
        return "/orderDetail.jsp";
    }
    public String changeStatus(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String oid = request.getParameter("oid");
        orderService.changeOrder(oid);
        response.getWriter().write("1");
        return null;
    }
    public String toBuy(HttpServletRequest request,HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "r:/xiami/login.jsp";
        }
        String goodId = request.getParameter("goodId");
        List<Address> addressByUid = userservice.findAddressByUid(user.getId());
        request.setAttribute("addList",addressByUid);
        Goods goodsById = goodsService.getGoodsById(Integer.parseInt(goodId));
        List<OrderDetail> list = new ArrayList<>();
        OrderDetail o = new OrderDetail();
        o.setGoods(goodsById);
        o.setNum(1);
        o.setMoney(goodsById.getPrice());
        o.setOid(RandomUtils.CreateUUId());
        list.add(o);
        request.setAttribute("cart",list);
        return "/order.jsp";
    }



}
