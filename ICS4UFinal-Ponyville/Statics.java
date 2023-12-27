import java.util.*;
import java.io.*;

/**
 * A class that stores static variables, could potentially be used for game saves.
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class Statics{
    //Current level (from 1 to 3), player position x, player position y.
    //Note: ppX and ppY are coordinates of Map, 
    private static int lV, ppX, ppY, MP, HP, orbReim, stay;
    private static boolean active;
    //below is use to read in all the conversations
    private static ArrayList<String> talkStorage;
    private static ArrayList<ArrayList<String>> talkStorage1;//talkStorage1 is to store each conversation one by one 
    private static String[] startLine;//this array will contain the first line of all the conversation
    private static Scanner sc;
    private static boolean moreLine;
    private static int numberOfTalk;
    private static long startTime, bestTime;
    
    /**
     * Convert a time stored in miliseconds to string.
     * 
     * @return String   The converted time. displayed in hours, minutes, seconds.
     */
    public static String timeToString(){
        long tmp = bestTime/1000;
        String hours, min, sec;
        hours = ((Long)(tmp/3600)).toString();
        tmp%=3600;
        min = ((Long)(tmp/60)).toString();
        tmp%=60;
        sec = ((Long)(tmp)).toString();
        return (hours+" Hours, "+min+" Minutes, "+sec+" Seconds");
    }
    
    /**
     * Begin timer
     */
    public static void begin(){
        startTime = System.currentTimeMillis();
    }
    
    /**
     * save the current calculated time in a file.
     */
    public static void saveTime(){
        try{
            if(bestTime==-1)
                bestTime = Long.MAX_VALUE;
            long tmp = System.currentTimeMillis()-startTime;
            PrintWriter pw = new PrintWriter(new FileWriter("./save/save.txt"));
            if(tmp<bestTime)
                pw.println(tmp);
            else
                pw.println(bestTime);
            pw.close();
        }catch(IOException e){};
    }
    
    /**
     * Loads the currently stored time in a file
     */
    public static long loadTime(){
        try{
            Scanner s = new Scanner(new FileReader("./save/save.txt"));
            if(!s.hasNextLine()){
                s.close();
                return bestTime = -1;
            }
            bestTime = Long.parseLong(s.nextLine());
            s.close();
        }catch(FileNotFoundException e){}
        return bestTime;
    }
    
    /**
     * This method will return the startline array
     * 
     * @return String[]
     */
    public static String[] getStartLine(){
        return startLine;
    }
    
    /**
     * <p>This method will read in all the conversations in the story.txt</p>
     * <p>This method should be call at the start of game</p>
     */
    public static void takeInWords(){
        if(talkStorage!=null && !talkStorage.isEmpty()){
            return;
        }
        talkStorage = new ArrayList<String>();
        talkStorage1 = new ArrayList<ArrayList<String>>();
        
        
        try{
            sc = new Scanner(new File("Story.txt"));
        }catch(FileNotFoundException e){
            System.out.println("File Not Found, Story.txt file corrupted.");
        }
        
        //use to read in all the lines
        while(!moreLine){
            try{
                talkStorage.add(sc.nextLine());
                
            }catch (NoSuchElementException e){
                moreLine = true;
            }
        }
        sc.close();
        
        //use to find the number of different enemy characters`
        for(int i = 0; i < talkStorage.size(); i++){
            String tmp = talkStorage.get(i);
            if(tmp.length() > 5 && tmp.substring(0,5).equals("Start")){
                numberOfTalk++;
            }
        }
        
        
        int c = 0;
        
        //use to put the conversation between each enemy character and the horse into diffeent arraylists
        for(int i = 0; i < numberOfTalk; i++){
            boolean go = true;
            ArrayList<String> tmp = new ArrayList<String>();
            while(go){
                
                /**FIX THIS**/
                String tmp1 = talkStorage.get(c);
                tmp.add(tmp1);
                c++;
                if(tmp1.length() > 3 && (tmp1.substring(0,3).equals("End") || c == talkStorage.size())){
                    go = false;                    
                }
                
                
                
                
            }
            
            talkStorage1.add(tmp);
            c++;
           
        }
        startLine = new String[talkStorage1.size()];
        
       
        
        for(int i = 0; i < talkStorage1.size(); i++){
            startLine[i] = talkStorage1.get(i).get(1);
            
        }
    }
    
    /**
     * Gets the talkStorage.
     * 
     * @return ArrayList<ArrayList<String>> The readed conversation file.
     */
    public static ArrayList<ArrayList<String>> getConversation(){
        
        return talkStorage1;
    }
    
    /**
     * Gets if current pony should be on map.
     * 
     * @param id    The id of pony
     * @return int  If = 1, pony should not stay.
     */
    public static int getStay(int id){
        return ((stay>>id)&1);
    }
    
    /**
     * Rests all ponies, make all ponies stay oon map.
     */
    public static void rsetStay(){
        stay = 0;
    }
    
    /**
     * Sets the pony with corresponding id to dissapear
     * 
     * @param id    The id of the pony.
     */
    public static void setStay(int id){
        stay |= (1<<id);
    }
    
    /**
     * Set active state to true if player is already inside a world (ending from normal pony battle)
     * 
     * @param in    The new state of active.
     */
    public static void setActive(boolean in){
        active = in;
    }
    
    /**
     * Returns if the player is currently active in world.
     * 
     * @return boolean  Returns true only if player is not in spawn point.
     */
    public static boolean isActive(){
        return active;
    }
    
    /**
     * Sets the number of remaining memory orbs.
     * 
     * @param val   the updated number of memory orbs.
     */
    public static void setOrb(int val){
        orbReim = val;
    }
    
    /**
     * Returns the current number of remaining memory orbs.
     * 
     * @return int  the number of memory orbs remaining.
     */
    public static int getOrb(){
        return orbReim;
    }
    
    /**
     * returns the current HP of player.
     * 
     * @return int  The current HP of player
     */
    public static int getHP(){
        return HP;
    }
    
    /**
     * sets the HP of player to the given value
     * 
     * @param val   The HP value after changing
     */
    public static void setHP(int val){
        HP = val;
    }
    
    /**
     * returns the current MP of player.
     * 
     * @return int  The current MP of player
     */
    public static int getMP(){
        return MP;
    }
    
    /**
     * sets the MP of player to the given value
     * 
     * @param val   The MP value after changing
     */
    public static void setMP(int val){
        MP = val;
    }
    
    /**
     * sets the current level (map) of player. (1, 2, 3)
     * 
     * @param lv    The current level.
     */
    public static void setLevel(int lv){
        lV = lv;
    }
    
    /**
     * returns current level (1, 2, 3);
     * 
     * @return int  The current level
     */
    public static int getLevel(){
        return lV;
    }
    
    /**
     * sets the current coordinate of player
     * 
     * @param coord     The coordinate of player
     */
    public static void setPlayerCoords(int[] coord){
        ppX = coord[0];
        ppY = coord[1];
    }
    
    /**
     * returns player's X and Y coordinate on grid
     * 
     * @return int[2]  returns player's X and Y coordinate on grid, respectively
     */
    public static int[] getPlayerCoords(){
        return new int[]{ppX, ppY};
    }
}
