package com.qf.web.servlet;


import com.qf.Final1.CommonFinal;
import com.qf.pojo.Goods;
import com.qf.pojo.PageBean;
import com.qf.service.GoodsServiceImpl;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.Introspector;

/**
 * @author zyh
 * @date 2019/9/10 0010 21:50
 * 佛祖保佑
 */
@WebServlet(name = "GoodsServlet",value = "/goodsservlet")
public class GoodsServlet extends BaseServlet {
    GoodsServiceImpl service = (GoodsServiceImpl) ContextLoader.getCurrentWebApplicationContext().getBean("goodsServiceImpl");
    public String  getGoodsListByTypeId(HttpServletRequest request, HttpServletResponse response){

        int typeId = Integer.parseInt(request.getParameter("typeId"));
        String choosing = "typeid="+typeId;
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        int ps = getPs(pageSize);
        int num = getNum(pageNum);
        PageBean<Goods> pageBean = service.findGoodsBywhere(num, ps, choosing);
        request.setAttribute("pageBean",pageBean);
        request.setAttribute("typeId",typeId);
        return "/goodsList.jsp";
    }
    public int getNum(String pageNum){
        int p = CommonFinal.PAGE_NUM;
        if (pageNum!=null){
            int pn = Integer.parseInt(pageNum);
            if (pn>0){
                p =pn;
            }
        }
        return p;
    }
public int getPs(String PageSize){
        int ps = CommonFinal.PAGE_SIZE;
        if (PageSize!=null && Integer.parseInt(PageSize)>0){
            ps = Integer.parseInt(PageSize);
        }
        return ps;
}
    public String  getGoodsById(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Goods goodsById = service.getGoodsById(Integer.parseInt(id));
        request.setAttribute("goods",goodsById);
        return "/goodsDetail.jsp";

    }
    public String  search1(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String condition = " name like '%"+name+"%'";
        PageBean<Goods> goodsBywhere = service.findGoodsBywhere(1, 4, condition);
        request.setAttribute("pageBean",goodsBywhere);
        return "/goodsList.jsp";

    }
}
//spring做的事项目管理
//管理手段：
// ioc：
// aop：由切入点和额外功能组成。
//mybatis是一个开源的orm框架技术
// 多例不会虽随着工厂的创建而创建销毁而销毁，而单例相反。
//clone初始时候用
//push第二次用
