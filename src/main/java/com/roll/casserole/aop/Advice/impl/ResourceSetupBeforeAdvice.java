package com.roll.casserole.aop.Advice.impl;

import com.roll.casserole.aop.Advice.BeforeAdviceTest;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.Resource;

import java.lang.reflect.Method;

/**
 * @author zongqiang.hao
 * created on 2019-03-10 13:17.
 */
public class ResourceSetupBeforeAdvice implements BeforeAdviceTest {

    private Resource resource;

    public ResourceSetupBeforeAdvice(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        if (resource.exists()){
            FileUtils.forceMkdir(resource.getFile());
        }
    }
}
