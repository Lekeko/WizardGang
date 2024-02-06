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
    private int maxFallAcceleration=19;
    private int acceleration = 1;
    public void act()
    {
        platformAbove();
       // System.out.print(" "+halfHeightSprite);
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
    public boolean checkRightWall(){//wall right-true   no wall right-false
        int spriteWidth = image.getWidth();
        int checkUp=-halfHeightSprite+rightUpCorner.y;
        int checkDown=-halfHeightSprite+rightDownCorner.y;
        Actor rightWall1 = getOneObjectAtOffset(-halfWidthSprite+rightUpCorner.x + 8, checkUp, platform.class);
        Actor rightWall2 = getOneObjectAtOffset(-halfWidthSprite+rightUpCorner.x + 8,(checkUp+checkDown)/2, platform.class);
        Actor rightWall3 = getOneObjectAtOffset(-halfWidthSprite+rightDownCorner.x + 8, checkDown, platform.class);
        if(rightWall1 != null || rightWall2!=null|| rightWall3!=null||getX()-halfWidthSprite+rightUpCorner.x> getWorld().getWidth()){
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean checkLeftWall(){//wall left-true   no wall left-false (also helps to not go through walls)
        int spriteWidth = image.getWidth();
        int checkUp=-halfHeightSprite+leftUpCorner.y;
        int checkDown=-halfHeightSprite+leftDownCorner.y;
        Actor leftWall1 = getOneObjectAtOffset(-halfWidthSprite+leftUpCorner.x - 8, checkUp, platform.class);
        Actor leftWall2 = getOneObjectAtOffset(-halfWidthSprite+leftUpCorner.x - 8,(checkUp+checkDown)/2, platform.class);
        Actor leftWall3 = getOneObjectAtOffset(-halfWidthSprite+leftDownCorner.x - 8, checkDown, platform.class);
        if(leftWall1 != null || leftWall2!=null || leftWall3!=null ||getX()-halfWidthSprite+leftUpCorner.x<0){
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean onGround()//true if on ground and false otherwise (also helps the player to not go to the backrooms)
    {
        int spriteHeight = getImage().getHeight();
        int checkLeft=-halfWidthSprite+leftDownCorner.x;
        int checkRight=-halfWidthSprite+rightDownCorner.x;
        Actor ground1 = getOneObjectAtOffset(-halfWidthSprite+leftDownCorner.x, -halfHeightSprite+leftDownCorner.y+8, platform.class);
        Actor ground2 = getOneObjectAtOffset((checkLeft+checkRight)/2, -halfHeightSprite+leftDownCorner.y+8, platform.class);
        Actor ground3 = getOneObjectAtOffset(-halfWidthSprite+rightDownCorner.x,-halfHeightSprite+rightDownCorner.y+8, platform.class);
        if(ground1 == null && ground2 == null && ground3 == null)
        {
            return false;
        }
        /*else if(getY()-halfHeightSprite+leftDownCorner.y >= getWorld().getHeight()){//collision with the lower border
            setLocation(getX(),getWorld().getHeight()-getImage().getHeight()/2+(leftDownCorner.y+leftUpCorner.y)/2);
            jumping = true;
            return true;
        }idk im incapable this is not working*/
        else
        {
            if(ground1!=null){
                stayOnGround(ground1);   
            }
            else if(ground2!=null){
                stayOnGround(ground2);
            }
            else{
                stayOnGround(ground3);
            }
            return true;
        }
    }
    public void stayOnGround(Actor ground)//this happens while the player moves on the ground (also helps to not go through walls)
    {
        int groundHeight = ground.getImage().getHeight();
        y = ground.getY() - (groundHeight + getImage().getHeight())/2;
    }
    public void fall()//guess what this does
    {
        y+= vSpeed;
        if(vSpeed <=maxFallAcceleration)
        {
            vSpeed = vSpeed + acceleration;
        }
    }
}
