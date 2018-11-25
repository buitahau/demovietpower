package vietpower.com.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by HauKute on 11/25/2018.
 */
@Entity
@Table(name="Formula")
public class Formula {
    @Id
    @Column(name = "formulaId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formulaId;

    @NotEmpty
    @Column(name = "formulaCode", unique = true)
    private String formulaCode;

    @NotEmpty
    @Column(name = "formulaName")
    private String formulaName;

    @ManyToOne
    @JoinColumn(name="collectionId")
    private Collection collection;

    @Column(name = "createdDate")
    private Timestamp createdDate;

    @ManyToOne
    @JoinColumn(name="createBy")
    private User createBy;

    public Long getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(Long formulaId) {
        this.formulaId = formulaId;
    }

    public String getFormulaCode() {
        return formulaCode;
    }

    public void setFormulaCode(String formulaCode) {
        this.formulaCode = formulaCode;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
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
