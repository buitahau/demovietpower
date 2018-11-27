package vietpower.com.dao;

import vietpower.com.model.Formula;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface FormulaDao {
    List<Formula> findAll();

    void persist(Formula formula);
}
