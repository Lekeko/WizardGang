import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class shiro extends Actor
{
    private int vSpeed=0;
    private int acceleration = 1;
    private boolean jumping=false;
    private boolean onGround=false;
    private int jumpStrength = 20;
    public int speed = 4;
    private int animationCounter = 0;
    private int frame = 1;
   
    //the coordinates of the colider with the 00 in the left up of the sprite
    private vector2 leftUpCorner=new vector2(10,13);
    private vector2 rightUpCorner=new vector2(19,13);
    private vector2 leftDownCorner=new vector2(10,31);
    private vector2 rightDownCorner=new vector2(19,31);
    private int halfWidthSprite;
    private int halfHeightSprite;
    private int maxFallAcceleration=19;
    boolean isLeft = false; //if the player is facing left
    GreenfootImage[] imagini = {
        new GreenfootImage("jhonnyWalk1.png"),
        new GreenfootImage("jhonnyWalk2.png"),
        new GreenfootImage("jhonnyWalk3.png"),
        new GreenfootImage("jhonnyWalk4.png"),
        new GreenfootImage("jhonnyWalk5.png"),
        new GreenfootImage("jhonnyWalk6.png"),
        new GreenfootImage("jhonnyWalk7.png"),
        new GreenfootImage("jhonnyWalk8.png"),
    };
    
    GreenfootImage[] imaginiLeft = {
        new GreenfootImage("jhonnyWalk1.png"),
        new GreenfootImage("jhonnyWalk2.png"),
        new GreenfootImage("jhonnyWalk3.png"),
        new GreenfootImage("jhonnyWalk4.png"),
        new GreenfootImage("jhonnyWalk5.png"),
        new GreenfootImage("jhonnyWalk6.png"),
        new GreenfootImage("jhonnyWalk7.png"),
        new GreenfootImage("jhonnyWalk8.png"),
    };
    
    int animate = 0;
    int animateSpeed = 3;
    int animatePos = 0;
    
    public shiro(){
        scaleShiro(3);
        halfWidthSprite=getImage().getWidth()/2;
        halfHeightSprite=getImage().getHeight()/2;
        for(int i = 0; i < 8; i++){
            
            imaginiLeft[i].mirrorHorizontally();
        }
    }
    public void act()
    {
        //move
        if(Greenfoot.isKeyDown("right"))
        {
            if(!checkRightWall()){
               moveRight();
               animate();
                
                if(isLeft){
                    
                    isLeft = false;
               }
            }else{
            setImage("jhonnyIdle.png");
                if(isLeft){getImage().mirrorHorizontally();}
                scaleShiroForever(3);}
        }
        else
        {
            if(Greenfoot.isKeyDown("left"))
            {
                if(!checkLeftWall()){
                    moveLeft();
                    animate();
                    
                    if(!isLeft){
                        
                        isLeft = true;
                   }
                }else{
                   setImage("jhonnyIdle.png");
                if(isLeft){getImage().mirrorHorizontally();}
                scaleShiroForever(3);}
                
            }
            else
            {
                setImage("jhonnyIdle.png");
                if(isLeft){getImage().mirrorHorizontally();}
                scaleShiroForever(3);
            }
        }
        
        
        if(animate < animateSpeed){
            animate++;
        }
        else
        {animate = 0;
            if(animatePos < imagini.length-1){
                animatePos++;
            }
            else
            {animatePos = 0;}
        }
        //jump
        if(Greenfoot.isKeyDown("up") && jumping == false)
        {
            jump();
        }
        //gravity logic
        if(onGround())
        {
            vSpeed = 0;
        }
        else
        {
            fall();
        }
        platformAbove();//collide with the platforms above
        if(Greenfoot.isKeyDown("q")){
            ((level)getWorld()).nextLevel();
        }
    }
    
    public void animate(){
            
            if(!isLeft){
                    
            
                   
                   getImage().mirrorVertically();
                   setImage(imagini[animatePos]);
               }else{
                   
                   getImage().mirrorVertically();
                
                setImage(imaginiLeft[animatePos]);
            }
                    
            scaleShiroForever(3);
    }
    public boolean platformAbove()//prevents the player from no clipping through the ceiling
    {
        int spriteHeight = getImage().getHeight();
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
    public void fall()//guess what this does
    {
        setLocation(getX(), getY() + vSpeed);
        if(vSpeed <=maxFallAcceleration)
        {
            vSpeed = vSpeed + acceleration;
        }
        jumping = true;
    }
    public boolean onGround()//true if on ground and false otherwise (also helps the player to not go to the backrooms)
    {
        if(!onGround){
            
        }
        int spriteHeight = getImage().getHeight();
        Actor ground1 = getOneObjectAtOffset(-halfWidthSprite+leftDownCorner.getX(), -halfHeightSprite+leftDownCorner.getY()+8, platform.class);
        Actor ground2 = getOneObjectAtOffset(-halfWidthSprite+rightDownCorner.getX(), -halfHeightSprite+rightDownCorner.getY()+8, platform.class);
        if(ground1 == null && ground2 == null)
        {
            jumping = true;
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
                moveOnGround(ground1);   
            }
            else{
                moveOnGround(ground2);
            }
            return true;
        }
    }
    public void moveOnGround(Actor ground)//this happens while the player moves on the ground (also helps to not go through walls)
    {
        int groundHeight = ground.getImage().getHeight();
        int newY = ground.getY() - (groundHeight + getImage().getHeight())/2;
        setLocation(getX(), newY);
        jumping = false;
    }
    public void moveLeft()
    {
        setLocation(getX()-speed, getY());
    }
    public void moveRight()
    {
        setLocation(getX()+speed, getY());
    }
    public void jump()
    {
        onGround=false;
        vSpeed = vSpeed - jumpStrength;
        jumping = true;
        fall();
    }
    public boolean checkLeftWall(){//wall left-true   no wall left-false (also helps to not go through walls)
        int spriteWidth = getImage().getWidth();
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
            setImage("jhonnyIdle.png");
                if(isLeft){getImage().mirrorHorizontally();}
                scaleShiroForever(3);
            return false;
        }
    }
    
    public boolean checkRightWall(){//wall right-true   no wall right-false
        int spriteWidth = getImage().getWidth();
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
            setImage("jhonnyIdle.png");
                if(!isLeft){getImage().mirrorHorizontally();}
                scaleShiroForever(3);
            return false;
        }
    }
    public void scaleShiro(int scalar){
        //scalse sprite
        GreenfootImage originalImage = getImage();
        int newWidth = originalImage.getWidth() * scalar;
        int newHeight = originalImage.getHeight() * scalar;
        GreenfootImage scaledImage = new GreenfootImage(originalImage);
        scaledImage.scale(newWidth, newHeight);
        setImage(scaledImage);
        //scale collider
        leftUpCorner=leftUpCorner.multiply(scalar);
        rightUpCorner=rightUpCorner.multiply(scalar);
        leftDownCorner=leftDownCorner.multiply(scalar);
        leftDownCorner.x+=(scalar-1);
        rightDownCorner=rightDownCorner.multiply(scalar);
        rightDownCorner.x+=(scalar-1);
    }
    
    
    public void scaleShiroForever(int scalar){
        //scalse only the sprite
        GreenfootImage originalImage = getImage();
        int newWidth = originalImage.getWidth() * scalar;
        int newHeight = originalImage.getHeight() * scalar;
        GreenfootImage scaledImage = new GreenfootImage(originalImage);
        scaledImage.scale(newWidth, newHeight);
        setImage(scaledImage);
        
    }
}