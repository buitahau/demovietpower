package vietpower.com.model;

import javax.persistence.*;

/**
 * Created by HauKute on 12/14/2018.
 */
@Entity
@Table(name="Machine")
public class Machine {
    @Id
    @Column(name = "machineId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long machineId;

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "minQuantity")
    private Integer minQuantity;

    @Column(name = "maxQuantity")
    private Integer maxQuantity;

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
