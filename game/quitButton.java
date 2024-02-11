import greenfoot.*;
public class quitButton extends button
{
    public void act()
    {
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.stop();
        }
    }
}
