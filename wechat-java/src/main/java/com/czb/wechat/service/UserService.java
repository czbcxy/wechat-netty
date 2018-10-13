package com.czb.wechat.service;

import com.czb.wechat.pojo.Users;
import com.czb.wechat.utils.JSONResult;
import org.apache.catalina.User;


public interface UserService {

    JSONResult SelectUsernameIsExist(Users users) throws Exception;

    JSONResult updateNicknameById(Users user);
}
