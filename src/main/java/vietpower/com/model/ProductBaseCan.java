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
    @JoinColumn(name="productId", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name="baseId", nullable = false)
    private Base base;

    @NotEmpty
    @Column(name = "volume")
    private Float volume;

    @Column(name = "unit")
    private String unit;

    @Column(name = "pricePerCan")
    private Float pricePerCan;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Float getPricePerCan() {
        return pricePerCan;
    }

    public void setPricePerCan(Float pricePerCan) {
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
