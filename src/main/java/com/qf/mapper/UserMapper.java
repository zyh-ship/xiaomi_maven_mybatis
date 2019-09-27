package com.qf.mapper;


import com.qf.pojo.Address;
import com.qf.pojo.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zyh
 * et@date 2019/9/10 0010 14:44
 * 佛祖保佑
 */
public interface UserMapper {
     List<User> findAll() throws SQLException;
     User findById(Integer id) throws SQLException;
     void add(User user) throws SQLException;
     void uppdate(User user) throws SQLException;
     void delete(Integer id) throws SQLException;
     User findByUsernameAndPassword(String username, String password) throws SQLException;
     User findUsername(String username) throws SQLException;
     List<Address> findAddressByUid(Integer uid);
     void deleteAddress(int uid, int id);
     void addAddress(Address address);
     void updateAddress(Address address);
     void setLevel(int uid);
     void uppdateDefault(int uid, int id);
     Address findAddressById(Integer id);
}
