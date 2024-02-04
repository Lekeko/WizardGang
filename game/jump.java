import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class jump here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class jump extends Actor
{
    /**
     * Act - do whatever the jump wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage[] imagini = {
        new GreenfootImage("jump1.png"),
        new GreenfootImage("jump2.png"),
        new GreenfootImage("jump3.png"),
        new GreenfootImage("jump4.png"),
        new GreenfootImage("jump5.png"),
        
    };
    int animate = 0;
    int animateSpeed = 5;
    static int i = 1 ;
    int animatePos = 0;
    
    
    public jump(){
        	
        setImage((GreenfootImage)null);
    }
    
    public void act()
    {
        
        if(shiro.shouldAnimate == 1){
            animate++;
            if(animate == animateSpeed){
            animate = 0;
            setImage("jump" + i + ".png");
            System.out.println(i);            
            GreenfootImage originalImage = getImage();
            int newWidth = originalImage.getWidth() * 3;
            int newHeight = originalImage.getHeight() * 3;
            GreenfootImage scaledImage = new GreenfootImage(originalImage);
            scaledImage.scale(newWidth, newHeight);
            setImage(scaledImage);
            i++;
            if(Greenfoot.isKeyDown("right")){
                move(-24);
            }
            if(Greenfoot.isKeyDown("left")){
                move(24);
            }
            
            if (i== 6){ 
                 //
                    i = 1;
                 getWorld().removeObject(this);
                 shiro.shouldAnimate = 0;
                 getImage().setTransparency(0);
            }
            
        }
    }
    
    }
}
