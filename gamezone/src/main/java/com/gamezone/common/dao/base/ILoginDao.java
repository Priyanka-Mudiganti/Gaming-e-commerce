package com.gamezone.common.dao.base;

import com.gamezone.common.model.User;

public interface ILoginDao {

    User findByName(String name);
    
    void create(User user);

}
