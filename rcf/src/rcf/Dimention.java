/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcf;

import java.util.Comparator;

/**
 *
 * @author Raj Shah
 */
class Dimention implements Comparator<Dimention>{
    int userId;
    int rate;
    long timeStamp;
    
    public Dimention(int u,int r,long t){
        userId = u;
        rate = r;
        timeStamp = t;
    }

    public Dimention(){}
    
    @Override
    public int compare(Dimention o1, Dimention o2) {
        return o1.timeStamp < o2.timeStamp ? -1 : (o1.timeStamp == o2.timeStamp ? 0 : 1) ;
    }
    
    
}
