package com.qf.mapper;


import com.qf.pojo.Order;
import com.qf.pojo.OrderDetail;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zyh
 * @date 2019/9/11 0011 23:08
 * 佛祖保佑
 */
public interface OrderMapper {
    void addorder(Order order);
    void addOrderDetail(OrderDetail detail);
    List<Order> findAllOrder(Integer uid) throws SQLException, InvocationTargetException, IllegalAccessException;
    Order findOrderByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException;
     List<OrderDetail> findDetailByOid(String oid);
     void update(Order order);
}
