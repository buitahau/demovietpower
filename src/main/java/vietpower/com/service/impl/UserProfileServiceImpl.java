package vietpower.com.service.impl;

import vietpower.com.dao.UserProfileDao;
import vietpower.com.model.UserProfile;
import vietpower.com.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by HauKute on 8/4/2018.
 */
@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Override
    public UserProfile findById(int id) {
        return userProfileDao.findById(id);
    }

    @Override
    public UserProfile findByType(String type) {
        return userProfileDao.findByType(type);
    }

    @Override
    public List<UserProfile> findAll() {
        return userProfileDao.findAll();
    }
}
