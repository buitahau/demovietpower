package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.ColourantDao;
import vietpower.com.dao.MachineColourantDao;
import vietpower.com.dao.MachineDao;
import vietpower.com.model.Colourant;
import vietpower.com.model.Machine;
import vietpower.com.model.MachineColourant;
import vietpower.com.service.MachineService;

import java.util.List;

/**
 * Created by HauKute on 12/20/2018.
 */
@Service("machineService")
@Transactional
public class MachineServiceImpl implements MachineService{
    @Autowired
    MachineDao machineDao;
    @Autowired
    MachineColourantDao machineColourantDao;
    @Autowired
    ColourantDao colourantDao;

    @Override
    public List<Machine> findAllMachine() {
        return machineDao.findAll();
    }

    @Override
    public void subtractColour(MachineColourant machineColourant) {
        Long machineId = machineColourant.getMachine().getMachineId();
        Long colourantId = machineColourant.getColourant().getColourantId();

        Machine machine = machineDao.findById(machineId);
        Colourant colourant = colourantDao.findById(colourantId);

//        MachineColourant machineColourant = machineColourantDao.findByMachineAndColour(machineId, colourantId);
    }
}
