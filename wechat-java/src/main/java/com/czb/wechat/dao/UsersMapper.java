package com.czb.wechat.dao;

import com.czb.wechat.pojo.Users;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMapper {
    Users selectUserByusername(Users users);

    void saveUsers(Users users);

    void updateNicknameById(Users user);
}