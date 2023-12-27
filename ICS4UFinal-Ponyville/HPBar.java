import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * HP Bar of either player or boss at battle screen.
 * 
 * @author George Lu
 * @version 1.0
 */
public class HPBar extends SuperSmoothMover
{
    private GreenfootImage hpBar;
    private SideWorld sw;
    private boolean side;
    private int hpI, hpC;//hpI is the inital hp, hpC is the hp that will change
    //side = true means it will show the main ch, visaversa
    /**
     * This is the constructor of HPBar class
     * 
     * @param side This boolean will determine which one HPBar will show
     */
    public HPBar(boolean side){
    
        if(side){
            hpI = Statics.getHP();
            hpBar = SparkleEngine.drawProgressBar(hpI, Statics.getHP(), 150, 15, Color.RED, Color.BLACK);
        }
        this.side = side;
    }
    
    /**
     * This method will intialize the HPBar object
     * 
     * @param w This will be the world the HPBar about to be added
     */
    public void addedToWorld(World w){
        sw = (SideWorld)w;
        if(!side){
            hpI = sw.getBoss().getHp();
            hpBar = SparkleEngine.drawProgressBar(hpI, Statics.getHP(), 150, 15, Color.RED, Color.BLACK);
        }
        setImage(hpBar);
    }
    
    /**
     * This is the act method of HPBar
     */
    public void act()
    {
        hp();
    }
    
    private void hp(){
        if(side){
            hpC = Statics.getHP();
            hpBar = SparkleEngine.drawProgressBar(hpI, hpC, 150, 15, Color.RED, Color.BLACK);
            setImage(hpBar);
        }else{
            hpC = sw.getBoss().getHp();
            hpBar = SparkleEngine.drawProgressBar(hpI, hpC, 150, 15, Color.RED, Color.BLACK);
            setImage(hpBar);
        }
    }
}
