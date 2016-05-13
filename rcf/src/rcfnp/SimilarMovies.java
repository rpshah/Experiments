/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcfnp;

import java.util.ArrayList;

/**
 *
 * @author Raj Shah
 */
class SimilarMovies {
    int movieId;
    ArrayList<Integer> movies;
    
    public SimilarMovies(int m){
        movieId = m;
        movies = new ArrayList<Integer>();
    }
}
