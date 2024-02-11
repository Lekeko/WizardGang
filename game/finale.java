import greenfoot.*;
public class finale extends idk
{
    public finale()
    {
    }
    public void act(){
        if(Greenfoot.isKeyDown("q")){
            Greenfoot.setWorld(new menu());
        }
    }
}
