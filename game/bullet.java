import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class bullet extends collision
{
    /**
     * Act - do whatever the bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public bullet(){
        leftUpCorner=new vector2(16,16);
        rightUpCorner=new vector2(16,16);
        leftDownCorner=new vector2(16,16);
        rightDownCorner=new vector2(16,16);
        image=getImage();
        spriteHeight=getImage().getHeight();
        halfWidthSprite=getImage().getWidth()/2;
        halfHeightSprite=getImage().getHeight()/2;
        shouldFall = false;
        hSpeed = 30;
    }
    
    public void act()
    {        
        super.act();
        if(this.isTouching(platform.class)){
            entity boom = new Boom();
            boom.location(x,y);
            getWorld().addObject(boom, this.getX(),this.getY());
            getWorld().removeObject(this);
        }
        
    }
    public void flip(){
        getImage().mirrorHorizontally();
        hSpeed*=-1;
    }
    
    
}
