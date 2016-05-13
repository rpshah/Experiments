/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Scanner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

/**
 *
 * @author Raj Shah
 */
public class StartPoint extends ApplicationFrame
{
   public StartPoint( String applicationTitle , String chartTitle )
   {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Category",            
         "score",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 600 , 600 ) );        
      setContentPane( chartPanel ); 
   }
   private CategoryDataset createDataset( )
   {
      final String timeTitle = "Excecution Time";        
      final String rateTitle = "Rates Count";
      final String list1 = "Without Patition";
      final String list2 = "With Patition";
      
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  
      
      System.out.println("Please enter UserId(1-6040)");
      int userValue = new Scanner(System.in).nextInt();
      rcf.Rcf partition = new rcf.Rcf();
      partition.main(userValue);
      
      rcfnp.Rcf noPartition = new rcfnp.Rcf();
      noPartition.main(userValue);
      
              
      dataset.addValue( noPartition.excecutionTime , list1 , timeTitle );        
      dataset.addValue( partition.excecutionTime , list2 , timeTitle);        
      
      dataset.addValue( noPartition.ratesFound , list1 , rateTitle );        
      dataset.addValue( partition.ratesFound , list2 , rateTitle);        
      
      return dataset; 
   }
   public static void main( String[ ] args )
   {
      StartPoint chart = new StartPoint("Reverse Colloborative Filtering Statistics", "Which method is better? In time & in Counting!");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}

