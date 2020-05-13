package com.roll.casserole.ioc.mf;

import java.util.List;

/**
 * @author roll
 * created on 2020/5/12 2:21 下午
 */
public class ColonDelimeterMovieFinder implements MovieFinder{
    private String path;

    public ColonDelimeterMovieFinder(String path) {
        this.path = path;
    }

    @Override
    public List findAll() {
        return null;
    }
}
