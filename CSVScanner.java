package monochromatic;

import java.io.*;
import java.util.ArrayList;

public class CSVScanner {
    public long[] readCSV(String fileName) {
        ArrayList<Point> ret = new ArrayList<Point>();
        int yy=0;

        long ans[] = new long[1255];
        long rtt[] = new long[1255];


// for(int i=0;i<1255;i++)
// System.out.print(ans[i]+" ");
System.out.println("&&&&&&&&&&&&&&&&&&&&");
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
            // sc.useDelimiter("\n");
            // sc.next();
            // sc.next();
            // sc.next();
            // sc.next();
            // sc.next();
            // sc.next();
            // NOTE : remove the header line in the csv file manually
            
            int xx=1;
            while (true) {
                try {
                    String lineInput = br.readLine();
                    if (lineInput == null) {
                        break;
                    }

                   // System.out.println(lineInput);
                    if(xx==1)
                    {
                    	xx++;
                    	continue;
                    }
                    xx++;
                    String[] inp = lineInput.split(",", 200);

                    int x = Integer.parseInt(inp[1]);
                    int y = Integer.parseInt(inp[0]);
                   // new Double(s).intValue();
                  
                  System.out.println(x+" y "+y);
                //System.out.println(t);
                    //int z = Integer.parseInt(inp[3]);
                    //inp[4] = inp[4].trim();
                    //int color = Integer.parseInt(inp[4]);
                    //Point currPoint = new Point(x, y, z, color);
                    //ret.add(currPoint);
                    //rtt[yy] = y;
                    if(y==0) ans[y] = x;
   
                else
                  ans[yy] = x + ans[yy-1];
                   
                 //  System.out.println("hello");
                    yy++;

                  //  System.out.println("hello");

                }
                
                

                
                catch (Exception e) {
                    System.out.println(e);
                    System.out.println("exit with exception in CSV Scanner2");
                    break;
                }
            }

            br.close();
           int empt=0;
            for(int i=1;i<1255;i++)
            {

                System.out.print(ans[i]+" ");
                if(ans[i]==0)
                {
                    ans[i]=ans[i-1];
                   empt++;
                }
               

              //  System.out.print(ans[i]+" ");
            }
            System.out.println(empt+" total is empty");

           
           

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("exit with exception in CSV2 Scanner2");
        }
       // System.out.println("last time stamp is "+ yy + " and count is " + ans[yy-1]);
       System.out.println("total ans is "+ans[1254]);
        return ans;
    }

}



/*package monochromatic;

import java.io.*;
import java.util.ArrayList;

public class CSVScanner {
    public ArrayList<Point> readCSV(String fileName) {
        ArrayList<Point> ret = new ArrayList<Point>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
            // sc.useDelimiter("\n");
            // sc.next();
            // sc.next();
            // sc.next();
            // sc.next();
            // sc.next();
            // sc.next();
            // NOTE : remove the header line in the csv file manually
            
            int xx=1;
            while (true) {
                try {
                    String lineInput = br.readLine();
                    if (lineInput == null) {
                        break;
                    }
                    if(xx==1)
                    {
                    	xx++;
                    	continue;
                    }
                    String[] inp = lineInput.split(",", 100);

                    int x = Integer.parseInt(inp[1]);
                    int y = Integer.parseInt(inp[2]);
                    int z = Integer.parseInt(inp[3]);
                    inp[4] = inp[4].trim();
                    int color = Integer.parseInt(inp[4]);
                    Point currPoint = new Point(x, y, z, color);
                    ret.add(currPoint);
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("exit with exception in CSV Scanner2");
                    break;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("exit with exception in CSV Scanner2");
        }
        return ret;
    }

}*/