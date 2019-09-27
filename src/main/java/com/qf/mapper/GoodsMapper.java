package com.qf.mapper;


import com.qf.pojo.Goods;
import com.qf.pojo.GoodsType;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zyh
 * @date 2019/9/10 0010 21:41
 * 佛祖保佑
 */
public interface GoodsMapper {
    List<Goods> findGoodsByTypeId(Integer typeid, int offset, int pageSize) throws SQLException;
    int findAllCount(int typeid) throws SQLException;
    Goods getGoodsById(Integer id) throws SQLException;
    List<Goods> findGoodsBywhere( int offset, int pageSize, @Param("ch") String ch) throws SQLException;
    int findCount(@Param("chc") String chc);
    GoodsType findGoodTypeByid(Integer id);
}
