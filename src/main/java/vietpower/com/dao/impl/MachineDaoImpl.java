package vietpower.com.dao.impl;

import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.MachineDao;
import vietpower.com.model.Machine;

/**
 * Created by HauKute on 12/14/2018.
 */
@Repository("machineDao")
public class MachineDaoImpl extends AbstractDao<Integer, Machine> implements MachineDao {
}
