import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class shiro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class shiro extends Actor
{
    /**
     * Act - do whatever the shiro wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        gravity();
        moveAround();
    }
        public void moveAround(){
        if(Greenfoot.isKeyDown("right")){
            setLocation(getX()+7, getY());
        }
        if(Greenfoot.isKeyDown("left")){
            setLocation(getX()-7, getY());
        }
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - 7);
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + 7);
        }
    }
    public void gravity(){
        setLocation(getX(), getY()+2);
    }
}
