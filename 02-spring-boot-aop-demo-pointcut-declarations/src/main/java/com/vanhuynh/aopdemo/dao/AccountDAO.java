package com.vanhuynh.aopdemo.dao;

import com.vanhuynh.aopdemo.Account;

public interface AccountDAO {
    void addAccount(Account theAccount, boolean vigFlag);
    boolean doWork();
}
