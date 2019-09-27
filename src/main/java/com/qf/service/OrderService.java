package com.qf.service;


import com.qf.mapper.CartMapper;
import com.qf.mapper.OrderMapper;
import com.qf.pojo.Order;
import com.qf.pojo.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zyh
 * @date 2019/9/11 0011 23:09
 * 佛祖保佑
 */
@Service
public class OrderService {
    @Autowired
            private OrderMapper orderMapper;
    @Autowired
            private CartMapper cartMapper;
    public void  add(Order order, List<OrderDetail> list) {
        orderMapper.addorder(order);
            for (OrderDetail detail : list) {
                orderMapper.addOrderDetail(detail);
            }
            cartMapper.clearCart(order.getUid());
    }
    public List<Order> findAllOrder(Integer uid){
        try {

            return orderMapper.findAllOrder(uid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Order findOrderByOid(String oid) {
        Order order = null;
        try {
            order = orderMapper.findOrderByOid(oid);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return order;
    }
    public void changeOrder(String oid){
        try {
            Order orderByOid = orderMapper.findOrderByOid(oid);
            orderByOid.setStatus("4");
            orderMapper.update(orderByOid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
