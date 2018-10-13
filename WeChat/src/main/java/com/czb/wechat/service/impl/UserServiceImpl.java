package com.czb.wechat.service.impl;

import com.czb.wechat.dao.UsersMapper;
import com.czb.wechat.pojo.Users;
import com.czb.wechat.service.UserService;
import com.czb.wechat.utils.JSONResult;
import com.czb.wechat.utils.MD5Utils;
import com.czb.wechat.vivo.UsersVo;
import org.apache.catalina.User;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * //@Transactional(propagation=Propagation.REQUIRED)
 * //如果有事务, 那么加入事务, 没有的话新建一个(默认情况下)
 * //@Transactional(propagation=Propagation.NOT_SUPPORTED)
 * //容器不为这个方法开启事务
 * //@Transactional(propagation=Propagation.REQUIRES_NEW)
 * //不管是否存在事务,都创建一个新的事务,原来的挂起,新的执行完毕,继续执行老的事务
 * //@Transactional(propagation=Propagation.MANDATORY)
 * //必须在一个已有的事务中执行,否则抛出异常
 * //@Transactional(propagation=Propagation.NEVER)
 * //必须在一个没有的事务中执行,否则抛出异常(与Propagation.MANDATORY相反)
 * //@Transactional(propagation=Propagation.SUPPORTS)
 * //如果其他bean调用这个方法,在其他bean中声明事务,那就用事务.如果其他bean没有声明事务,那就不用事务.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public JSONResult SelectUsernameIsExist(Users users) throws Exception {
        Users user = usersMapper.selectUserByusername(users);
        UsersVo usersVo = new UsersVo();
        if (user != null) {
            boolean b = user.getPassword().equals(MD5Utils.getMD5Str(users.getPassword()));
            if (!b) {
                return JSONResult.errorMsg("登录失败");
            }
            BeanUtils.copyProperties(user, usersVo);
        } else {
            users.setId(sid.nextShort());
            users.setNickname(users.getUsername());
            users.setFaceImage("");
            users.setFaceImageBig("");
            users.setPassword(MD5Utils.getMD5Str(users.getPassword()));
            users.setQrcode("");
            usersMapper.saveUsers(users);
            BeanUtils.copyProperties(users, usersVo);
        }
        return JSONResult.ok(usersVo);
    }

    @Override
    public JSONResult updateNicknameById(Users user)  {
        try {
            usersMapper.updateNicknameById(user);
        }catch (Exception e){
            return JSONResult.errorMsg("修改错误");
        }
        return JSONResult.ok();
    }

}

