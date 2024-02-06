import greenfoot.*; 
public abstract class collision extends entity
{
    public boolean onGround=false;
    public vector2 leftUpCorner;
    public vector2 rightUpCorner;
    public vector2 leftDownCorner;
    public vector2 rightDownCorner;
    public int halfWidthSprite;
    public int halfHeightSprite;
    public int vSpeed=9;
    public int spriteHeight;
    public GreenfootImage image;
    public void act()
    {
        super.act();
    }
    
}
