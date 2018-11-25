package vietpower.com.converter;


import vietpower.com.model.UserProfile;
import vietpower.com.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by HauKute on 8/4/2018.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {

    @Autowired
    UserProfileService userProfileService;

    @Override
    public UserProfile convert(Object ele) {
        Integer id = Integer.parseInt((String)ele);
        UserProfile profile = userProfileService.findById(id);
        return profile;
    }
}
