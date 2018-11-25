package vietpower.com.dao.impl;

import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.BaseDao;
import vietpower.com.model.Base;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("baseDao")
public class BaseDaoImpl extends AbstractDao<Integer, Base> implements BaseDao{
}
