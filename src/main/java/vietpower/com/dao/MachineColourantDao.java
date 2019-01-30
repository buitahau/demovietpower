package vietpower.com.dao;

import vietpower.com.model.MachineColourant;

import java.util.List;

/**
 * Created by HauKute on 12/20/2018.
 */
public interface MachineColourantDao {
    MachineColourant findByMachineAndColour(Long machineId, Long colourantId);

    List<MachineColourant> findByMachineId(Long machineId);

    void persist(MachineColourant machineColourant);

    void update(MachineColourant machineColourant);

    void deleteAll();
}
