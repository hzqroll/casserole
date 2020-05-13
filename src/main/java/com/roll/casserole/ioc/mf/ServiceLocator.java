package com.roll.casserole.ioc.mf;

/**
 * @author roll
 * created on 2020/5/12 4:35 下午
 */
public class ServiceLocator {
    private static ServiceLocator soleInstance;

    private MovieFinder movieFinder;

    public static MovieFinder movieFinder() {
        return soleInstance.movieFinder;
    }

    public static void load(ServiceLocator arg) {
        soleInstance = arg;
    }

    public ServiceLocator(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    private void config() {
        ServiceLocator.load(new ServiceLocator(new ColonDelimeterMovieFinder("a.txt")));
    }
}
