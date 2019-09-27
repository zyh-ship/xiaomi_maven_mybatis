package com.qf.web.servlet;




import com.qf.pojo.Address;
import com.qf.pojo.User;
import com.qf.service.UserServiceImpl;
import com.qf.utils.MD5Utils;
import com.qf.utils.RandomUtils;
import com.qf.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zyh
 * @date 2019/9/10 0010 15:16
 * 佛祖保佑
 */
@WebServlet("/userservlet")
public class UserServlet extends BaseServlet {
    UserServiceImpl userservice = (UserServiceImpl) ContextLoader.getCurrentWebApplicationContext().getBean("userServiceImpl");

    public String register(HttpServletRequest request, HttpServletResponse response) {

        try {
            User user = new User();
            for (Map.Entry<String, String[]> stringEntry : request.getParameterMap().entrySet()) {
                System.out.println(stringEntry.getKey() + "  " + stringEntry.getValue().toString());
            }
            BeanUtils.populate(user, request.getParameterMap());
            if (yanzheng(user).size() != 0) {
                request.setAttribute("error", yanzheng(user));
                return "/register.jsp";
            }
            user.setFlag(0);
            user.setRole(1);
            user.setCode(RandomUtils.CreateUUId());
            user.setPassword(MD5Utils.md5(user.getPassword()));
            userservice.register(user);

        /*    EmailUtils.sendEmail(user);*/
            return "r:/myxiaomi/registerSuccess.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/register.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password1 = request.getParameter("password");

        String password = MD5Utils.md5(password1);
        User login = userservice.login(username, password);
        if (login == null) {
            request.setAttribute("msg", "用户名密码错误!");
            return "/login.jsp";
        }
        if (login.getFlag() != 1) {
            request.setAttribute("msg", "你还没有激活");
            return "/login.jsp";
        }
        if (login.getRole() != 1) {
            request.setAttribute("msg", "你没有权限");
            return "/login.jsp";
        }
        String auto = request.getParameter("auto");
        if (auto != null) {
            String info = username + "#" + password;
            info = Base64.getEncoder().encodeToString(info.getBytes());
            System.out.println(info);
            Cookie cookie = new Cookie("info", info);
            cookie.setMaxAge(60 * 60 * 24 * 7);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }
        request.getSession().setAttribute("user", login);
        return "r:/myxiaomi/index.jsp";
    }

    public String checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        User user = userservice.checkUsername(username);
        if (user != null) {
            response.getWriter().write("1");
        } else {
            response.getWriter().write("0");
        }
        return null;
    }

    public Map<String, String> yanzheng(User user) {
        Map<String, String> error = new HashMap<>();
        if (StringUtils.isEmpty(user.getUsername())) {
            error.put("usernameError", "用户名不能为空");
        }
        if (StringUtils.isEmpty(user.getUsername())) {
            error.put("passwordError", "密码不能为空");
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            error.put("emailError", "邮箱不能为空");
        }
        return error;
    }

    public String checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = (String) request.getSession().getAttribute("code");
        String parameter = request.getParameter("code");
        if (code.equalsIgnoreCase(parameter)) {
            response.getWriter().write("0");
        } else {
            response.getWriter().write("1");
        }

        return null;
    }

    public String logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        Cookie cookie = new Cookie("info", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "r:/myxiaomi/index.jsp";
    }

    public String getAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            request.setAttribute("msg","你还没有登陆");
            return "r:/myxiaomi/login.jsp";
        }
        List<Address> byUid = userservice.findAddressByUid(user.getId());
        request.setAttribute("addList", byUid);
        return "/self_info.jsp";
    }
    public String deleteAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int uid = ((User) request.getSession().getAttribute("user")).getId();
        int id = Integer.parseInt(request.getParameter("id"));
        userservice.deleteAddress(uid,id);
        return getAddress(request,response);
    }
    public String addAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int uid = ((User) request.getSession().getAttribute("user")).getId();
        Address address = new Address();
        try {
            BeanUtils.populate(address,request.getParameterMap());
            address.setUid(uid);
            address.setLevel(0);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(address);
        userservice.addAddress(address);
        return getAddress(request,response);
    }
    public String updateAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Address address = new Address();
        Integer user = ((User) request.getSession().getAttribute("user")).getId();
        try {
            BeanUtils.populate(address,request.getParameterMap());
            address.setUid(user);
            userservice.updateAddress(address);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return getAddress(request,response);
    }
    public String defaultAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int i = Integer.parseInt(request.getParameter("id"));
        Integer uid = ((User) request.getSession().getAttribute("user")).getId();
        userservice.setDefault(i,uid);
        return getAddress(request,response);
    }


}