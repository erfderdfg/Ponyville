import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * For things that moves in turns (only moves when player moves).
 * Some common methods are displayed here.
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public abstract class MovingInTurns extends SuperSmoothMover{
    protected double[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    protected int[] realPos, prevPos;
    protected int dir, xx;
    protected double spd;
    protected boolean moving;
    
    /**
     * This is the act method of MovingInTurns class
     */
    public void act(){
        move();
        display();
    }
    
    /**
     * Moving the object
     */
    protected abstract void move();
    
    /**
     * Displaying animation or anything similar of the object.
     */
    protected abstract void display();
    
    /**
     * Animation that moves the character towards the target grid (so the character does not appear to be "teleported" to grids.
     * 
     * @param gridPos   The current position of character on grid {x, y}
     */
    protected void shift(int[] gridPos){
        if(dir==1 || dir==3){
            if(prevPos[0]+dirs[dir][0]*spd*xx<=realPos[0] && prevPos[1]+dirs[dir][1]*spd*xx<=realPos[1]){
                setLocation((double)prevPos[0]+dirs[dir][0]*spd*xx, (double)prevPos[1]+dirs[dir][1]*spd*xx);
                xx++;
            }else{
                rsetPos(gridPos);
            }
        }else{
            if(prevPos[0]+dirs[dir][0]*spd*xx>=realPos[0] && prevPos[1]+dirs[dir][1]*spd*xx>=realPos[1]){
                setLocation((double)prevPos[0]+dirs[dir][0]*spd*xx, (double)prevPos[1]+dirs[dir][1]*spd*xx);
                xx++;
            }else{
                rsetPos(gridPos);
            }
        }
    }
    
    /**
     * Set the position of character on grid to the target grid.
     * Used by shift() method when the target position (animation) is reached or exceeded.
     */
    protected void rsetPos(int[] gridPos){
        moving = false; xx = 0;
        setLocation(realPos[0], realPos[1]);
        prevPos = realPos.clone();
    }
}
