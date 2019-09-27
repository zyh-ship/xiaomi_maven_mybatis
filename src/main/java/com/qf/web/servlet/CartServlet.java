package com.qf.web.servlet;
import com.qf.pojo.Cart;
import com.qf.pojo.Goods;
import com.qf.pojo.User;
import com.qf.service.CartService;
import com.qf.service.GoodsServiceImpl;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author zyh
 * @date 2019/9/11 0011 10:08
 * 佛祖保佑
 */
@WebServlet(name = "CartServlet",value = "/cartservlet")
public class CartServlet extends BaseServlet {
    CartService service = (CartService) ContextLoader.getCurrentWebApplicationContext().getBean("cartService");
    GoodsServiceImpl goodsService = (GoodsServiceImpl) ContextLoader.getCurrentWebApplicationContext().getBean("goodsServiceImpl");
    public String addCart(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "r:/xiaomi/login.jsp";
        }
        String goodsId = request.getParameter("goodsId");
        int id = Integer.parseInt(goodsId);
        Goods goods = goodsService.getGoodsById(id);
        BigDecimal price = goods.getPrice();
        String number = request.getParameter("number");
        int num = Integer.parseInt(number);
        Cart cart = new Cart();
        cart.setNum(num);
        cart.setGoods(goods);
        cart.setUser(user);
        cart.setMoney(price.multiply(new BigDecimal(num+"")));
        try {
            service.addCart(cart);

            return "r:/xiaomi/cartSuccess.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "/index.jsp";
        }

    }
    public String getCart(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "r:/xiaomi/login.jsp";
        }
        List<Cart> carts = service.findCartsById(user.getId());
        request.setAttribute("carts",carts);
        for (Cart cart : carts) {
            System.out.println(cart);
        }
        return "/cart.jsp";
    }
    public String addCartAjax(HttpServletRequest request, HttpServletResponse response){
        String number = request.getParameter("number");
        int i = Integer.parseInt(number);
        String pid = request.getParameter("goodsId");
        User user = ((User)request.getSession().getAttribute("user"));
        Integer id = Integer.parseInt(pid);
        if (i == 0) {
            try {
                service.delete(user.getId(),id);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return null;
        }
        if (i==-1){

                  service.ReduceNum(user,id);
                  return null;
        }
        if (i==1){
                    service.AddNum(user,id);
                    return null;
        }
            return null;
    }
    public String clearCartAjax(HttpServletRequest request, HttpServletResponse response){
        Integer user = ((User) request.getSession().getAttribute("user")).getId();
        service.clearCart(user);
        return null;
    }
    public String toBuy(HttpServletRequest request, HttpServletResponse response){
        return null;
    }

}
