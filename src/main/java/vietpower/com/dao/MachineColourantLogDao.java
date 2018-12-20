package vietpower.com.dao;

import vietpower.com.model.MachineColourantLog;

import java.util.List;

/**
 * Created by HauKute on 12/20/2018.
 */
public interface MachineColourantLogDao {
    void persist(MachineColourantLog log);

    List<MachineColourantLog> getByMachineColourant(Long machineColourantLogId);
}
