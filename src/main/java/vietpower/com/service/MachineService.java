package vietpower.com.service;

import vietpower.com.model.Machine;
import vietpower.com.model.MachineColourant;
import vietpower.com.model.MachineColourantLog;
import vietpower.com.model.MachineFormulaProductBase;

import java.util.List;

/**
 * Created by HauKute on 12/20/2018.
 */
public interface MachineService {
    List<Machine> findAllMachine();

    Machine findById(Long machineId);

    // colourant tracking
    void subtractColour(MachineColourant machineColourant);

    List<MachineColourant> getAllMachineColourant(Long machineId);

    List<MachineColourantLog> getAllMachineColourantLog(Long machineColourantLogId);

    void updateMachineColourant(MachineColourant machineColourant);

//    formula product base tracking
    List<MachineFormulaProductBase> getAllMachineFormulaProductBase(Long machineId);

    MachineFormulaProductBase saveFormulaProductBase(MachineFormulaProductBase machineFormulaProductBase);

    MachineFormulaProductBase getMachineFormulaProductBaseLog(Long taskId);

    Machine updateMachine(Machine machine);

    Machine saveOrUpdate(Machine machine);
}
