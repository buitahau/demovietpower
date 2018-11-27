package vietpower.com.service.impl;

import vietpower.com.dao.UserDao;
import vietpower.com.model.User;
import vietpower.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by HauKute on 8/4/2018.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User findBySSO(String sso) {
        return userDao.findBySSO(sso);
    }

    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public void updateUser(User user) {
        User entity = userDao.findById(user.getUserId());
        if(entity != null){
            entity.setUserName(user.getUserName());
            entity.setPassword(user.getPassword());
        }
    }

    @Override
    public void deleteUserBySSO(String sso) {
        userDao.deleteBySSO(sso);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public boolean isUserSSOUnique(Integer id, String sso) {
        User user = findBySSO(sso);
        return ( user == null || ((id != null) && (user.getUserId() == id)));
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }
}
