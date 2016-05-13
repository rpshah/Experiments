/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcfnp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author Raj Shah
 */
class KnnGraph {
    
    int N;
    int D;
    
    int []counter;
    int []similarityCounter;
    ArrayList<LinkedList<SortedVector>> L;
    ArrayList<PriorityQueue<SortedVector>> Q;
    
    public KnnGraph(int n,int d){
        N=n;
        D=d;
        L = new ArrayList<LinkedList<SortedVector>>();
        Q = new ArrayList<PriorityQueue<SortedVector>>();
        counter = new int[N+1];
        similarityCounter = new int[N+1];
        L.add(new LinkedList<SortedVector>());
        Q.add(new PriorityQueue<SortedVector>(N+1));
        
    }

     ArrayList<PriorityQueue<SortedVector>> getKnnQueues(ArrayList<SortedVector> V, int P, int K) {
        
        try{
            /**for(Iterator<SortedVector> i =V.iterator(); i.hasNext();){
                SortedVector svCheck = (SortedVector)i.next();
                System.out.println(svCheck.movieId+" Size "+ svCheck.dims.size());
            }*/

             for(int i=0;i<=D;i++){
                    L.add(new LinkedList<SortedVector>());
                
             }
                SortedVector sv = new SortedVector();
                LinkedList<SortedVector> ll = new LinkedList<SortedVector>();
                int pos=0;

                for(Iterator<SortedVector> i = V.iterator(); i.hasNext() ; ){
                  sv = (SortedVector)i.next();
                  if(sv.dims.size()==0)
                      continue;
                  pos = sv.dims.get(0).userId;
                  ll = L.get(pos);
                  ll.add(sv);
                  counter[sv.movieId] = 2;
                }

                boolean flag = false;
                do{
                    flag = false;
                    for(int i=1 ; i<=D ; i++){
                        ll = L.get(i);
                        for(Iterator<SortedVector> i3 = ll.iterator(); i3.hasNext(); ){
                            sv = (SortedVector)i3.next();
                            similarityCounter[sv.movieId] += ll.size();
                        }
                    }
                    int limit = P*K;
                    for(Iterator<SortedVector> i = V.iterator(); i.hasNext() ; ){
                        sv = (SortedVector)i.next();
                        if(similarityCounter[sv.movieId] < limit && counter[sv.movieId]<= sv.dims.size()){
                            try{
                                pos = sv.dims.get(counter[sv.movieId]).userId;
                                ll = L.get(pos);
                                ll.add(sv);
                                counter[sv.movieId] += 1;
                                flag = true;
                            }
                            catch(ArrayIndexOutOfBoundsException e){

                            }
                        }
                    }
                }while(flag);

                for(int i=0;i<=N;i++){
                    Q.add(new PriorityQueue<SortedVector>(20,new KnnQueue()));
                }
                SortedVector v1 = new SortedVector();
                SortedVector v2 = new SortedVector();
                for(Iterator<LinkedList<SortedVector>> i = L.iterator(); i.hasNext(); ){
                    ll = (LinkedList<SortedVector>)i.next();
                    for(Iterator<SortedVector> i4 = ll.iterator(); i4.hasNext(); ){
                        v1 = (SortedVector)i4.next();
                        for(Iterator<SortedVector> i3 = ll.iterator(); i3.hasNext(); ){
                            v2 = (SortedVector)i3.next();
                            if(v1 == v2)
                                continue;
                            if( !(Q.get(v1.movieId).contains(v2)))
                                Q.get(v1.movieId).add(v2);
                        }
                    }
                }
            
            
            
        }
        catch(Exception e){
            System.out.println(e);
            //throw e;
        }
        /**int c=0;
        for(Iterator<PriorityQueue<SortedVector>> i = Q.iterator(); i.hasNext();c++){
            PriorityQueue<SortedVector> pq= (PriorityQueue<SortedVector>)i.next();
            for(Iterator<SortedVector> i2 = pq.iterator(); i2.hasNext() ; ){
                SortedVector svTemp = (SortedVector)i2.next();
                System.out.print("Item:"+svTemp.movieId+",");
            }
            System.out.println("That was for Item : "+c);
        }*/
        return Q;
    }
    
    private class KnnQueue implements Comparator<SortedVector>{

        
        @Override
        public int compare(SortedVector o1, SortedVector o2) {
            int i1,i2;
            try{
                i1 = similarityCounter[o1.movieId];
                i2 = similarityCounter[o2.movieId];
            }
            catch(Exception e){
                System.out.println(e);
                i1 = i2 = 0;
            }
            return i1 < i2 ? -1 : (i1==i2 ? 0 : 1);
        }
        
    }
    
     public int[] getSimilarityCounter(){
        return this.similarityCounter;
    }
   
}
