package vietpower.com.model;

//import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by HauKute on 8/2/2018.
 */
@Entity
@Table(name="User")
public class User {

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    //@NotEmpty
    @Column(name = "username", unique = true)
    private String userName;

    //@NotEmpty
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name="roleId")
    private Role role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
