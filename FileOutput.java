package monochromatic;

import java.io.FileWriter; // Import the FileWriter class

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collections;
import java.util.Calendar;
public class FileOutput {
    static String outputFile = Constants.outputFile;


    static String getTime(int slot){
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY,10);
        now.set(Calendar.MINUTE,0);
        now.set(Calendar.SECOND,0);
        int eq = slot*15;

        now.add(Calendar.MINUTE,eq);
        String s="";
        s+=Integer.toString(now.get(Calendar.HOUR_OF_DAY))+":"+Integer.toString(now.get(Calendar.MINUTE))+":"+Integer.toString(now.get(Calendar.SECOND));


        return s;
    }

    static void appendOutput() {
        try {
            FileWriter myWriter = new FileWriter(outputFile, true);
            ArrayList<Patterns> out=Periodicity.Daily_pattern;
            Collections.sort(out, new PatternComperator());
            
            myWriter.write("Daily pattern\n");
            for (Patterns cy : out) {
                Hotspot_Point ct = cy.center;
                // ct.x;
                // ct.y;
                // ct.z;
                // ct.color
                // pt.radius
                // pt.height;
                // pt.llr;
                String currLine = String.format("%d %d %d %d %d %d %d %f %d %d Time %s to %s\n", ct.x, ct.y, ct.start, ct.end, ct.color, cy.radius,
                cy.height, cy.llr,cy.c_value,cy.b_value,getTime(ct.start),getTime(ct.end));
                //System.out.println(currLine + "**********"+ct.x + " "+ct.y);
                
                myWriter.write(currLine);
            }
            
            myWriter.write("Weekdays pattern\n");
            out=Periodicity.weekdays;
            Collections.sort(out, new PatternComperator());
            for (Patterns cy : out) {
                Hotspot_Point ct = cy.center;
                // ct.x;
                // ct.y;
                // ct.z;
                // ct.color
                // pt.radius
                // pt.height;
                // pt.llr;
                String currLine = String.format("%d %d %d %d %d %d %d %f %d %d Time %s to %s\n", ct.x, ct.y, ct.start, ct.end, ct.color, cy.radius,
                cy.height, cy.llr,cy.c_value,cy.b_value,getTime(ct.start),getTime(ct.end));
                myWriter.write(currLine);
            }
            
            
            myWriter.write("Weekends pattern\n");
            out=Periodicity.weekends;
            Collections.sort(out, new PatternComperator());
            for (Patterns cy : out) {
            	Hotspot_Point ct = cy.center;
                // ct.x;
                // ct.y;
                // ct.z;
                // ct.color
                // pt.radius
                // pt.height;
                // pt.llr;
                String currLine = String.format("%d %d %d %d %d %d %d %f %d %d Time %s to %s\n", ct.x, ct.y, ct.start, ct.end, ct.color, cy.radius,
                cy.height, cy.llr,cy.c_value,cy.b_value,getTime(ct.start),getTime(ct.end));
                myWriter.write(currLine);
            }
            
            
            
            
            myWriter.write("weekly pattern\n");
            out=Periodicity.weekly_patterns;
            Collections.sort(out, new PatternComperator());
            for (Patterns cy : out) {
                Hotspot_Point ct = cy.center;
                // ct.x;
                // ct.y;
                // ct.z;
                // ct.color
                // pt.radius
                // pt.height;
                // pt.llr;
                String currLine = String.format("%d %d %d %d %d %d %d %f %d %d Time %s to %s\n", ct.x, ct.y, ct.start, ct.end, ct.color, cy.radius,
                cy.height, cy.llr,cy.c_value,cy.b_value,getTime(ct.start),getTime(ct.end));
                myWriter.write(currLine);
            }
            
            
            myWriter.write("Weekdays pattern\n");
            out=Periodicity.Daily_pattern_new;
            Collections.sort(out, new PatternComperator());
            for (Patterns cy : out) {
                Hotspot_Point ct = cy.center;
                // ct.x;
                // ct.y;
                // ct.z;
                // ct.color
                // pt.radius
                // pt.height;
                // pt.llr;
                String currLine = String.format("%d %d %d %d %d %d %d %f %d %d Time %s to %s\n", ct.x, ct.y, ct.start, ct.end, ct.color, cy.radius,
                cy.height, cy.llr,cy.c_value,cy.b_value,getTime(ct.start),getTime(ct.end));
                myWriter.write(currLine);
            }
            
            
            
            
            myWriter.close();
        } catch (Exception e) {
        }
    }

    static void clearOutputFile() {
        try {
            FileWriter myWriter = new FileWriter(outputFile);
            // clear the file
            myWriter.close();
        } catch (Exception e) {
        }
    }
}