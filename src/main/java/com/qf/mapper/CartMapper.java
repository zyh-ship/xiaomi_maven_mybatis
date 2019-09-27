package com.qf.mapper;


import com.qf.pojo.Cart;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zyh
 * @date 2019/9/11 0011 10:07
 * 佛祖保佑
 */
public interface CartMapper {
    void AddCart(Cart cart) throws SQLException;
    Cart fingById(Integer uid, Integer pid);
    void update(Cart cart);
    void delete(Integer uid, Integer pid);
    List<Cart> findCartsById(Integer id);
    void clearCart(Integer uid);
}
