import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Frame that displays the best time of pass.
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class Frame extends Actor{
    private Label l;
    
    /**
     * Initializes the frame, it's only job is to display the current fastest passing speed, so there's nothing much to talk about.
     */
    public Frame(){
        GreenfootImage img = new GreenfootImage("DialogueBox.png");
        img.scale(650, 250);
        setImage(img);
    }
    
    /**
     * Removes the frame. However, before removing, it should also remove the lable that comes with it.
     */
    public void removeSelf(){
        getWorld().removeObject(l);
        getWorld().removeObject(this);
    }
    
    /**
     * Adds a lable that displays text when added to world.
     * 
     * @param w This will be teh world the object is about to be added
     */
    public void addedToWorld(World w){
        getWorld().addObject(l = new Label("Fastest Time: \n"+Statics.timeToString(), 40), getX(), getY());
    }
}
