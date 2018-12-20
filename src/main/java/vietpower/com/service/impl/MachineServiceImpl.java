package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.*;
import vietpower.com.model.*;
import vietpower.com.service.MachineService;

import java.sql.Timestamp;
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
    @Autowired
    MachineColourantLogDao machineColourantLogDao;
    @Autowired
    MachineFormulaProductBaseDao machineFormulaProductBaseDao;

    @Override
    public List<Machine> findAllMachine() {
        return machineDao.findAll();
    }

    @Override
    public void subtractColour(MachineColourant dto) {
        Long machineId = dto.getMachine().getMachineId();
        Long colourantId = dto.getColourant().getColourantId();

        Machine machine = machineDao.findById(machineId);
        Colourant colourant = colourantDao.findById(colourantId);

        MachineColourant machineColourant = machineColourantDao.findByMachineAndColour(machineId, colourantId);
        if(machineColourant == null){
            machineColourant = new MachineColourant();
            machineColourant.setMachine(machine);
            machineColourant.setColourant(colourant);
            machineColourant.setQuantity(0d);
            machineColourantDao.persist(machineColourant);
        }

        MachineColourantLog log = new MachineColourantLog();
        log.setQuantity(dto.getQuantity());
        log.setAction("subtract");
        log.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        log.setMachineColourant(machineColourant);
        machineColourantLogDao.persist(log);

        machineColourant.setQuantity(machineColourant.getQuantity() - dto.getQuantity());
        machineColourantDao.update(machineColourant);
    }

    @Override
    public void saveFormulaProductBase(MachineFormulaProductBase machineFormulaProductBase) {
        machineFormulaProductBase.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        machineFormulaProductBaseDao.persist(machineFormulaProductBase);
    }

    @Override
    public List<MachineColourant> getAllMachineColourant(Long machineId) {
        return machineColourantDao.getByMachine(machineId);
    }

    @Override
    public List<MachineColourantLog> getAllMachineColourantLog(Long machineColourantLogId) {
        return machineColourantLogDao.getByMachineColourant(machineColourantLogId);
    }

    @Override
    public List<MachineFormulaProductBase> getAllMachineFormulaProductBase(Long machineId) {
        return machineFormulaProductBaseDao.getByMachine(machineId);
    }
}
