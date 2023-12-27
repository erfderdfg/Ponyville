import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the enemy class, it is the superclass for all the enemy objects
 * 
 * @author George Liu
 * @version 1.0
 */
public class Enemy extends SuperSmoothMover
{
    protected int hp;
    
    /**
     * This is the method will decrease the enemy's hp according to the damage being input
     * 
     * @param x This is the damage value
     */
    public void damage(int x){
        hp -= x;
    }
    
}
