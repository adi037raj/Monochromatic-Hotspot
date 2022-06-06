package monochromatic;

//import java.time.Period;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.HashMap;

/**
 * main
 */
public class Main {
	
	static int a = Constants.a;    // max x
	static int b = Constants.b;    // max y
	static int c = Constants.c;    // max time
	static int centerThreshold = Constants.centerThreshold;       // 
	static int htMin = Constants.htMin; 
	static int htMax = Constants.htMax;
	static int rMin = Constants.rMin;
	static int rMax = Constants.rMax;
	
	static long totalPoints = 0;

	// initializing the grid to 0

	
	
	
	

	static void addPointsToCummulativeGrid(int grid[][][])
	{
		int day,x,y,t;
		long tempGrid[][][];
		for(day=0;day<Constants.dayCount;day++)
		{
			//totalPoints = 0;
			tempGrid=new long[Constants.a][Constants.b][Constants.dayTimeSlot];
			for(x=0;x<Constants.a;x++)
			{
				for(y=0;y<Constants.b;y++)
				{
					for(t=0;t<Constants.c/Constants.dayCount;t++)
					{
						int z = t+day*(Constants.dayTimeSlot);
						if(x==0 && y==0 && t==0)
						{
							tempGrid[x][y][t] = grid[x][y][z];
						}
						else
						{
							long var1 = x > 0 ? tempGrid[x-1][y][t]: 0;
							long var2 = y > 0 ? tempGrid[x][y-1][t]: 0;
							long var3 = t > 0 ? tempGrid[x][y][t-1]: 0;
							long var4 = (x > 0 && y > 0) ? tempGrid[x-1][y-1][t] : 0;
							long var5 = (x > 0 && t > 0) ? tempGrid[x-1][y][t-1] : 0;
							long var6 = (y > 0 && t > 0) ? tempGrid[x][y-1][t-1] : 0;
							long var7 = (x > 0 && y > 0 && t > 0) ? tempGrid[x-1][y-1][t-1] : 0;
							long var8 = grid[x][y][z];
							tempGrid[x][y][t] = var1 + var2 + var3 - var4 - var5 - var6 + var7 + var8;
						}
					}
				}
			}
			totalPoints += tempGrid[Constants.a-1][Constants.b-1][Constants.dayTimeSlot-1];
			Constants.cummulativeGridList.add(day, new Cummulative_Grid(tempGrid));
		}
	}

	
	
	static void gridReset(int grid[][][], int x_axis_length, int y_axis_length, int z_axis_length) {        
		int i, j, k;
		for (i = 0; i < x_axis_length; i++) {
			for (j = 0; j < y_axis_length; j++) {
				for (k = 0; k < z_axis_length; k++) {
					grid[i][j][k] = 0;
				}
			}
		}
	}
	// storing the points in the grid
	static void addPointsToGrid(int grid[][][], ArrayList<Point> pointList) {
		for (Point p : pointList) {
			int x = p.x;
			int y = p.y;
			int z = p.z;
			// p.printer();
			grid[x][y][z]++;
		}
	}




	/*static void statCollection(ArrayList<Patterns> candidatePatterns, HashMap<Integer, Integer> colorFrequency,
			HashMap<Integer, Float> maxLLR, int color) {
		for (Patterns cy : candidatePatterns) {

			// pt.printer(); // todo remove
			if (colorFrequency.get(color) == null) {
				colorFrequency.put(color, 1);
			} else {
				int prevFreq = colorFrequency.get(color);
				colorFrequency.put(color, prevFreq + 1);
			}
			if (maxLLR.get(color) == null) {
				maxLLR.put(color, cy.llr);
			} else {
				float prevLLR = maxLLR.get(color);
				float nextMaxLLR = Math.max(prevLLR, cy.llr);
				maxLLR.put(color, nextMaxLLR);
			}
		}
	}*/

	static void statPrint(HashMap<Integer, ArrayList<Point>> colorPoint, HashMap<Integer, Integer> colorFrequency,
			HashMap<Integer, Float> maxLLR) {
		for (HashMap.Entry<Integer, ArrayList<Point>> entry : colorPoint.entrySet()) { // iterate over all the entries
			// in colorPoint hashmap
			int color = entry.getKey();
			int freq = (colorFrequency.get(color) == null) ? 0 : colorFrequency.get(color);
			float llr = (maxLLR.get(color) == null) ? 0 : maxLLR.get(color);

			System.out.printf("color: %d, freq: %d, maxLLR: %f\n", color, freq, llr);
			System.out.printf("points of above color: %d\n", entry.getValue().size());
		}
	}

