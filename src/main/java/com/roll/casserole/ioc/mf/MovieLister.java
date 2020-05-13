package com.roll.casserole.ioc.mf;

import java.util.Iterator;
import java.util.List;

/**
 * @author roll
 * created on 2020/5/12 9:13 上午
 */
public class MovieLister {
    private MovieFinder finder;

    public MovieLister(MovieFinder movieFinder) {
        this.finder = new ColonDelimeterMovieFinder("movie.txt");
    }

    public Movie[] moviesDirectedBy(String arg) {
        List allMovies = finder.findAll();
        for (Iterator it = allMovies.iterator(); it.hasNext(); ) {
            Movie movie = (Movie) it.next();
            if (!movie.getDirect().equals(arg)) {
                it.remove();
            }
        }
        return (Movie[]) allMovies.toArray(new Movie[allMovies.size()]);
    }
}
