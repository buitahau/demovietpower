package vietpower.com.service;

import vietpower.com.model.User;

import java.util.List;

/**
 * Created by HauKute on 8/4/2018.
 */
public interface UserService {
    User findById(Long userId);

    User findBySSO(String sso);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserBySSO(String sso);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Long id, String sso);

    User findByUserName(String userName);

    void deleteUserById(Long userId);
}
