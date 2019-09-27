package com.qf.service;


import com.qf.mapper.GoodsTypeMapper;
import com.qf.pojo.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zyh
 * @date 2019/9/10 0010 21:27
 * 佛祖保佑
 */
@Service
public class GoosTypeService {
    @Autowired
            private GoodsTypeMapper goodsTypeMapper;
    GoodsServiceImpl service = new GoodsServiceImpl();
    public List<GoodsType> findAllbyLevel(Integer i){
        try {
            return goodsTypeMapper.findAllbyLevel(i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public GoodsType findById(Integer id){
        try {
            return goodsTypeMapper.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
