import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Essentially something that displays left and right keys on starting world.
 * 
 * @author Yawen && Ming
 * @version 1.0
 */
public class PageIcon extends Actor
{
    GreenfootImage img;
    /**
     * This is the constructor for PageIcon class
     */
    public PageIcon() {
        GreenfootImage img = new GreenfootImage("PageIcon.png");
        img.scale(170, 100);
        setImage(img);
    }
    
    
}
