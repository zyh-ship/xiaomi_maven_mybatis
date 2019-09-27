package com.qf.Filter;

import com.qf.mapper.UserMapper;
import com.qf.pojo.User;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

/**
 * @author zyh
 * @date 2019/9/10 0010 20:07
 * 佛祖保佑
 */
@WebFilter(filterName = "LoginFilter",value = "/index.jsp")
public class LoginFilter implements Filter {
    UserMapper userMapper = ContextLoader.getCurrentWebApplicationContext().getBean("userMapper",UserMapper.class);
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        Object seesionUser = request.getSession().getAttribute("user");
        if (seesionUser!=null){
            chain.doFilter(req, resp);
            return ;
        }
        else{
            Cookie[] cookies = request.getCookies();
            if (cookies!=null){
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("info")){
                        byte[] decode = Base64.getDecoder().decode(cookie.getValue());
                        String de = new String(decode);
                        String[] split1 = de.split("#");
                        try {
                            User dao = userMapper.findByUsernameAndPassword(split1[0], split1[1]);
                            if (dao!=null){
                                if (dao.getFlag()==1){
                                    request.getSession().setAttribute("user",dao);
                                    chain.doFilter(request, resp);
                                    return ;
                                }
                            }
                            else {
                                Cookie cookie1 = new Cookie("info", "");
                                cookie1.setMaxAge(0);
                                cookie1.setHttpOnly(true);
                                cookie1.setPath("/");
                                HttpServletResponse resp1 = (HttpServletResponse) resp;
                                resp1.addCookie(cookie1);
                                chain.doFilter(request,resp1);
                                return;

                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }


        }
            chain.doFilter(request,resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
