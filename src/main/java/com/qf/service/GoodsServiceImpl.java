package com.qf.service;


import com.qf.mapper.GoodsMapper;
import com.qf.pojo.Goods;
import com.qf.pojo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zyh
 * @date 2019/9/10 0010 21:46
 * 佛祖保佑
 */
@Service
public class GoodsServiceImpl {
    @Autowired
     private GoodsMapper goodsMapper;
    public List<Goods> findGoodsByTypeId(Integer typeid, int pageNum, int pageSize){
        int offset = (pageNum-1)*pageSize;
        try {
            return goodsMapper.findGoodsByTypeId(typeid,offset,pageSize);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int findAllCount(int typeid){
        try {
            return goodsMapper.findAllCount(typeid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Goods getGoodsById(Integer id){
        try {
            return goodsMapper.getGoodsById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PageBean<Goods> findGoodsBywhere(int PageNum, int pageSize , String choosing) {

        int offset = (PageNum-1)*pageSize;
        try {
            List<Goods> goodsBywhere = goodsMapper.findGoodsBywhere(offset, pageSize, choosing);
            int count = goodsMapper.findCount(choosing);

            PageBean<Goods> pageBean = new PageBean<>(PageNum,count,pageSize,goodsBywhere);
            return pageBean;
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }

    }
}

