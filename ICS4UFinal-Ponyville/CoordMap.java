import java.util.*;
import java.io.*;
/**
 * Map, contains map and various methods that a map needs
 * Created when WarWorld is created, reflects map.
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class CoordMap{
    // instance variables - replace the example below with your own
    
    //Change by George, change Node from private to public
    private Node[][] mp;
    private int gridX, gridY; //number of grids
    private int xpix, ypix; //number of pixels in the x and y axis
    private int pixX, pixY; //Number of pixels per grid
    private int topOffset; //Offset to prevent bottom to be not covered by grid.
    
    /**
     * The constructure of CoordMap, which initializes the CoordMap that holds the map.
     * 
     * @param mapId     The id of the map (for reading maps from file).
     * @param xsiz      The number of columns of the grid.
     * @param ysiz      The number of rows of the grid.
     * @param xpix      The number of pixels in x coordinate in the world.
     * @param ypix      The number of pixels in y coordinate in the world.
     */
    public CoordMap(int mapId, int xsiz, int ysiz, int xpix, int ypix){
        this.gridX = xsiz; this.gridY = ysiz;
        this.xpix = xpix; this.ypix = ypix;
        pixX = xpix/xsiz; pixY = ypix/ysiz;
        mp = new Node[ysiz][xsiz];
        //to prevent top from offset.
        topOffset = 4;
        try{
            FastReader fr = new FastReader(new FileReader("./maps/"+mapId+"-1.txt"));
            for(int i=0; i<ysiz; i++)
                for(int j=0; j<xsiz; j++)
                    mp[i][j] = new Node(fr.nextInt());
        }catch(FileNotFoundException e){
            System.out.println("Unexpected Exception! Your program is probably corrupt.");
        }
    }
    
    /**
     * Gets the size of each grid in pixels
     * 
     * @return int[]    The size in pixels of each grid. {x, y}
     */
    public int[] getSz(){
        return new int[]{pixX, pixY};
    }
    
    /**
     * Get the raw Node object on a specific grid position in the grid.
     * 
     * @param in    The coordinate on grid (not pixels!) of the intended node. {x, y}
     * @return Node The node object in the given coordinate.
     */
    public Node getNode(int[] in){
        return mp[in[1]][in[0]];
    }
    
    /**
     * <p>Gets the corresponding pixel position from a grid coordinate.</p>
     * <p>The pixel position is supposed to be right in the middle of the grid.</p>
     * 
     * @param coord     The grid coordinate inputed. {x, y}
     * @return int[]    The corresponding {x, y} pixel value of the center of the grid.
     */
    public int[] getPixes(int[] coord){
        return new int[]{coord[0]*pixX + pixX/2, coord[1]*pixY + pixY/2 + topOffset};
    }
    
    //given a pixel x and y, return corresponding x and y of grid, 0-positioned.
    /**
     * Given a {x, y} coordinate of a pixel, return the grid position which this pixel lays on.
     * 
     * @param coord     The pixel position inputed. {x, y}
     * @return int[]    The corresponding grid position of this pixel. {x, y}
     */
    public int[] getMaps(int[] coord){
        return new int[]{coord[0]/pixX, (coord[1]-topOffset)/pixY < 0 ? 0:(coord[1]-topOffset)/pixY};
    }
    
    //Fastreader
    private static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader(FileReader in) {
            br = new BufferedReader(in);
        }
        
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        
        int nextInt() {
            return Integer.parseInt(next());
        }
    
        long nextLong() {
            return Long.parseLong(next());
        }
    
        double nextDouble() {
            return Double.parseDouble(next());
        }
    
        String nextLine() {
            String str = null;
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
