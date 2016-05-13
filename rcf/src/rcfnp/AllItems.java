/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcfnp;

/**
 *
 * @author Raj Shah
 */
class AllItems {
    int userId;
    int movieId;
    int rate;
    long timeStamp;
    
    public AllItems(int u,int m,int r,long t){
        userId = u;
        movieId = m;
        rate = r;
        timeStamp = t;
    }
}
