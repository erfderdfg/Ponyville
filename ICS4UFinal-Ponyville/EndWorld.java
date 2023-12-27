import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The EndWorld is the very last world that displays the result of the player's adventure.
 * 
 * @author Yawen Zhang
 * @version 0.1
 */
public class EndWorld extends World
{
    private boolean end;
    private Label instruction;
    private GreenfootSound bgm;
    /**
     * Initializes the EndWorld according to result.
     * 
     * @param result    The result of the world, true = good, false = bad.
     */
    public EndWorld(boolean result)
    {
        super(1200, 675, 1, false);
        if(result)
            Statics.saveTime();
        bgm = new GreenfootSound("bgm-end.mp3");
        GreenfootImage img = new GreenfootImage(result?"EndWorldGood.jpg":"EndWorldBad.jpg");
        img.scale(1200, 675);
        setBackground(img);
        music();
    }
    
    /**
     * When started, play music.
     */
    public void started(){
        music();
    }
    
    /**
     * When stopped, don't play music.
     */
    public void stopped(){
        unMusic();
    }
    
    /**
     * Don't play music.
     */
    private void unMusic(){
        bgm.stop();
    }
    
    /**
     * Play music.
     */
    private void music(){
        bgm.playLoop();
    }
    
    /**
     * Sets the End variable (which is deprecated).
     */
    public void setEnd(){
        end = true;
    }
}
