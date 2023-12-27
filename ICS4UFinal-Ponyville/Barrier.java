import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Barrier that blocks both player and chaser.
 * This is literally just a picture.
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class Barrier extends Actor{
    
    private GreenfootImage img;
    
    /**
     * Perform initialization when added to world because it needs the map object. The barrier is just supposed to be a placeholder so there isn't much to talk about.
     * 
     * @param w     The world in which the object is going to be added to.
     */
    public void addedToWorld(World w){
        decideLook();
        setImage(img);
    }
    
    /**
     * Decides the look of barrier according to current level.
     */
    private void decideLook(){
        img = new GreenfootImage("Barriers/"+Statics.getLevel()+".png");
        img.scale(((MainWorld)getWorld()).getMap().getSz()[0], ((MainWorld)getWorld()).getMap().getSz()[1]);
    }
}
