import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Thing that generates bullets (used as Twilight's second attack)
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class Generate2 extends RemovableBullet{
    private int activeCnt, x;
    
    /**
     * This is the constructor for Generate2 class
     * 
     * @param activeCnt Number of acts that the generator will follow the player
     */
    public Generate2(int activeCnt){
        this.activeCnt = activeCnt;
        GreenfootImage tmp = new GreenfootImage("generate.png");
        tmp.scale(75, 75); x = 60;
        tmp.setTransparency(127);
        setImage(tmp);
    }
    
    /**
     * This is the act method of the Generate2 class
     */
    public void act(){
        if(activeCnt!=0)
            activeCnt--;
        else{
            if(x==0){
                getWorld().removeObject(this);
                return;
            }
            if(x%24==0){
                for(int i=0; i<4; i++){
                    getWorld().addObject(new Bullet_Undirected(0, 2.5, (int)(Greenfoot.getRandomNumber(30)+i*90), 1, 15, getX(), getY(), false), getX(), getY());
                }
            }
            x--;
        }
    }
}
