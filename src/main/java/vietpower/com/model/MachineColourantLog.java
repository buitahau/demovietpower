package vietpower.com.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by HauKute on 12/17/2018.
 */
@Entity
@Table(name="MachineColourantLog")
public class MachineColourantLog {
    @Id
    @Column(name = "machineColourantLogId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long machineColourantLogId;

    @ManyToOne
    @JoinColumn(name="machineColourantId")
    private MachineColourant machineColourant;

    @Column
    private String action;

    @Column
    private Double quantity;

    @Column
    private Timestamp createdDate;

    public Long getMachineColourantLogId() {
        return machineColourantLogId;
    }

    public void setMachineColourantLogId(Long machineColourantLogId) {
        this.machineColourantLogId = machineColourantLogId;
    }

    public MachineColourant getMachineColourant() {
        return machineColourant;
    }

    public void setMachineColourant(MachineColourant machineColourant) {
        this.machineColourant = machineColourant;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
