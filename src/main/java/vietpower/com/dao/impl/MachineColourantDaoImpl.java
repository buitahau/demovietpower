package vietpower.com.dao.impl;

import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.MachineColourantDao;
import vietpower.com.model.MachineColourant;

/**
 * Created by HauKute on 12/20/2018.
 */
@Repository("machineColourantDao")
public class MachineColourantDaoImpl extends AbstractDao<Integer, MachineColourant> implements MachineColourantDao {
}
