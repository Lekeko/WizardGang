import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boom extends entity
{
    GreenfootImage[] boomSqeuence = {
         new GreenfootImage("bullet1.png"),
         new GreenfootImage("bullet2.png"),
         new GreenfootImage("bullet3.png"),
         new GreenfootImage("bullet4.png"),
         new GreenfootImage("bullet5.png"),
         new GreenfootImage("bullet6.png"),
         new GreenfootImage("bullet7.png"),
    };
    static boolean shouldAnimate =false;
    int animationSpeed = 2;
    int curentFrame = 0;
    public void Boom(){
        
    }
    /**
     * Act - do whatever the Boom wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(shouldAnimate){
            startAnimation();
        }
        this.getImage().scale(70, 70);
        animationSpeed--;
        super.act();
    }
    
    public void startAnimation(){
        if(animationSpeed < 0){
            animateNext(++curentFrame);
            animationSpeed = 2;
        }
    }
    
    public void animateNext(int curentFrame){
        if (curentFrame < 7){
            this.setImage(boomSqeuence[curentFrame]);
        }else{
            deleteEffect();
            return;
        }
    }
    
    public void deleteEffect(){
        this.getWorld().removeObject(this);
        
    }
}
