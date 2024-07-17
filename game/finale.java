import greenfoot.*;
public class finale extends idk
{
    public finale()
    {
        level.lastLvl=1;
    }
    public void act(){
        if(Greenfoot.isKeyDown("q")){
            Greenfoot.setWorld(new menu());
        }
    }
}
