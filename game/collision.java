import greenfoot.*; 
public abstract class collision extends animatedEntity
{
    //the coordinates of the colider with the 00 in the left up of the sprite
    public vector2 leftUpCorner;
    public vector2 rightUpCorner;
    public vector2 leftDownCorner;
    public vector2 rightDownCorner;
    
    private int maxFallAcceleration=19;
    private int acceleration = 1;
    public boolean onGround=false;
    public boolean shouldFall = true;
    public boolean shouldMove=true;
    public boolean shouldCollide=true;
    public int halfWidthSprite;
    public int halfHeightSprite;
    public int vSpeed=0;
    public int hSpeed=0;
    public int spriteHeight;
    public int groundHeight;
    public GreenfootImage image;
    public entity currentGround;
    public entity currenRightWall;
    public entity currenLeftWall;
    int damageCooldown = 0;
    
    public collision(){
    }
    
    public void act()
    {
        if(damageCooldown>0){
            damageCooldown--;
        }
        if(shouldCollide){
            platformAbove();
            if (onGround()&&vSpeed>=0){
                vSpeed = 0;
                stayOnGround(currentGround);
            }
            else{
                fall();
                super.act();
                if (onGround()&&vSpeed>=0){
                    vSpeed = 0;
                    stayOnGround(currentGround);
                }
            }
            if(checkRightWall()&&hSpeed>0){
                hSpeed=0;
                //stayOnRightWall(currenRightWall);
            }
            if(checkLeftWall()&&hSpeed<0){
                hSpeed=0;
                //stayOnLeftWall(currenLeftWall);
            }
        }
        if(shouldMove){
            moveOnX();   
        }
        super.act();
    }
    public boolean platformAbove()//prevents the player from no clipping through the ceiling
    {
        int checkLeft=-halfWidthSprite+leftUpCorner.x;
        int checkRight=-halfWidthSprite+rightUpCorner.x;
        entity ceiling1 = (entity)getOneObjectAtOffset(checkLeft,-halfHeightSprite+leftUpCorner.y-19, platform.class);
        entity ceiling2 = (entity)getOneObjectAtOffset((checkLeft+checkRight)/2,-halfHeightSprite+leftUpCorner.y-19, platform.class);
        entity ceiling3 = (entity)getOneObjectAtOffset(checkRight,-halfHeightSprite+rightUpCorner.y-19, platform.class);
        //why -19? idk. i have no idea what im doing. maybe it 
        if(ceiling1 == null && ceiling2 == null&&ceiling3 == null)
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
    public boolean checkRightWall(Class<?> clazz){//wall right-true   no wall right-false
        int spriteWidth = image.getWidth();
        int checkUp=-halfHeightSprite+rightUpCorner.y;
        int checkDown=-halfHeightSprite+rightDownCorner.y-1;
        entity rightWall1 = (entity)getOneObjectAtOffset(-halfWidthSprite+rightUpCorner.x + 16, checkUp, clazz);
        entity rightWall2 = (entity)getOneObjectAtOffset(-halfWidthSprite+rightUpCorner.x + 16,(checkUp+checkDown)/2, clazz);
        entity rightWall3 = (entity)getOneObjectAtOffset(-halfWidthSprite+rightDownCorner.x + 16, checkDown, clazz);
        if(rightWall1 != null || rightWall2!=null|| rightWall3!=null){
            if(rightWall1!=null){
                currenRightWall=rightWall1;
            }
            else if(rightWall2!=null){
                currenRightWall=rightWall2;
            }
            else{
                currenRightWall=rightWall3;
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean checkRightWall() {
        return checkRightWall(platform.class); // Assuming platform is the default class
    }
    public boolean checkLeftWall(Class<?> clazz){//wall left-true   no wall left-false (also helps to not go through walls)
        int spriteWidth = image.getWidth();
        int checkUp=-halfHeightSprite+leftUpCorner.y;
        int checkDown=-halfHeightSprite+leftDownCorner.y-1;
        entity leftWall1 = (entity)getOneObjectAtOffset(-halfWidthSprite+leftUpCorner.x - 16, checkUp, clazz);
        entity leftWall2 = (entity)getOneObjectAtOffset(-halfWidthSprite+leftUpCorner.x - 16,(checkUp+checkDown)/2, clazz);
        entity leftWall3 = (entity)getOneObjectAtOffset(-halfWidthSprite+leftDownCorner.x - 16, checkDown, clazz);
        if(leftWall1 != null || leftWall2!=null || leftWall3!=null){
            if(leftWall1!=null){
                currenLeftWall=leftWall1;
            }
            else if(leftWall2!=null){
                currenLeftWall=leftWall2;
            }
            else{
                currenLeftWall=leftWall3;
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean checkLeftWall() {
        return checkLeftWall(platform.class); // Assuming platform is the default class
    }
    public boolean onGround()//true if on ground and false otherwise (also helps the player to not go to the backrooms)
    {
        int checkLeft=-halfWidthSprite+leftDownCorner.x;
        int checkRight=-halfWidthSprite+rightDownCorner.x;
        entity ground1 = (entity)getOneObjectAtOffset(-halfWidthSprite+leftDownCorner.x, -halfHeightSprite+leftDownCorner.y+8, platform.class);
        entity ground2 = (entity)getOneObjectAtOffset((checkLeft+checkRight)/2, -halfHeightSprite+leftDownCorner.y+8, platform.class);
        entity ground3 = (entity)getOneObjectAtOffset(-halfWidthSprite+rightDownCorner.x,-halfHeightSprite+rightDownCorner.y+8, platform.class);
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
                currentGround=ground1;   
            }
            else if(ground2!=null){
                currentGround=ground2;
            }
            else{
                currentGround=ground3;
            }
            return true;
        }
    }
    public void stayOnGround(entity ground)//this happens while the player moves on the ground (also helps to not go through walls)
    {
        groundHeight = ground.getImage().getHeight();
        y = ground.getY() - (groundHeight + getImage().getHeight())/2;
    }
    public void stayOnLeftWall(entity Wall)//does the same thing as the one above but for walls
    {
        int wallHalfWidth = Wall.getImage().getWidth()/2;
        x =Wall.x+wallHalfWidth+halfWidthSprite;
    }
    public void stayOnRightWall(entity Wall)//does the same thing as the one above but for other walls
    {
        int wallHalfWidth = Wall.getImage().getWidth()/2;
        x =Wall.x-wallHalfWidth-halfWidthSprite;
    }
    public void fall()//guess what this does
    {
        if(shouldFall){
            y+= vSpeed;
            if(vSpeed <=maxFallAcceleration)
            {
                vSpeed = vSpeed + acceleration;
            }
        }
    }
    public void moveOnX(){
        x+=hSpeed;
    }
    public void scaleCollider(int scalaar){
        //scale collider
        leftUpCorner=leftUpCorner.multiply(scalaar);
        rightUpCorner=rightUpCorner.multiply(scalaar);
        leftDownCorner=leftDownCorner.multiply(scalaar);
        leftDownCorner.x+=(scalaar-1);
        rightDownCorner=rightDownCorner.multiply(scalaar);
        rightDownCorner.x+=(scalaar-1);
    }
}
