import greenfoot.*;
public class playButton extends button
{
    public playButton(){
        
    }
    public void act()
    {
        if(Greenfoot.mouseClicked(this)){
            level.lastLvl= 1;
            Greenfoot.setWorld(new intro());
        }
    }
}
