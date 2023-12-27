import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.FileNotFoundException;

/**
 * <p>The starting world of this game. Introduces the background story and instruction on how to play.
 * <h1>Game Instruction</h1>
 * <p>*** Must new StartWorld() every time before playing ***</p>
 * <h3>1. For MainWorld (Map Maze):</h3>
 * <p><strong>¡ü¡ý¡û¡ú</strong> to move in the map.
 * <strong>'z'</strong> to allure the chaser to come closer.
 * <strong>alt</strong> to illuminate magic to kill chasers.
 * <p>When in contact with Ponies or Portal, press 'z' to proceed.
 * <p>Goal: find all Ponies to collect all 3 orbs (you get them after saving the Ponies), to get to next level.
 * <p>*** If can't find the Ponies, in ShaderBox class, comment //this.setImage(blk);
 * <h3>2. For SideWorld (Level Boss Conversation):</h3>
 * <p>The ponies will say something, Flurry Heart replies by selecting choice <strong>A, B, C, D</strong>.
 * <p>Conversation proceeds if Flurry Heart replied the correct thing.
 * <p>Press the continue <strong>button (¡ú)</strong> to check answer, continue to next dialogue and exit world.
 * <p>Goal: help the ponies to figure out life issues.
 * <h3>3. For SideWorld (Level Final Boss Conversation+Battle):</h3>
 * <p>Flurry Heart needs to dodge the attacks from the ponies, and then solve their life issues.
 * <p>Attacks & Conversation takes turn to happen.
 * <p>Goal: survive & help the ponies to figure out life issues.
 * <h3>4. For SideWorld (Final Boss Battle):</h3>
 * <p>Dodge attacks from the Boss.
 * <p><strong>Press 'z'</strong> to attack the Boss.
 * <p>Goal: survive & kill the Boss.
 * 
 * <h1>Background Music Citations:</h1>
 * <ul>Beginning: Prisoner to a Formula (Pokemon)</ul>
 * <ul>1st map - Nectar Meadow (Pokemon SMD Music 037)</ul>
 * <ul>2nd map - Vast Poni Canyon - Sun/Moon Disc 3 Music 37</ul>
 * <ul>3rd map - The Kalos Power Plant - XY Disc 2 Music 25</ul>
 * <ul>Boss Battle: The Bitter Cold Stage 2 (Pokemon Mystery Dungeon Gates to Infinity)</ul>
 * <ul>Final Boss: Battle (Ultra Necrozma) (Pokemon USUM)</ul>
 * <ul>Talk: An Eternal Prison (Pokemon XY)</ul>
 * <ul>Final Panel: Don't Ever Forget (Pokemon Mystery Dungeon: Explorers of Sky)</ul>
 * 
 * <h1>Sound Effect Citations:</h1>
 * <ul>All sound effects are taken from LuaSTG-EX-Plus.</ul>
 * <ul> Except for 'lightSound', which is taken from public domain.</ul>
 * 
 * <h1>Graphics Citations:</h1>
 * <ul>Farm Land: https://www.deviantart.com/the-mystery-of-doom/art/Vector-School-Grounds-294405567</ul>
 * <ul>Track Field: https://www.vectorstock.com/royalty-free-vector/cartoon-running-track-stadium-vector-17932997</ul>
 * <ul>Library Floor: https://www.freepik.com/free-photos-vectors/cartoon-floor</ul>
 * <ul>Start World: https://www.pinterest.ca/pin/535154368199443510</ul>
 * <ul>Little Pony Logo: https://www.dafont.com/forum/read/331756/my-little-pony</ul>
 * <ul>Ponies: https://pony.town/</ul>
 * <ul>Light Circle: https://www.pngegg.com/en/png-wrhcb</ul>
 * <ul>Tree: https://www.vecteezy.com/png/13743345-pixel-art-tree-icon</ul>
 * <ul>Portal: https://www.tukuppt.com/muban/yaxxwdkx.html</ul>
 * <ul>Scroll: https://www.freepik.com/free-vector/realistic-open-parchment-scroll-transparent_39845337.htm#query=old%20scroll&position=0&from_view=keyword&track=ais</ul>
 * <ul>Evil Character: https://kiray96.artstation.com/projects/5801xJ</ul>
 * <ul>Ball: https://es.pixilart.com/art/crystal-ball-a971851e516e3b3</ul>
 * <ul>Next Buttom: https://www.flaticon.com/free-icon/next-button_3318722</ul>
 * 
 * @author Molly Wu & Yawen & Xuanxi
 * @version 6.9.2023
 */

public class StartWorld extends World
{
    GreenfootImage img;
    private int count = 0;
    private Label instructionOne;
    private GreenfootSound bgm;
    private Frame f;
    /**
     * Constructor for objects of class StartWorld.
     * 
     */
    
    private void unMusic(){
        bgm.stop();
    }
    
    private void music(){
        bgm.playLoop();
    }
    
    /**
     * Initializes the StartWorld, initialize music, background image, and add a fastest time if there is a save file.
     */
    public StartWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 675, 1, false);
        // Resizing the image to world size.
        img = new GreenfootImage("StartWorld.jpg");
        img.scale(1200, 675);
        if(Statics.loadTime()!=-1)
            addObject(f = new Frame(), 900, 600);
        setBackground(img);
        instructionOne = new Label("prev    next", 30);
        bgm = new GreenfootSound("bgm-start.mp3");
        bgm.stop(); bgm.setVolume(70);
    }
    
    /**
     * Play music when started
     */
    public void started(){
        music();
    }
    
    /**
     * Stop music when stopped.
     */
    public void stopped(){
        unMusic();
    }
    
    /**
     * The world only needs to determine of left or right (or space if at first panel) is clicked. If everything else is displayed, move to MainWorld.
     */
    public void act(){
        if (count == 9){
            moveWorld();
        }
        if (count == 0) {
            if (Greenfoot.isKeyDown("space")) {
                if(f!=null)
                    f.removeSelf();
                count++;
                setBackground(new GreenfootImage(count+".jpg"));
            }
        }        
        if (count < 9 && count > 0) {
            addObject(new PageIcon(), 240, 600);
            addObject(instructionOne, 240, 640);
            moveRight();
            moveLeft();
        }
    }
    
    /**
     * Moving to MainWorld while performing initialization of variables.
     */
    public void moveWorld(){
        Statics.setLevel(1);
        Statics.setHP(40);
        Statics.setActive(false);
        Statics.setOrb(3);
        Statics.rsetStay();
        unMusic(); Statics.begin();
        Greenfoot.setWorld(new MainWorld());
    }
    
    private boolean leftKeyDown;
    private void moveLeft() {
        if (!leftKeyDown && Greenfoot.isKeyDown("left")) {
            leftKeyDown = true;
            count = Math.max(1, count-1);
            setBackground(new GreenfootImage(count+".jpg"));
        }
        if (leftKeyDown && !Greenfoot.isKeyDown("left")) {
            leftKeyDown = false;
        }
    }
    
    private boolean rightKeyDown;
    private void moveRight() {
        if (!rightKeyDown && Greenfoot.isKeyDown("right")) {
            rightKeyDown = true;
            count++;
            if(count<9)
                setBackground(new GreenfootImage(count+".jpg"));
        }
        if (rightKeyDown && !Greenfoot.isKeyDown("right")) {
            rightKeyDown = false;
        }
    }
}
