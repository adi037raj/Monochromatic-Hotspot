package monochromatic;

import java.util.ArrayList;

public class Constants {

    static String dd = "sahibzada ajit singh nagar data";
    //static String fileName = "C:\\Users\\ADITYA\\Music\\MTP MS Results\\" +dd+".csv";
    static String outputFile = "C:\\Users\\ADITYA\\Videos\\circle enumeration bengalure\\nee.txt";
    static int a = 26;          // max x limit
    static int b = 26;            // max y limit
    static int weeks=2;
    static int c = 24*4*7*weeks;          // max time limit (hours * slot * days * weeks)
    static int dayCount = 7*weeks;          //     total days  
    static int dayTimeSlot=c/dayCount;      // No. of slots in a day
    static int centerThreshold = 0;    // experimental
    static int htMin = 4;                  //  Not in use currently
    static int htMax = 12;                // NOt in use currently
    static int rMin = 1;                
    static int rMax = 12;             // iterating every possible radius 
    static int logThreshold_daily =  10;       // experimental value to be decided
    static int logThreshold_weekly = 10;
    static int logThreshold_weekends=10;
    static int logThreshold_weekdays=10;
    static float rTol = 0.1f; // percentage tolerance in radius for shifted hotspots
    static float hTol = 0.1f;
    static ArrayList<Cummulative_Grid> cummulativeGridList=new ArrayList<>(dayCount+1);
    static int overlappingPercentage=40;
    // percentage tolerance in height for shifted hotspots
}
