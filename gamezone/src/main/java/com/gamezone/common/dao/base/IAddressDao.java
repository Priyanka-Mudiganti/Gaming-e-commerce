package com.gamezone.common.dao.base;

import com.gamezone.common.model.Address;

public interface IAddressDao {

    void create(Address address);
    
    Address findById(Long id);

}
