import java.util.*;
import greenfoot.*;
/**
 * SparkleEngine - Very portable & reusable engine that wrapps various functions.
 * All static methods.
 * Rooted from grade 11 final project's engine, developed largely since then.
 * Making the engine an actor to prevent repeatitively call dt = new Date().
 * 
 * @author Xuanxi Jiang
 * @version 0.5
*/
public class SparkleEngine{
    /**
     * convert the given image to greyscale
     * 
     * @param img   The given image
     * @return GreenfootImage   Greyscale image
     */
    public static GreenfootImage greyIFy(GreenfootImage img){
        GreenfootImage ret = new GreenfootImage(img);
        for(int x=0; x<ret.getWidth(); x++){
            for(int y=0; y<ret.getHeight(); y++){
                Color cc = ret.getColorAt(x, y);
                int tmp = (cc.getRed()+cc.getBlue()+cc.getGreen())/3;
                ret.setColorAt(x, y, new Color(tmp, tmp, tmp, cc.getAlpha()));
            }
        }
        return ret;
    }
    
    /**
     * Draw a progress bar given various parameters.
     * 
     * @param tot   Total value of the progress bar (max value)
     * @param rem   Remaining value of the progress bar (current value)
     * @param len   Length of progress bar in pixels
     * @param hig   Height of progress bar in pixels
     * @param before    Base color of progress bar
     * @param after Color of "current value".
     * @return  GreenfootImage  Returns the progress bar as an GreenfootImage.
     */
    public static GreenfootImage drawProgressBar(int tot, int rem, int len, int hig, Color before, Color after){//Draw Hp bar
        GreenfootImage i = new GreenfootImage(len, hig);
        i.setColor(before);
        i.fillRect(0, 0, len, hig);
        i.setColor(after);
        double perc = ((double)rem/tot);
        i.fillRect((int)(perc*len), 0, len, hig);
        return i;
    }
    
    /**
     * Set the transparency of an image according to brt.
     * 
     * @param img   The given image
     * @param perc  The percentage of transparency, ranges from [0 to 100], 0 = fully not transparent.
     * @return GreenfootImage   The processed input image that is either shaded or brightened by brt.
     */
    public static GreenfootImage setTransparency(GreenfootImage img, int perc){
        if(perc<=0 || perc>100)
            return img;
        GreenfootImage ret = new GreenfootImage(img);
        for(int x=0; x<ret.getWidth(); x++){
            for(int y=0; y<ret.getHeight(); y++){
                Color cc = ret.getColorAt(x, y);
                ret.setColorAt(x, y, new Color(cc.getRed(), cc.getGreen(), cc.getBlue(), ((100-perc)*255)/100));
            }
        }
        return ret;
    }
    
    /**
     * Set the brightness of an image according to brt.
     * 
     * @param img   The given image
     * @param brt   The brightness factor. less than 50 means shade, more than 50 means brighten.
     * @return GreenfootImage   The processed input image that is either shaded or brightened by brt.
     */
    public static GreenfootImage setBrightness(GreenfootImage img, int brt){
        GreenfootImage ret = new GreenfootImage(img);
        if(brt==50)
            return ret;
        else if(brt>50 && brt<=100)
            return brighten((brt-50)/50.0, ret);
        else if(brt>=0)
            return shade(brt/50.0, ret);
        return ret;
    }
    
    /**
     * Turns an image to red by perc percent.
     * 
     * @param img   The given image
     * @param perc  The percentage of turning red ranging from [0 to 100], 100 = fully red.
     * @return GreenfootImage   The processed input image that is turned red by perc percent.
     */
    public static GreenfootImage turnRed(GreenfootImage img, int perc){
        if(perc<=0 || perc>100)
            return img;
        GreenfootImage ret = new GreenfootImage(img);
        for(int x=0; x<ret.getWidth(); x++){
            for(int y=0; y<ret.getHeight(); y++){
                Color cc = ret.getColorAt(x, y);
                int r = cc.getRed(), dr = (r+((255-r)*perc)/100);
                int g = cc.getGreen(), dg = (g*perc)/100;
                int b = cc.getBlue(), db = (b*perc)/100;
                int a = cc.getAlpha();
                ret.setColorAt(x, y, new Color(dr, dg, db, a));
            }
        }
        return ret;
    }

