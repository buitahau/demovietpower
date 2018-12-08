package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.ProductBaseCanDao;
import vietpower.com.dao.ProductBaseDao;
import vietpower.com.model.ProductBase;
import vietpower.com.service.ProductBaseService;

import java.util.*;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("productBaseService")
@Transactional
public class ProductBaseServiceImpl implements ProductBaseService {
    @Autowired
    private ProductBaseDao productBaseDao;

    @Autowired
    private ProductBaseCanDao productBaseCanDao;

    @Override
    public List<ProductBase> findByProductId(Long productId){
        List<ProductBase>  result = this.productBaseDao.findByProductId(productId);

        return result;
    }
}
