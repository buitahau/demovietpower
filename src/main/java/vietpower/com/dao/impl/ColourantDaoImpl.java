package vietpower.com.dao.impl;

import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.ColourantDao;
import vietpower.com.model.Colourant;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("colourantDao")
public class ColourantDaoImpl extends AbstractDao<Integer, Colourant> implements ColourantDao {

}
