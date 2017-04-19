package com.gamezone.common.dao.base;

import java.util.List;

import com.gamezone.common.model.PaymentProfile;

public interface IPaymentProfileDao {

	PaymentProfile findById(Long id);

	void create(PaymentProfile entity);
    
    List<PaymentProfile> findAll();

}
