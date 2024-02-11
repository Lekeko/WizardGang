import greenfoot.*;
public class knifeParticles extends Boom
{
    public knifeParticles(){
        animateSpeed=4;
        scalar=3;
        imagini = new GreenfootImage[][] {
            {
                new GreenfootImage("knife_damage1.png"),
                new GreenfootImage("knife_damage2.png"),
                new GreenfootImage("knife_damage3.png"),
            }
        };
    }
    public void act()
    {
        super.act();
    }
}
