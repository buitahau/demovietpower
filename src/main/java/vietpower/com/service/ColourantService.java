package vietpower.com.service;

import vietpower.com.model.Colourant;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface ColourantService {
    List<Colourant> findAll();

    void save(Colourant colourant);

    void update(Colourant colourant);
}
