package vietpower.com.model;

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

    @Column(name = "baseCode", unique = true)
    private String baseCode;

    @Column(name = "baseName")
    private String baseName;

    @Column(name = "createdDate")
    private Timestamp createdDate;

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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
