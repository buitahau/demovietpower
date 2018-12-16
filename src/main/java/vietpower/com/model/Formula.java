package vietpower.com.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "formulaCode", unique = true)
    private String formulaCode;

    @Column(name = "formulaName")
    private String formulaName;

    @ManyToOne
    @JoinColumn(name="collectionId")
    private Collection collection;

    @Column(name = "createdDate")
    private Timestamp createdDate;

    @ManyToOne
    @JoinColumn(name="machineId")
    private Machine machine;

    @Column(name = "baseOnCan")
    private Double baseOnCan;

//    @OneToMany(mappedBy="formula", fetch = FetchType.LAZY)
//    private Set<FormulaProductBase> productBases = new HashSet<>();
//
//    public Set<FormulaProductBase> getProductBases() {
//        return productBases;
//    }
//
//    public void setProductBases(Set<FormulaProductBase> productBases) {
//        this.productBases = productBases;
//    }

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

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Double getBaseOnCan() {
        return baseOnCan;
    }

    public void setBaseOnCan(Double baseOnCan) {
        this.baseOnCan = baseOnCan;
    }
}
