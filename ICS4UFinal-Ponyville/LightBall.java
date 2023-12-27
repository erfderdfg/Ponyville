import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Essentially Bullet_Undirected implemted another way.
 * 
 * @author George Lu
 * @version 1.0
 */
public class LightBall extends StraightDart
{
    private GreenfootImage lightBall;
    private Color color;
    private SideWorld sw;
    private boolean side, gone;//This boolean will determine if this lightBall is from enemy, true is from enemy, gone will determine if the pbject should be transparent
    
    /**
     * This is the constructor for LightBall class
     * 
     * @param side This will determine the side the light ball is on, true means on main character, false means enemy
     * @param direction This will determine the direction of the light ball class
     */
    public LightBall(boolean side, int direction){
        lightBall = new GreenfootImage("RedBall.png");
        lightBall.scale(15,15);
        setImage(lightBall);
        this.side = side;
        setRotation(direction);
        
    }
    /**
     * The act method of LightBall class
     */
    public void act()
    {
        move(2.8);
        
        if(!gone){
            damage();
        }
        if(gone){
            lightBall.setTransparency(0);
            setImage(lightBall);
            
        }
        if(getX() > 1250 || getX() < -50 || getY() > 700 || getY() < -25){
            getWorld().removeObject(this);
        }
        
    }
    
    /**
     * This will create a SideWorld object when the object is created
     * 
     * @param w This is the world the object is about to enter
     */
    public void addedToWorld(World w){
        sw = (SideWorld)w;
        
    }
    
    /**
     * This method will return the side of current light ball
     * 
     * @return boolean This boolean will determine the side light ball is on, true means on main character side and false means on enemy side
     */
    public boolean getSide(){
        return side;
    }
    
    private void damage(){
        if(side){
            if(isTouching(HitBox.class)){
                Statics.setHP(Statics.getHP()-1);
                gone = true;
                
            }
            
        }else{
            if(isTouching(Boss.class)){
                sw.getBoss().changeHp(8);
                gone = true;
            }
        }
        
    }
}
