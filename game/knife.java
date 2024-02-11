import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
public class knife extends animatedEntity
{
    public boolean active=false;
    int offsetX=82, offsetY=-2;
    GreenfootSound sound = new GreenfootSound("swish.mp3");
    public knife(){
        sound.setVolume(20);
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
            active=true;
        }
        else{
            active=false;
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
        sound.play();
        oneTimeAnimation=true;
        getImage().setTransparency(255);
    }
}
