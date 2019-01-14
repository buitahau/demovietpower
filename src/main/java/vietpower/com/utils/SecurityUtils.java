package vietpower.com.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import vietpower.com.security.MyUserDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HauKute on 1/15/2019.
 */
public class SecurityUtils {
    /**
     * This method to retrieve the current online User Detail
     * @return the current online MyUserDetail object
     */
    public static MyUserDetail getPrincipal() {
        return (MyUserDetail) (SecurityContextHolder
                .getContext()).getAuthentication().getPrincipal();
    }
    /**
     * This getLoginUserId() is only used for doing jUnit test case only
     * @return the current login name for online user
     */
    public static Long getLoginUserId() {
        return getPrincipal().getUserId();
    }


    public static boolean userHasAuthority(String roleCode) {
        List<GrantedAuthority> list = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        List<GrantedAuthority> authories = list;
        for (GrantedAuthority authority : authories) {
            if (authority.getAuthority().equals(roleCode)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getAuthorities(){
        List<String> results = new ArrayList<String>();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
        }
        return results;
    }
}
