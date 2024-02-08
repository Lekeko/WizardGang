import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class thrownGunLeft here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class thrownGunLeft extends bullet
{
    /**
     * Act - do whatever the thrownGunLeft wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(-30);
        getImage().rotate(50);
        if(this.isTouching(platform.class)){
            getWorld().removeObject(this);
        }
    }
}
