package com.qf.Filter;

import com.qf.mapper.GoodsMapper;
import com.qf.mapper.UserMapper;
import com.qf.pojo.Goods;
import com.qf.pojo.User;
import com.qf.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zyh
 * @date 2019/9/26 0026 9:32
 * 佛祖保佑
 */
public class Demo1 {
    @Test
    public void t1() throws SQLException {
        ApplicationContext ap = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserServiceImpl userMapper = ap.getBean("userServiceImpl", UserServiceImpl.class);
        System.out.println(userMapper.findAddressByUid(1));
    }
    @Test
    public void t2() throws SQLException {
        ApplicationContext ap = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        GoodsMapper userMapper = ap.getBean("goodsMapper", GoodsMapper.class);
        System.out.println(userMapper.getGoodsById(2));
    }
}
