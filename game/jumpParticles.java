import greenfoot.*;
public class jumpParticles extends animatedEntity
{
    public jumpParticles(){
        scalar=3;
        crazyAnimation=true;
        imagini = new GreenfootImage[][] {
                {
                new GreenfootImage("jump1.png"),
                new GreenfootImage("jump2.png"),
                new GreenfootImage("jump3.png"),
                new GreenfootImage("jump4.png")
            }
        };
    }
    public void act()
    {
        super.act();
    }
}