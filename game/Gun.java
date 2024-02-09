import greenfoot.*;
public class Gun extends entity
{
    int offsetX=72, offsetY=-2,direction=1,offsetBullet=70;
    public void act()
    {
        setLocation(x+offsetX*direction, y+offsetY);        
    }
    public void flip(){
        direction*=-1;
        getImage().mirrorHorizontally();
    }
    public void shoot(){
        bullet glont=new bullet();
        glont.location(x, y);
        if(direction>0){
            getWorld().addObject(glont, x + offsetBullet*direction, offsetY);
        }
        else{
            getWorld().addObject(glont, x + offsetBullet*direction, offsetY);
            glont.flip();
        }
    }
}
