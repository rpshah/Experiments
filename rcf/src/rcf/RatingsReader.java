/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author Raj Shah
 */
public class RatingsReader {
    
   int a[] = new int[4];
   
   File ratingsFile;
   ArrayList<ArrayList<SortedVector>> vectors;
   ArrayList<AllItems> temp;
   ArrayList<ArrayList<UserVector>> userVector;
   ArrayList<ArrayList<MovieVector>> movieVector;
   int nodes;
   int dimentions;
   
   public RatingsReader(){
       temp = new ArrayList<AllItems>();
       vectors = new ArrayList<ArrayList<SortedVector>>();
       userVector = new ArrayList<ArrayList<UserVector>>();
       movieVector = new ArrayList<ArrayList<MovieVector>>();

       for(int i=0;i<4;i++){
            vectors.add(new ArrayList<SortedVector>());
            userVector.add(new ArrayList<UserVector>());
            movieVector.add(new ArrayList<MovieVector>());
       }
       
   }
   
   public void readFile(){
       try{
           ratingsFile = new File("ratings.dat");
           FileReader fReader = new FileReader(ratingsFile);
           BufferedReader bReader = new BufferedReader(fReader);

           nodes = 0;
           dimentions = 0;
           String line;
           String values[] = new String[4];
           int userId = 0,movieId = 0,rate = 0;
           long timeStamp = 0;
           
           while(bReader.ready()){
               line = bReader.readLine();
               values = line.split("::", 4);
               //For debugging
                //System.out.println(values[0] + "\t" + values[1] + "\t" + values[2] + "\t" + values[3] + "\t");
                
                try{
                    userId = Integer.parseInt(values[0]);
                    movieId = Integer.parseInt(values[1]);
                    rate = Integer.parseInt(values[2]);
                    timeStamp = Long.parseUnsignedLong(values[3]);
                }
                catch(NumberFormatException wrString){
                    userId = 0;
                    movieId = 0;
                    rate = 0 ;
                    timeStamp = 0;
                }
                finally{
                    //System.out.println(userId + "\t" + movieId + "\t" + rate + "\t" + timeStamp + "\t");
                    temp.add(new AllItems(userId,movieId,rate,timeStamp));
                    if(userId>dimentions)
                        dimentions = userId;
                    if(movieId>nodes)
                        nodes = movieId;
                }
           }
           
           fReader.close();
           bReader.close();
       }
       catch(FileNotFoundException notFound){
            System.out.println("The DataFile Could Not be Found\nMake Sure Your File Name is Correct.");
            System.out.println(notFound);
        }
       catch(Exception e){
            System.out.println(e);
       }
   }
   
   public ArrayList<ArrayList<SortedVector>> getSortedVectors(){
       SortedVector sv  = new SortedVector();
       UserVector uv = new UserVector();
       MovieVector mv = new MovieVector();
       for(int i=0;i<=nodes;i++){
           for(int i2=0;i2<4;i2++){
               vectors.get(i2).add(new SortedVector());
               movieVector.get(i2).add(new MovieVector());
           }
       }
       for(int i=0;i<=dimentions;i++){
           for(int i2=0;i2<4;i2++){
             userVector.get(i2).add(new UserVector());
           }
       }
       int partition = -1;
       for(Iterator<AllItems> i = temp.iterator(); i.hasNext(); ){
           AllItems data = (AllItems)i.next();
           partition++;
           if(partition == 4)
               partition = 0;
           sv = vectors.get(partition).get(data.movieId);
           sv.movieId = data.movieId;
           sv.dims.add(new Dimention(data.userId,data.rate,data.timeStamp));
           
           uv = userVector.get(partition).get(data.userId);
           uv.UserId = data.userId;
           uv.movies.add(data.movieId);
           
           mv = movieVector.get(partition).get(data.movieId);
           mv.movieId = data.movieId;
           mv.users.add(data.userId);
           
       }
       for(int i=0;i<=nodes;i++){
           for(int i2=0;i2<4;i2++){
                sv = vectors.get(i2).get(i);
                if(sv.movieId == 0)
                    sv.movieId = i ;
                sv.dims.sort(new Dimention());
           }
       }
       //System.out.println(vectors);
       return vectors;
   }
   
   public int getNodes(){
       return this.nodes;
   }
   public int getDimentions(){
       return this.dimentions;
   }
   public ArrayList<ArrayList<UserVector>> getUserVector(){
       return userVector;
   }
   public ArrayList<ArrayList<MovieVector>> getMovieVector(){
       return movieVector;
   }
}