    /**
     * Turns an image to blue by perc percent.
     * 
     * @param img   The given image
     * @param perc  The percentage of turning blue ranging from [0 to 100], 100 = fully blue.
     * @return GreenfootImage   The processed input image that is turned blue by perc percent.
     */
    public static GreenfootImage turnBlue(GreenfootImage img, int perc){
        if(perc<=0 || perc>100)
            return img;
        GreenfootImage ret = new GreenfootImage(img);
        for(int x=0; x<ret.getWidth(); x++){
            for(int y=0; y<ret.getHeight(); y++){
                Color cc = ret.getColorAt(x, y);
                int r = cc.getRed(), dr = (r*perc)/100;
                int g = cc.getGreen(), dg = (g*perc)/100;
                int b = cc.getBlue(), db = (b+((255-b)*perc)/100);
                int a = cc.getAlpha();
                ret.setColorAt(x, y, new Color(dr, dg, db, a));
            }
        }
        return ret;
    }
    
    /**
     * Reverses the color of a given image by a certain percentage.
     * 
     * @param img   The given image
     * @param perc  The percentage of inversion ranging from [0 to 100], 100 = fully reverse.
     * @return GreenfootImage   The processed input image that is reversed by perc percent.
     */
    public static GreenfootImage revColor(GreenfootImage img, int perc){
        if(perc<=0 || perc>100)
            return img;
        GreenfootImage ret = new GreenfootImage(img);
        for(int x=0; x<ret.getWidth(); x++){
            for(int y=0; y<ret.getHeight(); y++){
                Color cc = ret.getColorAt(x, y);
                int r = cc.getRed(), dr = (Math.abs(r-(255-r))*perc)/100;
                int g = cc.getGreen(), dg = (Math.abs(g-(255-g))*perc)/100;
                int b = cc.getBlue(), db = (Math.abs(b-(255-b))*perc)/100;
                int a = cc.getAlpha();
                int fr = r>(255-r) ? r-dr : r+dr;
                int fg = g>(255-g) ? g-dg : g+dg;
                int fb = b>(255-b) ? b-db : b+db;
                ret.setColorAt(x, y, new Color(fr, fg, fb, a));
            }
        }
        return ret;
    }
    
    /**
     * <p>Shades image (makes more black) according to shadeFactor.
     * <p>Usage: background = new GreenfootImage(SparkleEngine.shade(0.5, background));
     * 
     * @param shadeFactor  The factor of shade, ranges from [0 to 1.0]. 1.0 = completely black.
     * @param img           The input image
     * @return GreenfootImage   The input image, shaded by factor of brighten.
     */
    public static GreenfootImage shade(double shadeFactor, GreenfootImage img){
        GreenfootImage ret = new GreenfootImage(img);
        for(int x=0; x<ret.getWidth(); x++){
            for(int y=0; y<ret.getHeight(); y++){
                Color cc = ret.getColorAt(x, y);
                int r = cc.getRed();
                int g = cc.getGreen();
                int b = cc.getBlue();
                int a = cc.getAlpha();
                cc = new Color((int)(r*(shadeFactor)), (int)(g*(shadeFactor)), (int)(b*(shadeFactor)), a);
                ret.setColorAt(x, y, cc); 
            }
        }
        return ret;
    }
    
    /**
     * <p>Brightens image (makes more white) according to brightFactor.
     * <p>Usage: background = new GreenfootImage(SparkleEngine.brighten(0.5, background));
     * 
     * @param brightFactor  The factor of brighten, ranges from [0 to 1.0]. 1.0 = completely white.
     * @param img           The input image
     * @return GreenfootImage   The input image, brightened by factor of brighten.
     */
    public static GreenfootImage brighten(double brightFactor, GreenfootImage img){
        GreenfootImage ret = new GreenfootImage(img);
        for(int x=0; x<ret.getWidth(); x++){
            for(int y=0; y<ret.getHeight(); y++){
                Color cc = ret.getColorAt(x, y);
                int r = cc.getRed();
                int g = cc.getGreen();
                int b = cc.getBlue();
                int a = cc.getAlpha();
                cc = new Color((int)(r+(255-r)*(brightFactor)), (int)(g+(255-g)*(brightFactor)), (int)(b+(255-b)*(brightFactor)), a);
                ret.setColorAt(x, y, cc); 
            }
        }
        return ret;
    }
    
    /**
     * <p>Get force of friction of object in N
     * <p>Helpful in calculating the needed force to maintain velocity, etc.
     * <p>Remember: to maintain constant velocity: cva = getFrict()/mass
     * <p>(cva = acceleration to maintain constant velocity)
     * <p>Only works if y axis has no force other than gravity and normal force.
     * <p>If y axis has force: real friction = getFrict() + yForce*u
     * 
     * @param mass  The mass of the object
     * @param cof   The coefficient of friction of the surface
     * @param gravity   The gravity factor
     * @return double   The force of friction of the given object.
    */
    public static double getFrict(double mass, double cof, double gravity){
        return mass*gravity*cof;
    }
    
