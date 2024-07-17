import greenfoot.*;
public class Gun extends entity
{
    GreenfootSound sound = new GreenfootSound("gun_shot.mp3");
    int offsetX=72, offsetY=-2,direction=1,offsetBullet=70;
    public Gun(){
        sound.setVolume(20);
    }
    public void act()
    {
        setLocation(x+offsetX*direction, y+offsetY);        
    }
    public void flip(){
        direction*=-1;
        getImage().mirrorHorizontally();
    }
    public void shoot(){
        if(!sound.isPlaying()){
            sound.play();   
        }
        bullet glont=new playerBullet();
        glont.location(x+offsetBullet*direction, y+offsetY-3);
        if(direction>0){
            getWorld().addObject(glont, x + offsetBullet*direction, y+offsetY);
        }
        else{
            getWorld().addObject(glont, x + offsetBullet*direction, y+offsetY);
            glont.flip();
        }
    }
    public void throww(){
        sound.play();
        thrownGun glont=new thrownGun();
        glont.location(x, y);
        if(direction>0){
            getWorld().addObject(glont, x, y);
        }
        else{
            getWorld().addObject(glont, x, y);
            glont.flip();
        }
    }
}
