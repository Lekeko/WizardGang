import greenfoot.*; 
public class deathScreen extends idk
{
    public deathScreen()
    {
    }
    
    public void act(){
        menu.sound.stop();
        if(Greenfoot.isKeyDown("q")){
            Greenfoot.setWorld(new menu());
        }
    }
}