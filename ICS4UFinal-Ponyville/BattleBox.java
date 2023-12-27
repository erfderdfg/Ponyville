import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The box that displays the boundary of player's movement.
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class BattleBox extends Actor{
    /**
     * <p>Initializes the image of the battle box.</p>
     * <p>This box is just an indicator so it should do nothing other than being a picture.</p>
     */
    public BattleBox(){
        GreenfootImage img = new GreenfootImage(650, 410);
        img.drawRect(1, 1, 648, 408);
        setImage(img);
    }
}
