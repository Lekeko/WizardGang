import greenfoot.*;
public class bullet extends collision
{
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
        try{
            if(this.isTouching(enemy.class)){
                removeTouching(enemy.class);
                entity boom = new Boom();
                boom.location(x,y);
                getWorld().addObject(boom, this.getX(),this.getY());
                getWorld().removeObject(this);
            }
            if(this.isTouching(enemy2.class)){
                enemy2 inamic = (enemy2)getOneIntersectingObject(enemy2.class);
                if(inamic.hp == 0){
                    getWorld().removeObject(inamic);
                }else inamic.hp--;
                entity boom = new Boom();
                boom.location(x,y);
                getWorld().addObject(boom, this.getX(),this.getY());
                getWorld().removeObject(this);

            }
        }catch(Exception e){}
    }
    public void flip(){
        getImage().mirrorHorizontally();
        hSpeed*=-1;
    }
    
    
}
