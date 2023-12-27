import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The laser class
 * 
 * @author George Lu
 * @version (a version number or a date)
 */
public class LightRay extends Weapon
{
    private GreenfootImage lightRay, base;
    private boolean start, damage;//damage will tell if the lightray will start damage
    private int direction, width, warnTime, duration;
    private int damageTime;//This will check the gap it damage the hitbox
    private GreenfootSound laserSound;
    
    /**
     * This is the constructor of the LigthRay class
     * 
     * @param direction This will determine the direction the light ray will point to
     * @param width This will determine the width of the light ray
     * @param warnTime This will dtermine the about of warn time before actual shoot, unit in acts
     * @param duration This will determine the duration of the light ray
     */
    public LightRay(int direction, int width, int warnTime, int duration){
        laserSound = new GreenfootSound("SE/laser.wav");
        base = new GreenfootImage("laser.png");
        for(int x=0; x<base.getWidth(); x++){
            for(int y=0; y<base.getHeight()/2; y++){
                Color cc = base.getColorAt(x, y);
                base.setColorAt(x, y, new Color(cc.getRed(), cc.getGreen(), cc.getBlue(), 0));
            }
        }
        lightRay = new GreenfootImage(base);
        setRotation(direction);
        this.width = width;
        this.warnTime = warnTime;
        damageTime = 15;
        this.duration = duration;
    }
    
    public void stopped(){
        laserSound.stop();
    }
    
    public void started(){
        if(warnTime<0)
            laserSound.playLoop();
    }
    
    /**
     * This will set the start to true
     * 
     * @param w This is the world the object is about to enter
     */
    public void addedToWorld(World w){
        start = true;
    }
    
    /**
     * This is the act method of the LightRay class
     */
    public void act()
    {
        warnIt();
        warnTime--;
        if(warnTime < 0){
            laserSound.setVolume(30);
            laserSound.playLoop();
            lightRay = new GreenfootImage(base);
            lightRay.scale(width, 2000);
            setImage(lightRay);
            damage = true;
        }
        if(damage){
            damageTime--;
            damageThem();
        }
        remove();
    }
    
    private void warnIt(){
        if(start && warnTime > 0){
            lightRay = new GreenfootImage(base);
            lightRay.scale(5, 2000);
            setImage(lightRay);
        }
    }
    //if have time, create an animation to make a smooth transition
    
    private void damageThem(){
        if(damageTime%5 == 0){
            if(isTouching(HitBox.class)){
                Statics.setHP(Statics.getHP()-1);
            }
        }
    }
    
    private void remove(){
        if(warnTime == -1*duration){
            laserSound.stop();
            getWorld().removeObject(this);
        }
    }
}
