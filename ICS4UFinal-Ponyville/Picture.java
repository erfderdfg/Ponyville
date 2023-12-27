import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is the class that will show the picture of the pony boss
 * 
 * @author George 
 * @version 1.0
 */
public class Picture extends Enemy
{
    private GreenfootImage[] bigHead;
    /**
     * This is the constructor of Picture class
     * 
     * @param x This int will determine which pony it will show
     */
    public Picture(int x){
        bigHead = new GreenfootImage[3];
        bigHead[0] = new GreenfootImage("MainPony/FS-1.png");
        bigHead[1] = new GreenfootImage("MainPony/PP-1.png");
        bigHead[2] = new GreenfootImage("MainPony/TS-1.png");
        for(int i = 0; i < 3; i++){
            bigHead[i].scale(150, 150);
        }
        setImage(bigHead[x]);
    }
}
