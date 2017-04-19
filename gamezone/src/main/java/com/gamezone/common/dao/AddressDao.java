package com.gamezone.common.dao;

import org.springframework.stereotype.Repository;

import com.gamezone.common.dao.base.AbstractJpaDAO;
import com.gamezone.common.dao.base.IAddressDao;
import com.gamezone.common.model.Address;

@Repository
public class AddressDao extends AbstractJpaDAO<Address> implements IAddressDao {
    public AddressDao() {
        super();
        setClazz(Address.class);
    }
}
