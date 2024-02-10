import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class knife here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class knife extends animatedEntity
{
    /**
     * Act - do whatever the knife wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int offsetX=72, offsetY=-2;
    public knife(){
        
        crazyAnimation=true;
        animateSpeed=0;
        scalar=4;
        imagini = new GreenfootImage[][] {
            {
                new GreenfootImage("knife1.png"),
                new GreenfootImage("knife2.png"),
                new GreenfootImage("knife3.png"),
                new GreenfootImage("knife4.png"),
                new GreenfootImage("knife5.png")
            }
        };
    }
    public void act()
    {
        
        location(x+offsetX*direction, y+offsetY);
        super.act();  
        try{
            if(this.isTouching(enemy.class)){
                removeTouching(enemy.class);
            }
            if(this.isTouching(enemy2.class)){
                enemy2 inamic = (enemy2)getOneIntersectingObject(enemy2.class);
                if(inamic.hp == 0){
                    getWorld().removeObject(inamic);
                }else inamic.hp--;

            }
        }catch(Exception e){}
        }
    public void flip(){
        direction*=-1;
        getImage().mirrorHorizontally();
    }
}
