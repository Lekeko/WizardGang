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
    public static boolean isLeft = false; //if the player is facing left
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
    
    Actor[] bulletAmmo = {
            new GunShowcase(),
            new bulletShowcase(), 
            new bulletShowcase(), 
            new bulletShowcase(), 
            new bulletShowcase(), 
            new bulletShowcase(), 
            new bulletShowcase(), 
            new bulletShowcase()
        };
    
    public shiro(){
        leftUpCorner=new vector2(4,2);
        rightUpCorner=new vector2(28,2);
        leftDownCorner=new vector2(4,32);
        rightDownCorner=new vector2(28,32);
        scaleShiro(3);
        image=getImage();
        Actor bulletShowcase = new bulletShowcase();
        
        
        spriteHeight=getImage().getHeight();
        halfWidthSprite=getImage().getWidth()/2;
        halfHeightSprite=getImage().getHeight()/2;
        for(int i = 0; i < 8; i++){
            imaginiLeft[i].mirrorHorizontally();
        }
    }
    int gunCooldown = 20;
    int offsetBullet = 0;
    int offsetGun = 0;
    int ammo = 7;
    int timerShowGun = -1;
    boolean hasGun = true;
    Actor gun = new Gun();
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
                moveHorizontally(0);
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
        if(Greenfoot.isKeyDown("up") && jumping == false&&vSpeed>=0)
        {
            jump();
            shouldAnimate = 1;
        }     
        if(timer > 0)
            timer--;       
        if(jumping){
            
            if(timer > 0){
                setImage("jhonnyJum.png");
            }else{
                setImage("jhonnyFall.png");
            }
            if(isLeft){
                    getImage().mirrorHorizontally();
            }
             scaleShiroForever(3);
            
        }
        
        gunCooldown--;
        timerShowGun--;
        
        

        try{
            if(((shiro)this).isLeft){
                gun.setLocation(329, this.getY());
                gun.setImage("gunL.png");
            }else{
                gun.setLocation(474, this.getY());
                gun.setImage("gun.png");
            }
            if(timerShowGun > 0){
                gun.getImage().setTransparency(255);
            }
            if(Greenfoot.isKeyDown("space") && hasGun){
                if (gunCooldown < 0 && ammo > 0){
                    ammo--;
                    for(int i  = 7; i >= ammo + 1; i--){
                        bulletAmmo[i].getImage().setTransparency(0);
                    }
                    gun.getImage().setTransparency(255);
                    gunCooldown = 20;
                    timerShowGun = 20;
                    Actor bullet;
                    if(((shiro)this).isLeft){
                        bullet = new bulletL();
                        offsetBullet =  - 60;
                    }else{
                        bullet = new bullet();
                        offsetBullet =   60;
                        
                    }
                    entity glont = (entity)bullet;
                    glont.x = getX();
                    glont.y = getY();
                    getWorld().addObject(glont, getX() + offsetBullet, getY());
                }
                if (ammo == 0 && gunCooldown < 0){
                    gunCooldown = 600;
                    hasGun = false;
                    Actor thrownGun;
                    if(((shiro)this).isLeft){
                        thrownGun = new thrownGunLeft();
                        offsetBullet =  - 60;
                    }else{
                        thrownGun = new thrownGun();
                        offsetBullet =   60;
                        
                    }
                    getWorld().addObject(thrownGun, this.getX() + offsetBullet, this.getY());
                    bulletAmmo[0].getImage().setTransparency(0);
                    ammo = 7;
                }
        }else{
            if(timerShowGun < 0)gun.getImage().setTransparency(0);
        }
        }catch(Exception e){}
        super.act();
    }
    
    public void animate(){
        getImage().mirrorVertically();
        if(!isLeft){
               setImage(imagini[animatePos]);
        }
        else{  
            setImage(imaginiLeft[animatePos]);
        }
                
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
        getWorld().addObject(idk, this.getX(),this.getY());
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
    public void flipCollider(){
        int leftDistance = getImage().getWidth() - rightUpCorner.x;
        int rightDistance = getImage().getWidth() - leftUpCorner.x;
        leftUpCorner=new vector2(leftDistance,2);
        rightUpCorner=new vector2(rightDistance,2);
        leftDownCorner=new vector2(leftDistance,32);
        rightDownCorner=new vector2(rightDistance,32);
    }
}