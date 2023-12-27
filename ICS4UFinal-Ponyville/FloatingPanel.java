import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A panel that hints the user to perform actions (either display z)
 *                                                (or need more orbs)
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class FloatingPanel extends Actor{
    private boolean fadeIn, fadeOut;
    private int curTransparency, targTransparency;
    private int[] plC;
    private GreenfootImage[] img;
    
    /**
     * Constructing a floating panel according to player coordinate and word choise.
     * 
     * @param plC   The current coordinate of player. Once the player leaves, the floatingPanel will delete itself.
     * @param sel   The thing that this floatingPanel will display. 2 = need more orb, 1 = press z.
     */
    public FloatingPanel(int[] plC, int sel){
        this.plC = plC.clone();
        GreenfootImage timg = new GreenfootImage("textures/Box.png");
        timg.scale(sel==2?160:100, 40);
        timg = SparkleEngine.drawLable(Color.BLACK, new Font(20), sel==2 ? 14:18, 28, sel==2 ? "need more orb":"press z", timg);
        curTransparency = 75;
        fadeIn = true;
        img = new GreenfootImage[76];
        for(int i=25; i<=100; i++){
            img[i-25] = new GreenfootImage(timg);
            img[i-25].setTransparency(((100-i)*255)/100);
        }
        setImage(img[75]);
    }
    
    /**
     * floatingPanel does only one thing: fades in when created, fades out when deleating.
     */
    public void act(){
        if(plC[0]!=Statics.getPlayerCoords()[0] || plC[1]!=Statics.getPlayerCoords()[1])
            fadeOut = true;
        if(fadeIn){
            curTransparency-=2;
            if(curTransparency<=0){
                fadeIn = false;
                curTransparency = 0;
            }
            setImage(img[curTransparency]);
        }
        if(fadeOut){
            curTransparency+=2;
            setImage(img[Math.min(curTransparency, 75)]);
            if(curTransparency>=75)
                getWorld().removeObject(this);
        }
    }
}
