import greenfoot.*;
public class Boom extends animatedEntity
{
    public Boom(){
        crazyAnimation=true;
        animateSpeed=1;
        scalar=3;
        imagini = new GreenfootImage[][] {
            {
                new GreenfootImage("bullet1.png"),
                new GreenfootImage("bullet2.png"),
                new GreenfootImage("bullet3.png"),
                new GreenfootImage("bullet4.png"),
                new GreenfootImage("bullet5.png"),
                new GreenfootImage("bullet6.png"),
                new GreenfootImage("bullet7.png")
            }
        };
    }
    public void act()
    {
        super.act();
    }
}