	// this is the heart of the algorithm ---monochromatic 

	static void findPattern(long ans[], ArrayList<Point> pointList, int color) { // Here No parameters are required need to remove

		int i,j,r,start,end;
		//int times=0;
		Utils ut = new Utils();

		ArrayList<Patterns> Daily_pattern_new=new ArrayList<Patterns>();

		ArrayList<Patterns> Daily_pattern=new ArrayList<Patterns>();
		ArrayList<Patterns> weekdays=new ArrayList<Patterns>();
		ArrayList<Patterns> weekends=new ArrayList<Patterns>();
		ArrayList<Patterns> weekly_patterns=new ArrayList<Patterns>();
		float max_llr=0;
		for(i=0;i<1;i++)       // iterating for every possible x 
		{  			
			for(j=0;j<1;j++)      // iterating for every possible y
			{
				for(r=0;r<=0;r+=1)         //  iterating for every possible radius
				{
		
					// radius checking
					//if(i-r < 0 || i+r > a || j-r <0 || j+r > b) break;
					
					// daily patterns
					for(start=0; start<Constants.dayTimeSlot; start++)
					{
						for(end = start+4; end<=start+24 && end<Constants.dayTimeSlot; end=end+4)
						{

							int inside_points=0;
							//int outsidePoints=0;
							for(int day=0; day<Constants.dayCount; day+=1)
							{
								if(((day*96)+end)<1254)								
									inside_points += ans[(day*96)+end] - ans[(day*96)+start];
								//outsidePoints+= ut.points_inside_cylinder(i, j, end, Constants.dayTimeSlot, r, day);
								
								//if(day+1<Constants.dayCount)
								//outsidePoints+= ut.points_inside_cylinder(i, j, 0, start, r,day+1);
							}
							
							
							//System.out.println("inside is "+inside_points);
							float LR0 = ut.new_llr(inside_points, end-start, r, ans[1253], Constants.dayCount);
							//float LR2 = ut.llr(outsidePoints, Constants.dayTimeSlot-(end-start), r, pointList.size(),Constants.dayCount);
							//float llrVal = LR2 >= 1 ? LR0/LR2 : LR0;  // eliminating the elephant pattern
							float llrVal = LR0;
							
							max_llr=Math.max(max_llr, llrVal);
							//System.out.println("Llr "+max_llr);
							if (llrVal > Constants.logThreshold_daily) {

								Hotspot_Point currPoint = new Hotspot_Point(i, j, start,end, color);
								
								float cylinderVolume = ( end-start) * Constants.dayCount;
								float density = (float)  ans[1253] / (c * Constants.dayCount);
								int expectedPointsIn = Math.round(density * cylinderVolume);



								Daily_pattern_new.add(new Patterns(currPoint, r, end-start, llrVal,inside_points,expectedPointsIn));

							}
						}
					}
					
					//System.out.println("Daily pattern complete");
					
					
					// weekdays
					/*for(start=0; start<Constants.dayTimeSlot; start++)
					{
						for(end = start+4; end<start+48 && end<Constants.dayTimeSlot; end=end+4)             // considering only the 3 hours patterns
						{
							//Patterns maxLLRPattern = new Patterns(new Point(), 0, 0, -1);

							int inside_points=0;
							int outsidePoints=0;
							for(int day=0; day<Constants.dayCount; day+=1) // consider day 0 means monday,1 -> tuesday , so on
							{
								if(day%5==0 || day%6==0)continue;
								inside_points += ut.points_inside_cylinder(i, j, start, end, r,day);
								outsidePoints+= ut.points_inside_cylinder(i, j, end, Constants.dayTimeSlot, r,day);
								
								if(day+1<Constants.dayCount)
								outsidePoints+= ut.points_inside_cylinder(i, j, 0, start, r,day+1);
							}

							float LR0 = ut.llr(inside_points, end-start, r, pointList.size(),5*Constants.weeks);
							float LR2 = ut.llr(outsidePoints, Constants.dayTimeSlot-(end-start), r, pointList.size(),5*Constants.weeks);
							//float llrVal=LR2!=0?LR0/LR2:LR0;
							float llrVal = LR0;
							max_llr=Math.max(max_llr, llrVal);
							if (llrVal > Constants.logThreshold_weekdays) {
								Hotspot_Point currPoint = new Hotspot_Point(i, j, start,end, color);
								weekdays.add(new Patterns(currPoint, r, end-start, llrVal));

							}
						}	
					}
					//System.out.println("weekdays pattern complete");
					
					
					
					
					
					// weekends
					for(start=0; start<Constants.dayTimeSlot; start++)
					{
						for(end = start+4; end<start+48 && end<Constants.dayTimeSlot; end=end+4)             // considering only the 3 hours patterns
						{
							//Patterns maxLLRPattern = new Patterns(new Point(), 0, 0, -1);

							int inside_points=0;
							int outsidePoints=0;
							for(int day=0; day<Constants.dayCount; day+=1) // consider day 0 means monday,1 -> tuesday , so on
							{
								if(day%5==0 || day%6==0)
									inside_points += ut.points_inside_cylinder(i, j, start, end, r,day);
								outsidePoints+= ut.points_inside_cylinder(i, j, end, Constants.dayTimeSlot, r,day);
								
								if(day+1<Constants.dayCount)
								outsidePoints+= ut.points_inside_cylinder(i, j, 0, start, r,day+1);
							}

							float LR0 = ut.llr(inside_points, end-start, r, pointList.size(),2*Constants.weeks);
							float LR2 = ut.llr(outsidePoints, Constants.dayTimeSlot-(end-start), r, pointList.size(),2*Constants.weeks);
							//float llrVal=LR2!=0?LR0/LR2:LR0;
							float llrVal = LR0;
							max_llr=Math.max(max_llr, llrVal);
							if (llrVal > Constants.logThreshold_weekends) {
								Hotspot_Point currPoint = new Hotspot_Point(i, j, start,end, color);
								weekends.add(new Patterns(currPoint, r, end-start, llrVal));
							}
						}	
					}
					//System.out.println("weekends pattern complete");
					
					
					
					// weekly patterns
					
					for(start=0; start<Constants.dayTimeSlot-9; start++)
					{
						for(end = start+4; end<start+48 && end<Constants.dayTimeSlot; end=end+4)                    // considering only the 3 hours patterns
						{
							//Patterns maxLLRPattern = new Patterns(new Point(), 0, 0, -1);

							for(period=1;period<=7;period++)
							{
								int inside_points=0;
								int outsidePoints=0;
								for(int day=period-1; day<Constants.dayCount; day+=7)
								{
									inside_points += ut.points_inside_cylinder(i, j, start, end, r,day);
									outsidePoints+= ut.points_inside_cylinder(i, j, end, Constants.dayTimeSlot, r,day);
									
									if(day+1<Constants.dayCount)
									outsidePoints+= ut.points_inside_cylinder(i, j, 0, start, r,day+1);
								}

								float LR0 = ut.llr(inside_points, end-start, r, pointList.size(),1*Constants.weeks);
								float LR2 = ut.llr(outsidePoints, Constants.dayTimeSlot-(end-start), r, pointList.size(),1*Constants.weeks);
								//float llrVal=LR2!=0?LR0/LR2:LR0;
								float llrVal = LR0;
								max_llr=Math.max(max_llr, llrVal);
								if (llrVal > Constants.logThreshold_weekly) {
									Hotspot_Point currPoint = new Hotspot_Point(i, j, start,end, color);
									weekly_patterns.add(new Patterns(currPoint, r, end-start, llrVal));

								}
							}
						}
					}*/
					//System.out.println("weekly patten complete");
				}
			}
		}
		System.out.println("Hotspot geenrated ");

		Daily_pattern_new = MergeOverlapCylinders.mergeOverlapNew(Daily_pattern_new);

		Daily_pattern=MergeOverlapCylinders.mergeOverlap(Daily_pattern);
		weekdays=MergeOverlapCylinders.mergeOverlap(weekdays);
		weekends=MergeOverlapCylinders.mergeOverlap(weekends);
		weekly_patterns=MergeOverlapCylinders.mergeOverlap(weekly_patterns);
		System.out.println("Overlapped done");
		Periodicity.Daily_pattern_new = Daily_pattern_new;
		Periodicity.Daily_pattern=Daily_pattern;
		Periodicity.weekdays=weekdays;
		Periodicity.weekends=weekends;
		Periodicity.weekly_patterns=weekly_patterns;
		System.out.println(max_llr);
	}
	/*
	static ArrayList<Patterns> findShiftedPattern(int grid[][][], ArrayList<Point> pointList, int color) {
		Utils ut = new Utils();
		int x_axis_length = a;
		int y_axis_length = b;
		int z_axis_length = c;

		ArrayList<Patterns> candidatePatterns = new ArrayList<Patterns>();

		int i, j, k;
		for (i = 0; i < x_axis_length; i++) {
			for (j = 0; j < y_axis_length; j++) {
				for (k = 0; k < 96; k++) {
					if (grid[i][j][k] > centerThreshold) {
						for (int ht = htMin; ht < htMax && k + ht < 96; ht++) {
							for (int r = rMin; r <= rMax; r++) {
								int insidePoints = 0;
								for (int day = 0; day < 7; day++) {
									// center -> i, j, k;
									// height -> ht;
									// radius -> r;
									// if (ht == 0) { // just for checking
									insidePoints += ut.pointsInCylinder(grid, i, j, k + day * 96, ht, r);
								}
								float llrVal = ut.llr(insidePoints, ht, r, pointList.size(),0);
								if (llrVal > logThreshold) {
									Point currPoint = new Point(i, j, k, color);
									Patterns currPattern = new Patterns(currPoint, r, ht, llrVal);
									candidatePatterns.add(currPattern);
								}
							}
						}
					}
				}
			}
		}
		return candidatePatterns;
	}
	 */

