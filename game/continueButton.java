import greenfoot.*;
public class continueButton extends button
{
    public void act()
    {
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(level.lastMap);
        }
    }
}
