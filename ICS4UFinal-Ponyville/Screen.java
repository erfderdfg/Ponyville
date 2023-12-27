import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the super class for screen
 * 
 * @author George Lu
 * @version 1.0
 */
public class Screen extends SuperSmoothMover
{
    private GreenfootImage image;
    /**
     * This is the constructor for screen class
     */
    public Screen(){
        image = new GreenfootImage("DialogueBox.png");
        image.scale(700,460);
        setImage(image);
    }
}


