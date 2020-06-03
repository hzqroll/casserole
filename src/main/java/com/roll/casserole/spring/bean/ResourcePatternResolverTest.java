package com.roll.casserole.spring.bean;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;

/**
 * @author roll
 * created on 2020/5/30 9:29 下午
 */
public class ResourcePatternResolverTest {
    public static void main(String[] args) throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource resource = resourcePatternResolver.getResource("classpath:common.xml");
        File file = resource.getFile();
        System.out.println(file.getName());

        System.out.println(resource.getURL());

        System.out.println(resource.getURI());
    }
}
