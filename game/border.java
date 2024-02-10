import greenfoot.*;
public class border extends animatedEntity
{
    public border(){
        scalar=4;
        interactible=false;
        setImage(scaleSprite(getImage(),4));
        imagini = new GreenfootImage[][]{
            {
                new GreenfootImage("border1a.png"),
                new GreenfootImage("border1b.png"),
            },
            {
                new GreenfootImage("border2a.png"),
                new GreenfootImage("border2b.png"),
            },
            {
                new GreenfootImage("border3a.png"),
                new GreenfootImage("border3b.png"),
            },
            {
                new GreenfootImage("borderDead.png"),
            },
        };
    }
    public void act()
    {
        super.act();
        
    }
}
