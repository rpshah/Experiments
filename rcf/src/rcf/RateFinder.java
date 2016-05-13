/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author Raj Shah
 */
public class RateFinder {
    int [][]similarityCounter;
    ArrayList<ArrayList<UserVector>> userVector;
    ArrayList<ArrayList<MovieVector>> movieVector;
    ArrayList<ArrayList<SortedVector>> V;
    ArrayList<ArrayList<SimilarMovies>> vectorToUse;
    ArrayList<ArrayList<PriorityQueue<SortedVector>>> Q;
    int user;
    int K;
    int nodes;
    int dimentions;
    int totals = 0;
        
    public RateFinder(ArrayList<ArrayList<PriorityQueue<SortedVector>>> q,int [][]sc,ArrayList<ArrayList<UserVector>> uv,ArrayList<ArrayList<MovieVector>> mv,ArrayList<ArrayList<SortedVector>> V,int userId,int k,int n,int d){
        this.Q = q;
        this.similarityCounter = sc;
        this.userVector = uv;
        this.movieVector = mv;
        this.V = V;
        this.user = userId;
        this.K = k;
        this.nodes = n;
        this.dimentions = d;
        this.vectorToUse = new ArrayList<ArrayList<SimilarMovies>>();
    
        for(int i2=0;i2<4;i2++){
            this.vectorToUse.add(new ArrayList<SimilarMovies>());
            for(int i=0;i<=nodes;i++){
                vectorToUse.get(i2).add(new SimilarMovies(i));
            }
        }
        
    }
    
    public float[] findRates(){
        float rateCalculated[][] = new float[4][this.nodes];
        try{
            int ratedMovie = 0;
            int tempMovie = 0;
            SortedVector sv = new SortedVector();
            
            for(int i2=0;i2<4;i2++){
                System.out.println("Iteration NO" + i2);
                for(Iterator<Integer> i = userVector.get(i2).get(user).movies.iterator(); i.hasNext() ; ){
                    ratedMovie = i.next();
                    for(Iterator<SortedVector> i3 = Q.get(i2).get(ratedMovie).iterator();i3.hasNext();){
                        sv = (SortedVector)i3.next();
                        tempMovie = sv.movieId;
                        if( !(userVector.get(i2).get(user).movies.contains(tempMovie))){
                            vectorToUse.get(i2).get(tempMovie).movies.add(ratedMovie);
                        }
                    }
                }

                /**for(Iterator<SimilarMovies> i = vectorToUse.iterator(); i.hasNext() ; ){
                    SimilarMovies sm  = (SimilarMovies)i.next();
                    System.out.println("Movie : "+sm.movieId + "Size : "+sm.movies.size());
                }*/
                SimilarMovies sm = new SimilarMovies(0);

                for(Iterator<SimilarMovies> i = vectorToUse.get(i2).iterator(); i.hasNext() ; ){
                    sm = (SimilarMovies)i.next();
                    float finalRate = 0.0F;
                    float totalRate = 0.0F;
                    int rateByUser = 0;
                    float rateTemp = 0.0F;
                    int movieTemp = 0 ;
                    int kCounter = 0;
                    if(sm.movies.size() != 0){
                        finalRate = findAvgRate(sm.movieId);
                        //System.out.println("Movie : "+sm.movieId+"\tRate : "+finalRate);
                        if(kCounter > K)
                            break;
                        kCounter++;
                        for(Iterator<Integer> i3 = vectorToUse.get(i2).get(sm.movieId).movies.iterator(); i3.hasNext() ; ){
                            movieTemp = i3.next();
                            rateTemp  = findAvgRate(movieTemp);
                            rateByUser = findUserRate(movieTemp,user);
                            rateTemp = rateByUser - rateTemp;

                            totalRate += rateTemp;
                        }
                        finalRate = finalRate + (totalRate/sm.movies.size());
                        //System.out.println("Movie : "+sm.movieId+"\tRate : "+finalRate);
                        rateCalculated[i2][sm.movieId] = finalRate;
                        
                    }
                }
            }
            
        }
        catch(Exception e){}
        
        float []rateFinal = new float[this.nodes];
        for(int nodeNo=0;nodeNo<this.nodes;nodeNo++){
            float rateTemp=0.0F;
            float rateTotal=0.0F;
            float rateAvg=0.0F;
            int rateCounter = 0;
            
            for(int i2=0;i2<4;i2++){
                rateTemp = rateCalculated[i2][nodeNo];
                if(rateTemp == 0) {
                } 
                else{
                    rateTotal += rateTemp;
                    rateCounter++;
                }
            }
            
            if(rateCounter!=0){
                rateAvg = rateTotal/rateCounter;
                rateFinal[nodeNo] = rateAvg;
                totals++;
            }
            //System.out.println("Movie : "+nodeNo +" : Rate : "+ rateFinal[nodeNo] );
        }
        //System.out.println(totals);
        return rateFinal;
    }
    
    private float findAvgRate(int m){
        int total = 0;
        int count  = 0;
        int rate = 0;
        for(int i2=0;i2<4;i2++){
            SortedVector sv = this.V.get(i2).get(m);
            for(Iterator<Dimention> i = sv.dims.iterator(); i.hasNext() ; ){
                rate = ((Dimention)i.next()).rate;
                total += rate;
                count++;
            }
        }
        
        return ((float)total/count);
    }

    private int findUserRate(int m, int u) {
        int rate = 0;
        for(int i2=0;i2<4;i2++){
            SortedVector sv = this.V.get(i2).get(m);
            Dimention d = new Dimention();
            for(Iterator<Dimention> i = sv.dims.iterator(); i.hasNext() ; ){
                d = (Dimention)i.next();
                if( d.userId == u ){
                    rate = d.rate;
                    return rate;
                }
            }
        }
        
        return rate;
    }
}
