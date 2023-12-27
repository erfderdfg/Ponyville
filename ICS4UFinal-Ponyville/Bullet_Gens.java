import greenfoot.*;
/**
 * Bullet generator which generates bullets / lasers.
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class Bullet_Gens extends RemovableBullet{
    private int cnt, live, dx;
    private double subPixPosX, subPixPosY, subPixIncX, subPixIncY, dv, ang;
    
    /**
     * This is the constructor for Bullet_Gens class
     * 
     * @param facing    The second speed vector of the object, controlling direction with another moving machenic indipendent of move(), intended to be used for bullets that goes in circles while expanding outwards.
     * @param rotation  The direction of the object, controlling direction by move() method.
     * @param x         x coordinate of the object at generation
     * @param y         y coordinate of the object at generation
     * @param speed     speed of the second vector.
     */
    public Bullet_Gens(int facing, int rotation, int x, int y, int speed){
        subPixPosX = x; subPixPosY = y;
        subPixIncX = Math.cos((facing)*(Math.PI/180))*speed;
        subPixIncY = Math.sin((facing)*(Math.PI/180))*speed;
        setImage(draw());
        setLocation(x, y);
        setRotation(rotation);
        dv = 0; cnt = 0;
        live = 180; dx = 0; ang = 0;
    }
    
    /**
     * This is the act method of Bullet_Gens class
     */
    public void act(){
        if(this.isTouching(HitBox.class)){
            getWorld().removeObject(this);
            Statics.setHP(Statics.getHP()-1);
            //TODO: If HP <=0, send to start of the world.
            return;
        }
        if(subPixPosX>=10 && subPixPosX<=1190 && subPixPosY>=10 && subPixPosY<=665){
            cnt++;
            dv += cnt*0.005;
            subPixPosX+=subPixIncX; subPixPosY+=subPixIncY;
            setLocation((int)subPixPosX, (int)subPixPosY);
            move(dv);
            subPixPosX = getPreciseX(); subPixPosY = getPreciseY();
        }else{
            live--; dx++;
            if(live%30==0){
                double fx = ((7/2.0)*dx);
                ang = ang+fx;
                for(int i=0; i<6; i++)
                    getWorld().addObject(new Bullet_Undirected(0, 3, (int)(ang+i*60), 1, 15, getX(), getY(), false), getX(), getY());
            }
            if(live<0)
                getWorld().removeObject(this);
        }
    }
    
    private GreenfootImage draw(){
        int size = 25;
        GreenfootImage image = new GreenfootImage(size*2/2, size*3/3);
        image.setColor(new Color(231, 76, 60));
        image.fillOval(0, 0, size*2/2, size*3/3);
        image.setColor(Color.WHITE);
        image.drawOval(0, 0, size*2/2, size*3/3);
        return image;
    }
}