    /**
     * <p>delta velocity due to friction
     * <p>F = m*a, Ff = mass*gravity*u (mass*gravity) = N, u = kinetic friction coefficient
     * usage: spd = spd - dvDueToFrict();
     * 
     * @param vel   Velocity of object
     * @param accel Acceleration of object
     * @param mass  Mass of object
     * @param cof   Coeficcient of friction of the surface.
     * @param gravity   The gravity factor
     * @param time  Time of the motion.
     * @return int  The change in velocity of provided object in the given time period.
    */
    
    public static double dvDueToFrict(double vel, double accel, double mass, double cof, double gravity, double time){//function to calculate the effect of friction.
        double F = mass*accel;
        double frict = mass*gravity*cof;
        return ((F - frict)/mass)*time;
    }
    
    /**
     * Gets the width of a string based on the font.
     * 
     * @param font  The font used to display the string.
     * @param text  The string of text itself.
     * @return int  The width of the string if displayed in the choosen font.
     */
    public static int getStringWidth (Font font, String text){//Borrowed & optimized code from Mr. Cohen to detect length of String.
        //Note: this method is optimized using binary search by me for better performance.
        int maxWidth = (int)(text.length() * (font.getSize()/1.20)), fontSize = font.getSize();
        int margin = fontSize/6, l = 0, r = maxWidth; //Skipped pixel; variables for binary search algorithm;
        GreenfootImage temp = new GreenfootImage (maxWidth+fontSize, fontSize);
        temp.setFont(font);
        temp.drawString (text, 0, fontSize);
        while(l<r){
            boolean found = false;
            int mid = (l+r)/2; //find middle (for binary search)
            //searching through a fontsize*fontsize space to find if font exsist
            for(int j=0; j<=fontSize-1 && !found; j+=margin)
                for (int i=0; i<=fontSize-1 && !found; i+=margin)
                    if (temp.getColorAt(mid+j, i).getAlpha() != 0)
                        found = true;
            if (found) //binary search case 1 (index too less)
                l=mid+1;
            else //binary search case 2 (index too much)
                r=mid;
        }
        return l;
    }
    
    /**
     * Takes in a string of words that needs to be alighned and automatically add '/n' to it.
     * 
     * @param in    The input string
     * @param f     The font of the string that you will use to display
     * @param mxLength  The maximum length that your string cannot exceed.
     * @return String   Returns the string, formatted so that it will continue on next line if exceeds mxLength.
     */
    public static String wordWrap(String in, Font f, int mxLength){//Method to convert a string to an automatically wraped string.
        int ind = 1; //current index
        String curWord = "", cur = in.charAt(0)+"", out = ""; //Initial strings
        while(ind<in.length()){ //Iterate through the text until the entire text is checked
            while(getStringWidth(f, cur)<mxLength){ //prevent overflow
                if(ind>=in.length()){//if index is more than the text size, break immediately
                    cur += curWord;
                    break;
                }
                if(in.charAt(ind)==' '){ //detect word
                    if(getStringWidth(f, cur+curWord)>=mxLength){//additional detection of overflow
                        ind -= curWord.length()-1;//if overflow, go back to the previous word (not including the "space" in [0]).
                        break;//break out of while loop
                    }
                    cur += curWord;//add current word to current string
                    curWord = "";//reset current word
                }else if(in.charAt(ind)=='\n'){//additional detection of new line
                    ind++; //if new line, go to next character and break the loop.
                    break;
                }
                curWord += in.charAt(ind);//add next letter to current word
                ind++;
            }
            out+=cur+"\n";//add current string to output string, and add an "\n" to represent next line.
            if(ind<in.length()){//if index is less than the text, continue to iterate.
                cur = in.charAt(ind)+""; 
                curWord = "";
                ind++;
            }
        }
        return out;
    }
    
    /**
     * Draws label on an GreenfootImage
     * 
     * @param c     The color of the lable
     * @param f     The font of the lable
     * @param x     The x position (relative to top left of the object) of the lable
     * @param y     The y position (relative to top left of the object) of the lable.
     * @param text  The text on the lable
     * @return GreenfootImage   Returns the image with lable drawn on it.
     */
    public static GreenfootImage drawLable(Color c, Font f, int x, int y, String text, GreenfootImage in){
        GreenfootImage ret = new GreenfootImage(in);
        ret.setFont(f); ret.setColor(c);
        ret.drawString(text, x, y);
        return ret;
    }
    
    /**
     * Get the Manhatten Distance of two objects.
     * 
     * @param fromX     The X coordinate of source object
     * @param fromY     The Y coordinate of source object
     * @param toX       The X coordinate of target object
     * @param toY       The Y coordinate of target object
     * @return int      Returns the manhatten distance between from object and to object.
     */
    public static int ManhattenDistance(int[] from, int[] to){
        return Math.abs(from[0]-to[0])+Math.abs(from[1]-to[1]);
    }
}
