package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.MachineColourantDao;
import vietpower.com.model.MachineColourant;
import vietpower.com.service.MachineColourantService;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("machineColourantService")
@Transactional
public class MachineColourantServiceImpl implements MachineColourantService {
    @Autowired
    private MachineColourantDao machineColourantDao;

    @Override
    public List<MachineColourant> findByMachineId(Long machineId) {
        return machineColourantDao.findByMachineId(machineId);
    }
}
