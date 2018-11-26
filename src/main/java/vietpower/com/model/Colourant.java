package vietpower.com.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by HauKute on 11/25/2018.
 */
@Entity
@Table(name = "Colourant")
public class Colourant {
    @Id
    @Column(name = "colourantId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long colourantId;

    @NotEmpty
    @Column(name = "colourantCode", unique = true)
    private String colourantCode;

    @NotEmpty
    @Column(name = "colourantName")
    private String colourantName;

    @NotEmpty
    @Column(name = "rbgHex")
    private String rbgHex;

    @Column(name = "density")
    private Double density;

    @Column(name = "pricePerUnit")
    private Double pricePerUnit;

    @Column(name = "surcharge")
    private Integer surcharge;

    @Column(name = "kind")
    private String kind;

    public Long getColourantId() {
        return colourantId;
    }

    public void setColourantId(Long colourantId) {
        this.colourantId = colourantId;
    }

    public String getColourantCode() {
        return colourantCode;
    }

    public void setColourantCode(String colourantCode) {
        this.colourantCode = colourantCode;
    }

    public String getColourantName() {
        return colourantName;
    }

    public void setColourantName(String colourantName) {
        this.colourantName = colourantName;
    }

    public String getRbgHex() {
        return rbgHex;
    }

    public void setRbgHex(String rbgHex) {
        this.rbgHex = rbgHex;
    }

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Integer getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(Integer surcharge) {
        this.surcharge = surcharge;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
