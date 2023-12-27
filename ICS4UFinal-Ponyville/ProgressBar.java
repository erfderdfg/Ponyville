import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * HP/MP bar for player
 * 
 * @author Yawen
 * @version 20230606
 */
public class ProgressBar extends Actor{
    GreenfootImage HPBar, MPBar;
    GreenfootImage img;
    int selection, barValue;
    
    /**
     * Update progress bar according to HP and MP stored in Statics.
     */
    public void act(){
        //implementBar();
        if(selection==0)
            setImage(SparkleEngine.drawProgressBar(40, Statics.getHP(), 160, 20, new Color(255, 128, 128), new Color(204, 0, 0)));
        else if(selection==1)
            setImage(SparkleEngine.drawProgressBar(100, Statics.getMP(), 160, 20, new Color(255, 153, 255), new Color(179, 0, 179)));
    }
    
    /**
     * Constructor of the progress bar, essentially selecting a desired color theme accoridng to select.
     * 
     * @param select    The selection of type. 0 = displays player's HP; 1 = displays player's MP.
     */
    public ProgressBar(int select){
        selection = select;
        if(selection==0)
            setImage(SparkleEngine.drawProgressBar(40, Statics.getHP(), 160, 20, new Color(255, 128, 128), new Color(204, 0, 0)));
        else if(selection==1)
            setImage(SparkleEngine.drawProgressBar(100, Statics.getMP(), 160, 20, new Color(255, 153, 255), new Color(179, 0, 179)));
    }
}
