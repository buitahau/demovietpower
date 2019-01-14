package vietpower.com.model;

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
    private Long userId;

    @Column(name = "username", unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name="roleId")
    private Role role;

    @ManyToOne
    @JoinColumn(name="machineId")
    private Machine machine;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
