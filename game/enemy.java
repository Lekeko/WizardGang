import greenfoot.*;
public class enemy extends collision
{
    public enemy(){
        leftUpCorner=new vector2(0,0);
        rightUpCorner=new vector2(63,0);
        leftDownCorner=new vector2(0,63);
        rightDownCorner=new vector2(63,63);
        image=getImage();
        spriteHeight=getImage().getHeight();
        halfWidthSprite=getImage().getWidth()/2;
        halfHeightSprite=getImage().getHeight()/2;
    }
    public void act()
    {
        super.act();
    }
}
