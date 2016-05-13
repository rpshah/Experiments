/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcf;

import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

/**
 *
 * @author Raj Shah
 */
public class Rcf {
    public int ratesFound;
    public long excecutionTime;

    /**
     * @param args the command line arguments
     */
    public void main(int userValue) {
        // TODO code application logic here
        RatingsReader r = new RatingsReader();
            System.out.println("Reading The File");
        r.readFile();
            System.out.println("Generating Sorted Vectors");
        ArrayList<ArrayList<SortedVector>> V = r.getSortedVectors();
        
        int userId = userValue; 
        int P = 4;
        int K = 3;
        int nodes = r.getNodes();
        int dimentions = r.getDimentions();
        
        long start = new Date().getTime();
        
        KnnGraph constructor = new KnnGraph(nodes,dimentions);
            System.out.println("Generating Queues");
        ArrayList<ArrayList<PriorityQueue<SortedVector>>> Q = constructor.getKnnQueues(V,P,K);
        
        int [][]similarityCounter = constructor.getSimilarityCounter();
        ArrayList<ArrayList<UserVector>> userVector = r.getUserVector();
        ArrayList<ArrayList<MovieVector>> movieVector = r.getMovieVector();
        RateFinder rf = new RateFinder(Q,similarityCounter,userVector,movieVector,V,userId,K,nodes,dimentions);
            System.out.println("Finding Ratings...");
        
        float []rateCalculated;    
        rateCalculated = rf.findRates();
        ratesFound = rf.totals;
        System.out.println(ratesFound);
        
        long end  = new Date().getTime();
        excecutionTime = end-start;
        System.out.println(excecutionTime);
        
    }
    
}
