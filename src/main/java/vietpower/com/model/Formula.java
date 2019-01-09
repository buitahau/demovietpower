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

    @Column(name = "ApproximateColor")
    private String approximateColor;

    @Column(name = "Substrate")
    private String substrate;

    @Column(name = "Comment")
    private String comment;

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

    public String getApproximateColor() {
        return approximateColor;
    }

    public void setApproximateColor(String approximateColor) {
        this.approximateColor = approximateColor;
    }

    public String getSubstrate() {
        return substrate;
    }

    public void setSubstrate(String substrate) {
        this.substrate = substrate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
