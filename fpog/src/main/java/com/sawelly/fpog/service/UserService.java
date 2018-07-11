package com.sawelly.fpog.service;

import com.sawelly.fpog.common.page.Page;
import com.sawelly.fpog.entity.Project;
import com.sawelly.fpog.entity.User;
import com.sawelly.fpog.entity.UserExample;
import com.sawelly.fpog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService{

    @Autowired
    private UserMapper userMapper;

    public User selectByUserNameAndPwd(String userName, String pwd) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUserNameEqualTo(userName)
                .andPwdEqualTo(pwd);
        List<User> lst = userMapper.selectByExample(userExample);
        return lst.size()==0?null:lst.get(0);
    }

    public Page selectUserPage(User user, Page page) {
        return fastMybatisPageDao.query(user, page.getCurrentPage(),
                page.getPageSize(), "selectUserPage");
    }

    public User selectById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public Integer save(User user) {
        if (user.getId() != null ){
            userMapper.updateByPrimaryKeySelective(user);
        }else{
            userMapper.insert(user);
        }
        return user.getId();
    }

    public void del(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
