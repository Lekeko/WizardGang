import greenfoot.*;
public class shiro extends collision
{
    private boolean jumping=false;
    private int jumpStrength = 20;
    private int animationCounter = 0;
    private int frame = 1;
    private int timer = 0;
    private Actor fart = new jumpParticles();
    public int speed = 9;
    //the coordinates of the colider with the 00 in the left up of the sprite
    private boolean isLeft = false; //if the player is facing left
    GreenfootImage[] imagini = {
        new GreenfootImage("jhonnyWalk1.png"),
        new GreenfootImage("jhonnyWalk2.png"),
        new GreenfootImage("jhonnyWalk2.png"),
        new GreenfootImage("jhonnyWalk2.png"),
        new GreenfootImage("jhonnyWalk2.png"),
        new GreenfootImage("jhonnyWalk1.png"),
        new GreenfootImage("jhonnyWalk1.png"),
        new GreenfootImage("jhonnyWalk1.png"),
    };
    
    GreenfootImage[] imaginiLeft = {
       new GreenfootImage("jhonnyWalk1.png"),
        new GreenfootImage("jhonnyWalk2.png"),
        new GreenfootImage("jhonnyWalk2.png"),
        new GreenfootImage("jhonnyWalk2.png"),
        new GreenfootImage("jhonnyWalk2.png"),
        new GreenfootImage("jhonnyWalk1.png"),
        new GreenfootImage("jhonnyWalk1.png"),
        new GreenfootImage("jhonnyWalk1.png"),
    };
    
    private int animate = 0;
    private int animateSpeed = 5;
    private int animatePos = 0;
    public static int shouldAnimate = 0;
    public shiro(){
        leftUpCorner=new vector2(3,1);
        rightUpCorner=new vector2(28,1);
        leftDownCorner=new vector2(3,31);
        rightDownCorner=new vector2(28,31);
        scaleShiro(3);
        image=getImage();
        spriteHeight=getImage().getHeight();
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
            moveHorizontally(1);
            if(!checkRightWall()){
               animate();
                
                if(isLeft){
                    
                    isLeft = false;
               }
            }
            else
            {
            setImage("jhonnyIdle.png");
                if(isLeft){
                    getImage().mirrorHorizontally();
                }
                scaleShiroForever(3);
            }
        }
        else
        {
            if(Greenfoot.isKeyDown("left"))
            {
                moveHorizontally(-1);
                if(!checkLeftWall()){
                    animate();
                    
                    if(!isLeft){
                        isLeft = true;
                   }
                }
                else
                {
                    setImage("jhonnyIdle.png");
                    if(isLeft){
                        getImage().mirrorHorizontally();
                    }
                    scaleShiroForever(3);
                }
                
            }
            else
            {
                hSpeed=0;
                setImage("jhonnyIdle.png");
                if(isLeft){
                    getImage().mirrorHorizontally();
                }
                scaleShiroForever(3);
            }
        }
        if(animate < animateSpeed){
            animate++;
        }
        else
        {
            animate = 0;
            if(animatePos < imagini.length-1){
                animatePos++;
            }
            else
            {
                animatePos = 0;
            }
        }
        //gravity logic
        
        if(onGround())
        {
            jumping=false;
            shouldAnimate = 0;
            
        }
        else
        {
            jumping=true;
        }
        //jump
        if(Greenfoot.isKeyDown("up") && jumping == false)
        {
            jump();
            shouldAnimate = 1;
            //addObject(fart, getImage().getWidth(), getImage().getHeight());
        }
        if(Greenfoot.isKeyDown("q")){
            ((level)getWorld()).nextLevel();
        }
        
        if(timer > 0)
            timer--;       
        if(jumping){
            
            if(timer > 0){
                setImage("jhonnyJum.png");
                if(isLeft){
                    getImage().mirrorHorizontally();
                }
                scaleShiroForever(3);
            }else{
                setImage("jhonnyFall.png");
                if(isLeft){
                    getImage().mirrorHorizontally();
                }
                scaleShiroForever(3);
            }
            
        }
        super.act();
    }
    
    public void animate(){
        if(!isLeft){
               getImage().mirrorVertically();
               setImage(imagini[animatePos]);
        }
        else{  
            getImage().mirrorVertically();
            setImage(imaginiLeft[animatePos]);
        }
        //setImage(new GreenfootImage("jhonnyWalk3.png"));
                
        scaleShiroForever(3);
    }
    public void moveHorizontally(int direction)
    {
        hSpeed=speed*direction;
    }
    public void jump()
    {
        entity idk=(entity)fart;
        idk.x=getX();
        idk.y=getY();
        getWorld().addObject(idk, this.getX(), this.getY()  );
        timer = 20;
        onGround=false;
        vSpeed =-jumpStrength;
        jumping = true;
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