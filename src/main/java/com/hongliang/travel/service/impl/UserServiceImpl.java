package com.hongliang.travel.service.impl;
import com.hongliang.travel.dao.UserDao;
import com.hongliang.travel.dao.impl.UserDaoImpl;
import com.hongliang.travel.domain.User;
import com.hongliang.travel.service.UserService;
import com.hongliang.travel.util.MailUtils;
import com.hongliang.travel.util.UuidUtil;

/**
 * @author Hongliang Zhu
 * @create 2020-05-11 23:00
 */
public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    @Override
    public boolean register(User user) {
        User u = dao.findByName(user.getUsername());
        //判断u是否为null
        if(u != null){
            // 存在，注册失败
            return false;
        }

        // 设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        // 保存用户信息
        dao.save(user);

        // 发送邮件激活
        String content = "<a href='https://hongliangzhu.cn/user/activate?code="+user.getCode()+"'>点击激活【道士庄旅游网】</a>";
        MailUtils.sendMail(user.getEmail(), content, "道士庄旅游网激活邮件");

        return true;
    }

    @Override
    public boolean activate(String code) {
        // 根据激活码查询用户
        User user = dao.findByCode(code);
        if(user != null){
            // 用户存在

            // dao修改激活状态
            dao.updateStatus(user);
            return true;
        }else {

            return false;
        }

    }

    @Override
    public User login(User user) {
        return dao.findByNameAndPassword(user.getUsername(),user.getPassword());
    }
}
