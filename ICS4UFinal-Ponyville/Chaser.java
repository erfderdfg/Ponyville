import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * The thing that chases your character in the main game.
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class Chaser extends MovingInTurns{
    
    private GreenfootImage img;
    private int cnt, hp, prehp;
    private Stack<Integer> curstk;
    private Stack<int[]> curPos;
    private GreenfootImage cc, ccdamaging;
    
    /**
     * Initializeation of the chaser object. Since chaser moves 2x faster than player, its speed should be 3.
     */
    public Chaser(){
        curstk = new Stack<Integer>();
        curPos = new Stack<int[]>();
        moving = false; hp = 45; prehp = 45;
        spd = 3;
    }
    
    /**
     * Perform more initialization steps that requires the world.
     * 
     * @param w     The world in which the chaser is added to.
     */
    public void addedToWorld(World w){
        prevPos = new int[]{getX(), getY()};
        realPos = new int[]{getX(), getY()};
        cc = new GreenfootImage("EvilCharacter.png");
        cc.scale(((MainWorld)getWorld()).getMap().getSz()[0], ((MainWorld)getWorld()).getMap().getSz()[1]);
        ccdamaging = new GreenfootImage(SparkleEngine.greyIFy(cc));
        setImage(cc);
    }
    
    /**
     * Implementing the move() method in superclass. The chaser would determine whether to follow the path stored in the stack.
     */
    protected void move(){
        if(!moving && cnt>0 && !curstk.isEmpty()){
            moving = true;
            cnt--; dir = curstk.pop();
            realPos = curPos.pop();
        }
        if(moving)
            shift(((MainWorld)getWorld()).getMap().getMaps(new int[]{getX(), getY()}));
    }
    
    /**
     * Implementing the display() method in superclass.
     */
    protected void display(){
        if(hp!=prehp){
            setImage(ccdamaging);
            prehp = hp;
        }else{
            setImage(cc);
        }
        if(where()[0]==Statics.getPlayerCoords()[0] && where()[1]==Statics.getPlayerCoords()[1]){
            Statics.setHP(Statics.getHP()-5);
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Damage self
     */
    public void damage(){
        hp--;
    }
    
    /**
     * Gets self's current HP
     */
    public int getHP(){
        return hp;
    }
    
    /**
     * Action (get a working shortest path to the player's coordinates.)
     * 
     * @param plCoord   The player's current coord (target of bfs)
     */
    public void action(int[] plCoord){
        decide(plCoord);
        cnt = 2;
    }

    /**
     * Retuns where self is currently at.
     * 
     * @return int[]    The place where self is currently at. {x, y}
     */
    public int[] where(){
        return ((MainWorld)getWorld()).getMap().getMaps(new int[]{getX(), getY()});
    }
    
    /**
     * Check if current node is able to travel.
     * 
     * @param cur   The array which stores the node that is going to be detected
     * @return boolean  If the current node could be visited
     */
    private boolean chk(int[] cur){
        if(cur[0]<0 || cur[1]<0 || cur[0]>=20 || cur[1]>=11)
            return false;
        if(((MainWorld)getWorld()).getMap().getNode(cur).getType()==2)
            return false;
        return true;
    }
    
    /**
     * BFS algorithm that detects the player's coordinates and provides a working list of nodes stored in a stack. (FILO to reverse tracking)
     * 
     * @param plCoord   the player's current coord
     */
    private void decide(int[] plCoord){
        int[] ccoord = ((MainWorld)getWorld()).getMap().getMaps(new int[]{getX(), getY()});
        Queue<int[]> q = new LinkedList<int[]>();
        q.add(ccoord); int[][] vis = new int[20][11];
        for (int[] i:vis)
            Arrays.fill(i, -1);
        vis[ccoord[0]][ccoord[1]] = -2;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0]==plCoord[0] && cur[1]==plCoord[1])
                break;
            for(int i=0; i<4; i++){
                int[] curr = cur.clone();
                curr[0] += dirs[i][0]; curr[1] += dirs[i][1];
                if(chk(curr) && vis[curr[0]][curr[1]]==-1){
                    q.add(curr);
                    vis[curr[0]][curr[1]] = i;
                }
            }
        }
        curstk.clear(); curPos.clear();
        int[] btCoord = plCoord.clone();
        while(vis[btCoord[0]][btCoord[1]]!=-2){
            int tmp = vis[btCoord[0]][btCoord[1]];
            curstk.push(tmp); 
            curPos.push(((MainWorld)getWorld()).getMap().getPixes(btCoord));
            btCoord[0] -= dirs[tmp][0]; btCoord[1] -= dirs[tmp][1];
        }
    }
}
