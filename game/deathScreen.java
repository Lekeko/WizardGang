import greenfoot.*; 
public class deathScreen extends idk
{

    /**
     * Constructor for objects of class deathScreen.
     * 
     */
    public deathScreen()
    {
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("q")){
            Greenfoot.setWorld(new menu());
        }
    }
}