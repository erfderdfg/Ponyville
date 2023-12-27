import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player's hit box at battle screen.
 * 
 * @author George Lu
 * @version 2023/6/1 
 */
public class HitBox extends SuperSmoothMover
{
    private GreenfootImage heart;
    private SideWorld sw;
    private boolean start;
    private int time, damageTime;//time is for the gap between each shoot, damageTime is for the gap between each damage
    private int hp;
    private boolean final1;
    private final int speed = 4;
    
    /**
     * This is the constructor for the HitBox class
     * 
     * @param final1 This boolean will determine whether the hitBox can shoot or not
     */
    public HitBox(boolean final1){
        heart = new GreenfootImage("The Heart.png");
        heart.scale(27, 27);
        setImage(heart);
        time = 5;
        damageTime = 5;
        this.final1 = final1;
        Statics.setHP(40);
    }
    
    /**
     * This is the act method of HitBox class
     */
    public void act(){
        move();
        if(final1){
            shoot();
            success();
        }
        time--;
        damageTime--;
        dead();
    }
    
    /**
     * This method will create a SideWorld object and make the start boolean true
     * 
     * @param w This is the world the object is about to be add in
     */
    public void addedToWorld(World w){
        sw = (SideWorld)w;
        start = true;
    }
    
    private boolean chk(int x, int y){
        if(y>275 && y<675 && x<925 && x>275)
            return true;
        return false;
    }
    
    private void move(){
        int dirX = 0, dirY = 0;
        if (Greenfoot.isKeyDown("up"))
            dirY -= speed;
        if (Greenfoot.isKeyDown("down"))
            dirY += speed;
        if (Greenfoot.isKeyDown("left"))
            dirX -= speed;
        if (Greenfoot.isKeyDown("right"))
            dirX += speed;
        if (dirX != 0 && dirY != 0){
            if(chk(getX()+dirX/2, getY()+dirY/2))
                setLocation(getX()+dirX/2, getY()+dirY/2);
        }else{
            if(chk(getX()+dirX, getY()+dirY))
                setLocation(getX()+dirX, getY()+dirY);
        }
    }
    
    private void shoot(){
        if(Greenfoot.isKeyDown("z") && (time % 5 == 0)){
            GreenfootSound st = new GreenfootSound("SE/player_Shoot.wav");
            st.setVolume(75);
            st.play();
            getWorld().addObject(new Bullet_Undirected(1, 20, 270, 8, 12, getX(), getY(), true), getX(), getY());
        }
        
    }
    
    private void dead(){
        if(Statics.getHP() <= 0 && !final1){
            Statics.setLevel(2); ((SideWorld)getWorld()).unMusic();
            Greenfoot.setWorld(new MainWorld());
        }else if(final1 && start && Statics.getHP() <= 0){
            ((SideWorld)getWorld()).unMusic();
            EndWorld ew = new EndWorld(false);
            ew.setEnd();
            Greenfoot.setWorld(ew);
        }
    }
    
    private void success(){
        if(final1 && start && sw.getBoss().getHp() < 0){
            ((SideWorld)getWorld()).unMusic();
            EndWorld ew = new EndWorld(true);
            ew.setEnd();
            Greenfoot.setWorld(ew);
        }
    }
    
}
