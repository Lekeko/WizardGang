import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
public class knife extends animatedEntity
{
    public boolean dealingDmg=false;
    int offsetX=82, offsetY=-2;
    public knife(){
        animateSpeed=0;
        scalar=4;
        imagini = new GreenfootImage[][] {
            {
                new GreenfootImage("knife1.png"),
                new GreenfootImage("knife2.png"),
                new GreenfootImage("knife3.png"),
                new GreenfootImage("knife4.png"),
                new GreenfootImage("knife5.png")
            }
        };
        getImage().setTransparency(0);
    }
    public void act()
    {
        if(oneTimeAnimation||crazyAnimation||animate){
            dealingDmg=true;
        }
        else{
            dealingDmg=false;
        }
        if(finishedAnimating){
            getImage().setTransparency(0);
        }
        location(x+offsetX*direction, y+offsetY);
        super.act();
        }
    public void flip(){
        direction*=-1;
        getImage().mirrorHorizontally();
    }
    public void swing(){
        oneTimeAnimation=true;
        getImage().setTransparency(255);
    }
}
