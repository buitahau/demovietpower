package vietpower.com.dao;

import vietpower.com.model.UserProfile;

import java.util.List;

/**
 * Created by HauKute on 8/4/2018.
 */
public interface UserProfileDao {
    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
