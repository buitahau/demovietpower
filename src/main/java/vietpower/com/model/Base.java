package vietpower.com.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by HauKute on 11/25/2018.
 */
@Entity
@Table(name="Base")
public class Base {
    @Id
    @Column(name = "baseId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long baseId;

    @NotEmpty
    @Column(name = "baseCode", unique = true)
    private String baseCode;

    @NotEmpty
    @Column(name = "baseName")
    private String baseName;

    @NotEmpty
    @Column(name = "rbgHex")
    private String rbgHex;

    @Column(name = "density")
    private Float density;

    @Column(name = "createdDate")
    private Timestamp createdDate;

    @ManyToOne
    @JoinColumn(name="createBy")
    private User createBy;

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getRbgHex() {
        return rbgHex;
    }

    public void setRbgHex(String rbgHex) {
        this.rbgHex = rbgHex;
    }

    public Float getDensity() {
        return density;
    }

    public void setDensity(Float density) {
        this.density = density;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }
}
