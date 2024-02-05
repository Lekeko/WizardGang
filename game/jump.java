import greenfoot.*;
public class jump extends Actor
{
    GreenfootImage[] imagini = {
        new GreenfootImage("jump1.png"),
        new GreenfootImage("jump2.png"),
        new GreenfootImage("jump3.png"),
        new GreenfootImage("jump4.png"),
        new GreenfootImage("jump5.png"),
        
    };
    private int animate = 0;
    private int animateSpeed = 5;
    private static int i = 1 ;
    private int animatePos = 0;
    
    
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
            GreenfootImage originalImage = getImage();
            int newWidth = originalImage.getWidth() * 3;
            int newHeight = originalImage.getHeight() * 3;
            GreenfootImage scaledImage = new GreenfootImage(originalImage);
            scaledImage.scale(newWidth, newHeight);
            setImage(scaledImage);
            i++;
            
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
