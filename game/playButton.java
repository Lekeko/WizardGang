import greenfoot.*;
public class playButton extends button
{
    public playButton(){
        
    }
    public void act()
    {
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(new intro());
        }
    }
}
