import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bulletL here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class bulletL extends entity
{
    /**
     * Act - do whatever the bulletL wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        move(-40);
        if(this.isTouching(platform.class)){
        
            Actor boom = new Boom();
            getWorld().addObject(boom, this.getX(), this.getY());
            getWorld().removeObject(this);
        }
    }
}
