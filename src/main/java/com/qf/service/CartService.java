package com.qf.service;

import com.qf.mapper.CartMapper;
import com.qf.pojo.Cart;
import com.qf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zyh
 * @date 2019/9/11 0011 10:08
 * 佛祖保佑
 */
@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;
    public List<Cart> findCartsById(Integer id) {
            return cartMapper.findCartsById(id);
    }
    public void addCart(Cart cart){
        Cart cart1 = cartMapper.fingById(cart.getUser().getId(),cart.getGoods().getId());
        if (cart1!=null){
            Integer cnum = cart1.getNum();
            Integer num = cart1.getNum();
                    num+=cnum;
            BigDecimal decimal = cart1.getGoods().getPrice().multiply(new BigDecimal(num + ""));
            cart1.setMoney(decimal);
            cart1.setNum(num);
            cart1.setUser(cart.getUser());
            cartMapper.update(cart1);
        }
        else{
            try {
                cartMapper.AddCart(cart);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void delete(Integer uid, Integer pid) {
        cartMapper.delete(uid,pid);
    }
    public void ReduceNum(User user, Integer pid){
        Cart cart = cartMapper.fingById(user.getId(), pid);
        Integer num = cart.getNum();
        if (num-1>0){
            cart.setNum(num-1);
            cart.setUser(user);
            cart.setMoney(cart.getMoney().subtract(new BigDecimal(cart.getGoods().getPrice()+"")));
            cartMapper.update(cart);
        }
    }
    public void AddNum(User user, Integer pid){
        Cart cart = cartMapper.fingById(user.getId(), pid);
        Integer num = cart.getNum();
            cart.setNum(num+1);
            cart.setUser(user);
            cart.setMoney(cart.getMoney().add(new BigDecimal(cart.getGoods().getPrice()+"")));
        cartMapper.update(cart);
    }
    public void clearCart(Integer uid) {
        cartMapper.clearCart(uid);
    }
    }

