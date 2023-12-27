import greenfoot.*;
/**
 * Bullet that will act as an undirected bullet, but will also generate more undirected bullets after some acts.
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class GenerateBullet extends RemovableBullet{
    private int power, size, facing, gact, gcnt;
    private double speed;
    private SideWorld sw;
    private boolean friendly;
    private double subPixPosX, subPixPosY, subPixIncX, subPixIncY;
    
    /**
     * This is the constructor for Bullet_Undirected class
     * 
     * @param gact  Number of acts before this bullet will generate more undirected bullets.
     * @param speed Speed of bullet
     * @param power How much damage (in hp) this bullet will do.
     * @param size  The physical size of the bullet in pixels
     * @param x     The x position of the bullet when generated
     * @param y     The y position of the bullet when generated
     * @param friendly  If the bullet is friendly. true = friendly.
     */
    public GenerateBullet(int gact, double speed, int facing, int power, int size, int x, int y, boolean friendly){
        this.speed = speed; this.facing = facing; this.power = power; this.size = size; this.friendly = friendly;
        setRotation(facing); this.gact = gact;
        setImage(temp()); gcnt = 3;
        setLocation(x, y);
        subPixPosX = x; subPixPosY = y;
        subPixIncX = Math.cos((facing)*(Math.PI/180))*speed;
        subPixIncY = Math.sin((facing)*(Math.PI/180))*speed;
    }
    
    /**
     * This method will create a SideWorld object when it is created
     * 
     * @param w This is the world it is about to get in
     */
    public void addedToWorld(World w){
        sw = (SideWorld)w;
    }

    /**
     * This is the act method for Undirected_Bullet
     */
    public void act(){
        gact--;
        if(gact<=0){
            if(Math.abs(gact)%8==0 && gcnt>0){
                gcnt--;
                getWorld().addObject(new Bullet_Undirected(0, speed+1, facing, power, size, getX(), getY(), false), getX(), getY());
            }
        }
        subPixPosX+=subPixIncX; subPixPosY+=subPixIncY;
        setLocation((int)subPixPosX, (int)subPixPosY);
        if(friendly){//If friendly bullet, decrease enemy hp when hitted the enemy.
            if(this.isTouching(Boss.class)){
                getWorld().removeObject(this);
                sw.getBoss().changeHp(8);
                return;
            }
        }else{//else, decrease player hp when hitted the player.
            if(this.isTouching(HitBox.class)){
                getWorld().removeObject(this);
                Statics.setHP(Statics.getHP()-1);
                //TODO: If HP <=0, send to start of the world.
                return;
            }
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
        GreenfootImage image = new GreenfootImage("RedBall.png");
        image.scale(size, size);
        return image;
    }
}
