import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shader box, the box that acts as a "cover" to cover up the real maze.
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class ShaderBox extends Actor{
    GreenfootImage blk, realImg;
    
    /**
     * Initializes the ShaderBox object. This object shouldn't do anything other than staying there and change brightness when asked to.
     * 
     * @param xsz   The width of the box.
     * @param ysz   The height of the box.
     */
    public ShaderBox(int xsz, int ysz){
        blk = new GreenfootImage(xsz, ysz);
        blk.setColor(Color.BLACK);
        blk.fillRect(0, 0, xsz, ysz);
        realImg = new GreenfootImage(blk);
        this.setImage(blk);
    }
    
    /**
     * Iluminate the shader box
     * 
     * @param perc  The percentage of transparency of shaderBox.
     */
    public void iluminate(int perc){
        realImg = new GreenfootImage(SparkleEngine.setTransparency(blk, perc));
        this.setImage(realImg);
    }
}
