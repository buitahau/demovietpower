package vietpower.com.dao.impl;

import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.ProductBaseCanDao;
import vietpower.com.model.ProductBaseCan;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("productBaseCanDao")
public class ProductBaseCanDaoImpl extends AbstractDao<Integer, ProductBaseCan> implements ProductBaseCanDao {
}
