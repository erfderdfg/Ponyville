import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * This is TwilightSparkle class, which is the class for the first pony boss
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class TwilightSparkle extends Enemy{
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
    public TwilightSparkle(int meth, int duration){
        x = 0; this.meth = meth;
        cnt = 0; ang = 0; timer = duration;
        gf = new GreenfootImage("MainPony/TS-1.png");
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
    
    private void phase1ATK(){
        if(cnt==0){
            cnt = 600;
            getWorld().addObject(new Generate(300), ((SideWorld)getWorld()).getHitBox().getX(), ((SideWorld)getWorld()).getHitBox().getY());
        }else{
            cnt--;
        }
    }
    
    private void phase2ATK(){
        if(cnt==0){
            cnt = 60;
            getWorld().addObject(new Generate2(120), ((SideWorld)getWorld()).getHitBox().getX()-50, ((SideWorld)getWorld()).getHitBox().getY());
            getWorld().addObject(new Generate2(120), ((SideWorld)getWorld()).getHitBox().getX()+50, ((SideWorld)getWorld()).getHitBox().getY());
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
            sw.addObject(new Picture(2), 600, 150);
            getWorld().removeObject(this);
        }
            
    }
}
