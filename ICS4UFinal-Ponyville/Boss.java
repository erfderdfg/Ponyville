import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Final boss that releases bullets and shifts to phase 2 when hp is lower than 3000.
 * 
 * @author George && Thomas
 * @version 1.0
 */
public class Boss extends Enemy
{
    private int x, cnt, shootCnt;
    private double prevAng;
    private boolean rev;
    private SideWorld sw;
    private boolean addSidePlane;
    
    //meth: which attack pattern will use 
    private GreenfootImage gf;
    /**
     * This is the constructor for Boss class
     */
    public Boss(){
        x = 0; hp = 3000; shootCnt = 0;
        cnt = 0; prevAng = 0;
        gf = new GreenfootImage("blackball.png");
        gf.scale(150, 150); rev = false;
        setImage(gf);
    }
    
    /**
     * This method will return the hp of current Boss object
     * 
     * #return int This is the hp of Boss
     */
    public int getHp(){
        return hp;
    }
    
    /**
     * This method will decrease the Boss's hp according to the damage being put in
     * 
     * @param x This is the damage being put in
     */
    public void changeHp(int x){
        GreenfootSound st = new GreenfootSound("SE/enemy_damaged.wav");
        st.setVolume(75);
        st.play();
        hp -= x;
    }
    
    private double phase1Method1(double prev){
        x++;
        if(prev<-7){//swing the "lines" of bullets, reverse swing if necessary.
            prev = -7;
            rev = true;
        }else if(prev>7){
            prev = 7;
            rev = false;
        }
        prev += (rev ? 1 : -1);
        int xpos = 600, ypos = this.getY();
        for(int i=0; i<3; i++){//generating the 4 "lines" of bullets on the side
            int theta = (i-1)*80;
            getWorld().addObject(new Bullet_Undirected(0, 11, (int)(90+prev)+theta, 8, 17, xpos-xpos/3, ypos-50, false), xpos-xpos/3, ypos-50);
            getWorld().addObject(new Bullet_Undirected(0, 11, (int)(90-prev)+theta, 8, 17, xpos+xpos/3, ypos-50, false), xpos+xpos/3, ypos-50);
            getWorld().addObject(new Bullet_Undirected(0, 11, (int)(90+prev)+theta, 8, 17, xpos-3*(xpos/4), ypos+150, false), xpos-3*(xpos/4), ypos+150);
            getWorld().addObject(new Bullet_Undirected(0, 11, (int)(90-prev)+theta, 8, 17, xpos+3*(xpos/4), ypos+150, false), xpos+3*(xpos/4), ypos+150);
        }
        if(prev%7==0 && prev!=0){//The other bullets
            int tmp = (int)(Math.random()*18);
            for(int i=0; i<20; i++){
                getWorld().addObject(new Bullet_Undirected(0, (2.13+x/867.0), i*18+tmp, 8, 17, xpos-3*(xpos/4), ypos+150, false), xpos-3*(xpos/4), ypos+150);
                getWorld().addObject(new Bullet_Undirected(0, (2.13+x/867.0), i*18-tmp, 8, 17, xpos+3*(xpos/4), ypos+150, false), xpos+3*(xpos/4), ypos+150);
            }
        }
        return prev;
    }
    
    private void phase1ATK(){
        if(cnt==0){
            cnt = 2; 
            prevAng = phase1Method1(prevAng);
        }else{
            cnt--;
        }
    }
    
    /**
     * This method will create a world object when Boss is added to world
     * 
     * @param w This current world
     */
    public void addedToWorld(World w){
        sw = (SideWorld)w;
        
    }
    
    private void phase2Method1(){
        int tmp = Greenfoot.getRandomNumber(3);
        for(int i=0; i<20; i++){
            getWorld().addObject(new GenerateBullet(50, 2.5, i*18+(rev?9:0)+tmp, 8, 12, getX(), getY(), false), getX(), getY());
        }
    }
    
    private void phase2Method2(){
        for(int i=0; i<10; i++){
            getWorld().addObject(new LightRay(i*36+18, 30, 60, 90), getX(), getY());
        }
    }
    
    private void phase2ATK(){
        if(cnt==0){
            if(shootCnt==0)
                phase2Method2();
            cnt = 6; rev = !rev;
            shootCnt++;
            phase2Method1();
            if(shootCnt%6==0)
                cnt = 90;
            if(shootCnt==12)
                shootCnt =0 ;
        }else{
            cnt--;
        }
    }
    
    /**
     * This is the act method for Boss class
     */
    public void act(){
        if(hp>1500)
            phase1ATK();
        else
            phase2ATK();
    }
    
    
}
