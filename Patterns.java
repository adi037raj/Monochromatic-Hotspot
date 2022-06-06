package monochromatic;

//import java.util.regex.Pattern;

public class Patterns {
    Hotspot_Point center;
    int radius;
    int height;
    double llr;
    int c_value;
    int b_value;

    Patterns(Hotspot_Point center, int radius, int height, float llr,int c,int b) {
        this.center = center;
        this.radius = radius;
        this.height = height;
        this.c_value=c;
        this.llr = llr;
        this.b_value=b;
        
    }
    Patterns(Patterns p)    // Copy Constructor
    {
    	this.center=p.center;
    	this.llr=p.llr;
    	this.height=p.height;
    	this.radius=p.radius;
        this.c_value=p.c_value;
        this.b_value=p.b_value;
       

    }
    

    void printer() {
        center.printer();
        System.out.printf("r: %d, ht: %d, llr: %f\n", radius, height, llr);
    }
}
