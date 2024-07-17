import greenfoot.*;
public class BoomGun extends Boom
{
    public BoomGun(){
        scalar=5;
        imagini = new GreenfootImage[][] {
            {
                new GreenfootImage("bullet1_gun.png"),
                new GreenfootImage("bullet2_gun.png"),
                new GreenfootImage("bullet3_gun.png"),
                new GreenfootImage("bullet4_gun.png"),
                new GreenfootImage("bullet5_gun.png"),
                new GreenfootImage("bullet6_gun.png"),
                new GreenfootImage("bullet7_gun.png")
            }
        };
    }
    public void act()
    {
        
        super.act();
    }
}
