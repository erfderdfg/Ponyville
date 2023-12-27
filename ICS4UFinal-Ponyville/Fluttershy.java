import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * This is Fluttershy class, which is the class for the first pony boss
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class Fluttershy extends Enemy{
    private int x, meth, timer, prevAng, cnt;
    private double ang; 
    private SideWorld sw;
    private static int count;
    
    //meth: which attack pattern will use 
    private GreenfootImage gf;
    /**
     * This is the constructor of Fluttershy class
     * 
     * @param meth This int will determine which damage method it will use
     * param duration This int will determine the time this boss exist
     */
    public Fluttershy(int meth, int duration){
        x = 0; this.meth = meth;
        cnt = 0; ang = 0; timer = duration;
        gf = new GreenfootImage("MainPony/FS-1.png");
        gf.scale(150, 150);
        setImage(gf);
    }
    
    /**
     * This method will increase  the count variable by one
     */
    public static void countplus(){
        count++;
    }
    
    /**
     * This method will reset the count variable since it's a static variable.
     */
    public static void rsetCount(){
        count = 0;
    }
    
    private void phase1Method1(){
        double fx = ((-1/10.0)*x + 28/3.0); //generation (angle) function
        ang = ang+fx;
        int xpos = this.getX(), ypos = this.getY();
        for(int i=0; i<4; i++){//function to generate bullet for first spell card.
            double theta = 180-140/3 + i*90;//angle offset
            //set speed to 3.46 + x/584.0 so that the speed of bullets are increasing.
            getWorld().addObject(new Bullet_Undirected(0, (3.46+x/584.0), (int)(ang+theta), 8, 17, xpos, ypos, false), xpos, ypos);
        }
    }
    
    private void phase2Method1(){
        double fx = x*x*0.7;
        ang = ang+fx;
        for(int i=0; i<3; i++){
            getWorld().addObject(new Bullet_Undirected(0, 1.5, (int)(i*120+ang), 8, 17, getX(), getY(), false), getX(), getY());            
        }
        for(int i=0; i<3; i++){
            getWorld().addObject(new Bullet_Undirected(0, 3, (int)(i*120+ang+10), 8, 17, getX(), getY(), false), getX(), getY());            
        }
    }
    
    private void phase1ATK(){
        if(cnt==0){
            phase1Method1();
            x+=2; cnt = 4;
        }else{
            cnt--;
        }
    }
    
    private void phase2ATK(){
        if(cnt==0){
            x++; cnt = 8;
            phase2Method1();
        }else{
            cnt--;
        }
    }
    
    /**
     * This method will create a sideworld object and remove all the Picture object in SideWorld
     * 
     * @param w The world the Fluttershy is about to be add in
     */
    public void addedToWorld(World w){
        sw = (SideWorld)w;
        ArrayList<Picture> p = (ArrayList<Picture>)(sw.getObjects(Picture.class));
        for(Picture x: p){
            sw.removeObject(x);
        }
    }
    
    /**
     * This is the act method of Fluttershy class
     */
    public void act(){
        timer--;
        switch(meth){
            case 1:
                phase1ATK();
                break;
            case 2:
                phase2ATK();
                break;
        }
        if(timer==0){
            ((SideWorld)getWorld()).remAllBullets();
            if(count >= 1){
                ((SideWorld)getWorld()).setConversation();
            }
            sw.setContinueChooseLine(true);
            sw.setSpeakFirst();
            sw.setContinueChoose(false);
            sw.addObject(new Picture(0), 600, 150);
            getWorld().removeObject(this);
        }
            
    }
}
