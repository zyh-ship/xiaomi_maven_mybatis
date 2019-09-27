package com.qf.mapper;


import com.qf.pojo.GoodsType;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zyh
 * @date 2019/9/10 0010 21:03
 * 佛祖保佑
 */
public interface GoodsTypeMapper {
    List<GoodsType> findAllbyLevel(Integer integer) throws SQLException;
    GoodsType findById(Integer id) throws SQLException;
}
