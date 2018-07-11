package com.sawelly.fpog.controller;

import com.sawelly.fpog.entity.User;
import com.sawelly.fpog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    //    @RequestMapping("/wx")
//    @ResponseBody
//    public String wx(Model model, HttpServletRequest request,String username,int type) {
//        System.out.println(username);
//        System.out.println(type);
//        return "success";
//    }
    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        String backUrl = request.getParameter("backUrl");
        model.addAttribute("backUrl", backUrl);
        return "login";
    }

    //
    @RequestMapping("/doLogin")
    @ResponseBody
    public String doLogin(Model model, HttpServletRequest request, User user) {
        User u = userService.selectByUserNameAndPwd(user.getUserName(), user.getPwd());
        if (u == null) {
            return "-1";
        } else {
            request.getSession().setAttribute("SYSTEM_USER", u);
        }
        return "success";
    }

    //
//    private ProjectContext initProjectContext(Integer userId) {
//        ProjectContext projectContext = new ProjectContext();
//        projectContext.setUserId(userId);
//        return projectContext;
//    }
//
    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("SYSTEM_USER");
        response.sendRedirect("login");
    }
//
//    @RequestMapping("/jsonp")
//    @ResponseBody
//    public String jsonp(HttpServletRequest request,HttpServletResponse response) {
////		response.setHeader("Access-Control-Allow-Origin", "http://www.json.com");
//        return "callback('jsonp test');";
//    }

}
