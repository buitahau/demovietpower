package hau.kute.model;

/**
 * Created by HauKute on 8/4/2018.
 */
public enum UserProfileType {
    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    String userProfileType;

    UserProfileType(String s) {

    }

    public String getUserProfileType() {
        return userProfileType;
    }

    public void setUserProfileType(String userProfileType) {
        this.userProfileType = userProfileType;
    }
}
