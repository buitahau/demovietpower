package hau.kute.dao;

import hau.kute.model.User;

import java.util.List;

/**
 * Created by HauKute on 8/4/2018.
 */
public interface UserDao {
    User findById(int id);

    User findBySSO(String sso);

    void save(User user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();

}
