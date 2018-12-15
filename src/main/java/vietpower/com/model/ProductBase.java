package vietpower.com.model;

//import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by HauKute on 11/26/2018.
 */
@Entity
@Table(name="ProductBase")
public class ProductBase {
    @Id
    @Column(name = "productBaseId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productBaseId;

    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name="baseId")
    private Base base;

    //@NotEmpty
    @Column(name = "rbgHex")
    private String rbgHex;

    @Column(name = "density")
    private Double density;

    @Column(name = "createdDate")
    private Timestamp createdDate;

    @ManyToOne
    @JoinColumn(name="createBy")
    private User createBy;

//    @OneToMany(mappedBy="productBase", fetch = FetchType.LAZY)
//    private List<ProductBaseCan> productBaseCans;
//
//    public List<ProductBaseCan> getProductBaseCans() {
//        return productBaseCans;
//    }
//
//    public void setProductBaseCans(List<ProductBaseCan> productBaseCans) {
//        this.productBaseCans = productBaseCans;
//    }

    public Long getProductBaseId() {
        return productBaseId;
    }

    public void setProductBaseId(Long productBaseId) {
        this.productBaseId = productBaseId;
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