	public static void main(String[] args) {
		// todo clear the output file
		FileOutput.clearOutputFile();
		CSVScanner sc = new CSVScanner();
		Utils ut = new Utils();
		ArrayList<Point> points = new ArrayList<Point>(); //= sc.readCSV(fileName);

	
		String path = "C:\\Users\\ADITYA\\Videos\\circle enumeration bengalure\\";

		long ans[]=sc.readCSV("C:\\Users\\ADITYA\\Music\\modieed ne data.csv");


		
		
		
		System.out.println("Input done");
		
		//HashMap<Integer, ArrayList<Point>> colorPoint = new HashMap<Integer, ArrayList<Point>>();
		/*for (Point p : points) {
			// p.printer();
			ut.addToList(p.color, p, colorPoint);         // distinguish points with respect to colors and store in colorpoints
		}*/

		int x_axis_length = a;
		int y_axis_length = b;
		int z_axis_length = c;
		// ArrayList<ArrayList<ArrayList<Integer>>> grid = new
		// ArrayList<>(x_axis_length);
		// for (i = 0; i < x_axis_length; i++) {
		// grid.add(new ArrayList<ArrayList<Integer>>(y_axis_length));
		// for (j = 0; j < y_axis_length; j++) {
		// grid.get(i).add(new ArrayList<Integer>(z_axis_length));
		// }
		// }
		int grid[][][] = new int[x_axis_length][y_axis_length][z_axis_length];
		//HashMap<Integer, Integer> colorFrequency = new HashMap<Integer, Integer>(); // count of significant cylinders
		// colorwise
		//HashMap<Integer, Float> maxLLR = new HashMap<Integer, Float>(); // maxLLR colorwise
		
		// for each color we are finding the pattern
		/*
		for (HashMap.Entry<Integer, ArrayList<Point>> entry : colorPoint.entrySet()) { // iterate over all the entries
			// in colorPoint hashmap
			int color = entry.getKey(); // current color
			ArrayList<Point> pointList = entry.getValue(); // all points of current color
			System.out.println("color is "+color);
			gridReset(grid, x_axis_length, y_axis_length, z_axis_length);

			addPointsToGrid(grid, pointList);
			System.out.println("before findpattern");
			findPattern(grid, pointList, color);
			System.out.println("patterns findpattern completed");
			FileOutput.appendOutput(); // this will print the points to the txt file

			//statCollection(candidatePatterns, colorFrequency, maxLLR, color);

			// Find cylinder
			// Find pattern
			// print answer
		}
*/
		//statPrint(colorPoint, colorFrequency, maxLLR);
	
		long start=System.currentTimeMillis();
		
		
		//gridReset(grid, x_axis_length, y_axis_length, z_axis_length);
		//addPointsToGrid(grid, points);
		//addPointsToCummulativeGrid(grid);
		System.out.println("total points " + totalPoints);
		System.out.println("before findpattern");
		findPattern(ans, points, 0);
		System.out.println("patterns findpattern completed");
		FileOutput.appendOutput(); 
		long end=System.currentTimeMillis();
		System.out.println("The total time taken is"+(end-start));

	}
}
