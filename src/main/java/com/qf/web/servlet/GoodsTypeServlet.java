package com.qf.web.servlet;

import com.alibaba.fastjson.JSON;
import com.qf.pojo.GoodsType;
import com.qf.service.GoosTypeService;

import org.springframework.web.context.ContextLoader;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zyh
 * @date 2019/9/10 0010 21:30
 * 佛祖保佑
 */
@WebServlet("/goodstypeservlet")
public class GoodsTypeServlet extends BaseServlet {
    GoosTypeService service = (GoosTypeService) ContextLoader.getCurrentWebApplicationContext().getBean("goosTypeService");
    public String goodstypelist(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json;charset=utf-8");
        List<GoodsType> all = service.findAllbyLevel(1);
        String s = JSON.toJSONString(all);
        try {
            response.getWriter().write(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
