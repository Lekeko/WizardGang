import greenfoot.*;
public class finale extends idk
{
    public finale()
    {
        level.lastMap=new lvl1();
    }
    public void act(){
        if(Greenfoot.isKeyDown("q")){
            Greenfoot.setWorld(new menu());
        }
    }
}
