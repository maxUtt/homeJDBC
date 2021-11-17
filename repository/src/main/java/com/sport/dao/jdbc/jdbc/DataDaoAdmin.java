package com.sport.dao.jdbc.jdbc;

import com.sport.model.Admin;

public interface DataDaoAdmin {
    default Admin getAdminRole(String login, String password){
        return null;
    }
}
