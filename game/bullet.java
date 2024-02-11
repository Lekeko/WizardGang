import greenfoot.*;
public class bullet extends collision
{
    entity boom;
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
        shouldCollide=false;
        hSpeed = 30;
        boom = new Boom();
    }
    
    public void act()
    {        
        super.act();
        if(this.isTouching(platform.class)){
            
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
