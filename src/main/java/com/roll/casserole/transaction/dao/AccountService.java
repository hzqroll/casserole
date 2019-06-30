package com.roll.casserole.transaction.dao;

/**
 * @author zongqiang.hao
 * created on 2019-04-11 21:35.
 */
public interface AccountService {
    void inset(String customerName, String money);

    Object get(int id);

    void update(int id, String customerName, String money);
}
