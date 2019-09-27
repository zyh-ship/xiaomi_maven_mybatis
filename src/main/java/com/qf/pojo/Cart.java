package com.qf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author zyh
 * @date 2019/9/10 0010 14:37
 * 佛祖保佑
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private int id;
    private User user;
    private Integer num;
    private BigDecimal money;
    private Goods goods;

}