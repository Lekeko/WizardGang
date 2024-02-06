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
    public void act()
    {
        super.act();
    }
    public boolean platformAbove()//prevents the player from no clipping through the ceiling
    {
        Actor ceiling1 = getOneObjectAtOffset(-halfWidthSprite+leftUpCorner.x,-halfHeightSprite+leftUpCorner.y-8, platform.class);
        Actor ceiling2 = getOneObjectAtOffset(-halfWidthSprite+rightUpCorner.x,-halfHeightSprite+rightUpCorner.y-8, platform.class);
        if(ceiling1 == null && ceiling2 == null&& getY()-halfHeightSprite+leftUpCorner.y > 0)
        {
            return false;
        }
        else
        {
            if (vSpeed<0){
                vSpeed = -vSpeed/2;   
            }
            return true;
        }
    }
    
}
