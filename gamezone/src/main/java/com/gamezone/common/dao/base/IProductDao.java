package com.gamezone.common.dao.base;

import java.util.List;

import com.gamezone.common.model.Product;

public interface IProductDao {

    Product findById(Long id);

    List<Product> findAll();

}
