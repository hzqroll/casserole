package com.roll.casserole.set.hash;

/**
 * <p>@author roll
 * <p>created on 2020/7/21 4:54 下午
 */
public class VirtualService {
    private Service service;

    public VirtualService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
