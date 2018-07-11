package com.sawelly.fpog.controller.system;

import com.sawelly.fpog.common.page.Page;
import com.sawelly.fpog.entity.User;
import com.sawelly.fpog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController extends BaseSystemController{

    private Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(Model model, User user, Page page){
        page = userService.selectUserPage(user,page);
        model.addAttribute("page",page);
        model.addAttribute("user",user);
        return "user/user_list";
    }

    @RequestMapping("/edit")
    public String edit(Model model, User user){
        if (user.getId() != null){
            user = userService.selectById(user.getId());
        }
        model.addAttribute("user",user);
        return "user/user_edit";
    }

    @RequestMapping("/save")
    public String save(Model model, User user){
        userService.save(user);
        addSuccessMessage("保存成功");
        return "redirect:index";
    }

    @RequestMapping("/del/{id}")
    public String del(@PathVariable("id") Integer id){
        userService.del(id);
        addSuccessMessage("删除成功");
        return "redirect:/user/index";
    }
}
