package hau.kute.service;

import hau.kute.model.UserProfile;

import java.util.List;

/**
 * Created by HauKute on 8/4/2018.
 */
public interface UserProfileService {
    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();

}
