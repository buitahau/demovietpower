package hau.kute.service;

import hau.kute.model.User;

import java.util.List;

/**
 * Created by HauKute on 8/4/2018.
 */
public interface UserService {
    User findById(int id);

    User findBySSO(String sso);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserBySSO(String sso);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);
}
