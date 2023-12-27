import greenfoot.*;
/**
 * Undirected bullet (goes in a straight line and will not automatically aim to target)
 * However this bullet will turn every few whiles
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class Bullet_turn extends RemovableBullet{
    private int pict, power, size, facing;
    private double speed;
    private SideWorld sw;
    private boolean rev, revdir, flip;
    
    /**
     * This is the constructor for Banked_turn class
     * 
     * @param facing This is the rotation of the Bullet_turn class
     * @param x This is the x-coordnate of the object when generated
     * @param y This is the y-coordnate of the object when generated
     * @param flip This is the boolean determine if it is flip to the reverse direction
     */
    public Bullet_turn(int facing, int x, int y, boolean flip){
        facing += flip?(20):(-20); this.flip = flip;
        this.facing = facing;
        setRotation(facing);
        setImage(temp());
        setLocation(x, y);
        speed = -10; rev = false; revdir = false;
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
     * This is the act method of the Bullet_turn class
     */
    public void act(){
        if(speed>=0)
            move(speed/10);
        else if(!rev)
            facing += revdir?(flip?(4):(-4)):(flip?(-4):(4));
        setRotation(facing);
        if(rev)
            speed--;
        else
            speed++;
        if(speed==40)
            rev = true;
        if(speed==-10){
            rev = false;
            revdir = !revdir;
        }
            
        if(this.isTouching(HitBox.class)){
            getWorld().removeObject(this);
            Statics.setHP(Statics.getHP()-1);
            //TODO: If HP <=0, send to start of the world.
            return;
        }
        if(isEdge())//If touching edge or touching information panel, remove itself.
            getWorld().removeObject(this);
    }
    
    private boolean isEdge(){
        int x = getX(), y = getY();
        if(x<-100 || y<-100)
            return true;
        if(x>1700 || y>775)
            return true;
        return false;
    }

    private GreenfootImage temp(){
        GreenfootImage img = new GreenfootImage("candy_icon.png");
        img.scale(60, 45);
        img.rotate(90);
        return img;
    }
}
