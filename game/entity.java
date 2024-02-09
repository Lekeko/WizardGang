import greenfoot.*;

public abstract class entity extends vector2
{
    public boolean isOnScreen;
    public entity(){

    }
    public entity(int x,int y){
        this.x=x;
        this.x=y;
    }
    public void act()
    {
        setLocation(this.x, this.y);
    }
    public GreenfootImage scaleSprite(GreenfootImage image, int scalar){
        int newWidth = image.getWidth() * scalar;
        int newHeight = image.getHeight() * scalar;
        GreenfootImage scaledImage = new GreenfootImage(image);
        scaledImage.scale(newWidth, newHeight);
        return scaledImage;
        
    }
}
