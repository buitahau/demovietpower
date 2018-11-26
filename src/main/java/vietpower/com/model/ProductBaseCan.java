package vietpower.com.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by HauKute on 11/25/2018.
 */
@Entity
@Table(name="ProductBaseCan")
public class ProductBaseCan {
    @Id
    @Column(name = "productBaseCanId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productBaseCanId;

    @ManyToOne
    @JoinColumn(name="productBaseId", nullable = false)
    private ProductBase productBase;

    @NotEmpty
    @Column(name = "can")
    private Double can;

    @Column(name = "unit")
    private String unit;

    @Column(name = "pricePerCan")
    private Double pricePerCan;

    @Column(name = "barCode")
    private String barCode;

    @Column(name = "percentage")
    private Integer percentage;

    public Long getProductBaseCanId() {
        return productBaseCanId;
    }

    public void setProductBaseCanId(Long productBaseCanId) {
        this.productBaseCanId = productBaseCanId;
    }

    public ProductBase getProductBase() {
        return productBase;
    }

    public void setProductBase(ProductBase productBase) {
        this.productBase = productBase;
    }

    public Double getCan() {
        return can;
    }

    public void setCan(Double can) {
        this.can = can;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPricePerCan() {
        return pricePerCan;
    }

    public void setPricePerCan(Double pricePerCan) {
        this.pricePerCan = pricePerCan;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
