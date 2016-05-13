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
class MovieVector {
    int movieId;
    ArrayList<Integer> users;
    
    public MovieVector(){
        movieId = 0;
        users = new ArrayList<Integer>();
    }
}
