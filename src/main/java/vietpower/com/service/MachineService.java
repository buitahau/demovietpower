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

    void subtractColour(MachineColourant machineColourant);

    void saveFormulaProductBase(MachineFormulaProductBase machineFormulaProductBase);

    List<MachineColourant> getAllMachineColourant(Long machineId);

    List<MachineColourantLog> getAllMachineColourantLog(Long machineColourantLogId);

    List<MachineFormulaProductBase> getAllMachineFormulaProductBase(Long machineId);

    void updateMachineColourant(MachineColourant machineColourant);

    Machine findById(Long machineId);
}
