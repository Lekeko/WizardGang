import greenfoot.*;
public class eagle extends collision
{
    level lvl;
    int timer=0;
    public eagle(){
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
        hSpeed = 10;
        GreenfootSound sound = new GreenfootSound("eagle.mp3");
        sound.play();
        //setImage(scaleSprite(getImage(),2));
    }
    public void addedToWorld(World world) {
        lvl=((level)getWorld());
        if(lvl.player.x<x){
            flip();
        }
    }
    public void act()
    {
        timer++;
        super.act();
        if(timer>1000){
            getWorld().removeObject(this);
        }
    }
    public void flip(){
        getImage().mirrorHorizontally();
        hSpeed*=-1;
    }
}
