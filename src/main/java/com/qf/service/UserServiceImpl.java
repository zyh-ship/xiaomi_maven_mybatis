package com.qf.service;

import com.qf.mapper.UserMapper;
import com.qf.pojo.Address;
import com.qf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zyh
 * @date 2019/9/10 0010 15:44
 * 佛祖保佑
 */
@Service
public class UserServiceImpl{
    @Autowired
            private UserMapper userMapper;
    public void register(User user) {
        try {
            userMapper.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     
    public User checkUsername(String username) {
        try {
            return userMapper.findUsername(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

     
    public User login(String username, String password) {
        try {
            User user = userMapper.findByUsernameAndPassword(username, password);
            return user;
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }

    }

     
    public List<Address> findAddressByUid(Integer uid) {
        return userMapper.findAddressByUid(uid);
    }

     
    public void deleteAddress(int uid, int id) {
        userMapper.deleteAddress(uid,id);
    }

     
    public void addAddress(Address address) {
        userMapper.addAddress(address);
    }

     
    public void updateAddress(Address address) {
        userMapper.updateAddress(address);
    }

     
    public void setDefault(int i,int uid) {
        userMapper.setLevel(uid);
        userMapper.uppdateDefault(uid,i);
    }

     
    public Address findAddressById(Integer id) {
        return userMapper.findAddressById(id);
    }
}
