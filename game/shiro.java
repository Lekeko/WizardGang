import greenfoot.*;
public class shiro extends collision
{
    public static boolean isLeft = false; //if the player is facing left
    private boolean previousKeyPressed = false;
    private boolean hasGun = true;
    private int jumpStrength = 18;
    private int animationCounter = 0;
    private int frame = 1;
    private int timer = 0;
    private int speed = 9;
    private int hp= 3;
    private int timeSinceLastFrame = 0;
    private int currentFrame = 0;
    private int gunCooldown = 20;
    private int offsetBullet = 0;
    private int offsetGun = 0;
    private int ammo = 7;
    private int timerShowGun = -1;
    private int reloadTimer = 70;
    private Gun gun = new Gun();
    private knife knife = new knife();
    private level lvl;
    private boolean usesKnife = false;
    public shiro(){
        
        leftUpCorner=new vector2(4,2);
        rightUpCorner=new vector2(28,2);
        leftDownCorner=new vector2(4,32);
        rightDownCorner=new vector2(28,32);
        isLeft = false;//"but you already initialized that" yea sure if i move left and restart the game, shiro will face left even it its initialized right why? idk greenfot inferior engine
        scalar=3;
        scaleShiro(scalar);
        image=getImage();
        spriteHeight=getImage().getHeight();
        halfWidthSprite=getImage().getWidth()/2;
        halfHeightSprite=getImage().getHeight()/2;
        animate=true;
        animateSpeed = 1;
        imagini = new GreenfootImage[][]{ 
            {
                new GreenfootImage("jhonnyWalk1.png"),
                new GreenfootImage("jhonnyWalk2.png"),
                new GreenfootImage("jhonnyWalk2.png"),
                new GreenfootImage("jhonnyWalk2.png"),
                new GreenfootImage("jhonnyWalk2.png"),
                new GreenfootImage("jhonnyWalk1.png"),
                new GreenfootImage("jhonnyWalk1.png"),
                new GreenfootImage("jhonnyWalk1.png"),
            },
            {
                new GreenfootImage("jhonnyIdle.png"),
            },
            {
                new GreenfootImage("jhonnyJum.png"),
            },
            {
                new GreenfootImage("jhonnyFall.png"),
            },
        };
    }
    public void addedToWorld(World world) {
        lvl=((level)getWorld());
        getWorld().addObject(gun, x+72, y-2);
        getWorld().addObject(knife, x+72, y-2);
    }
    int knifeCooldown = 20;
    int damageCooldown = 0;
    public void act()
    {
        damageCooldown--;
        knifeCooldown--;
        if(damageCooldown <= 0 && isTouching(enemies.class) && hp > 0){
            takeDmg();
            damageCooldown =100;
        }
        
        //move
        if(!hasGun){
            reloadTimer--;
        }
        
        if(reloadTimer < 0){
            reloadGun();
        }
        animate();//move shiro based on where you pushed her (left right up dow)
        if(Greenfoot.isKeyDown("right"))
        {//press right and push shiro right
            hSpeed=speed;
            if(isLeft){
                gun.flip();
                knife.flip();
                isLeft = false;
            }
        }
        else if(Greenfoot.isKeyDown("left"))
        {//press left and push shiro left
            hSpeed=-speed;
            if(!isLeft){
                gun.flip();
                knife.flip();
                isLeft = true;
            }
        }
        else
        {//press nothing and do nothing
            hSpeed=0;
        }
        if(Greenfoot.isKeyDown("up") &&onGround()&&vSpeed>=0)
        {//press up and push shiro up
            jump();
        }  
        if(timer > 0)
            timer--;       
        
        gunCooldown--;
        timerShowGun--;
        gun.location(x,y);
        knife.location(x, y);
        if(timerShowGun > 0){
            gun.getImage().setTransparency(255);
        }
        if(knifeCooldown < 0)
            usesKnife = false;
            
        if(Greenfoot.isKeyDown("x") && knifeCooldown < 0){
            knife.getImage().setTransparency(255);
            getWorld().addObject(knife, x+72*direction, y-2);
            knife.resetAnimation();
            knifeCooldown = 20;
            usesKnife = true;
            if(checkRightWall() || checkLeftWall()){
                vSpeed = -17;
            }
        }
        
        if(Greenfoot.isKeyDown("z") && hasGun && !usesKnife){
            if (gunCooldown < 0 && ammo > 0){
                ammo--;
                for(int i  = 7; i >= ammo + 1; i--){
                    lvl.bulletAmmo[i].getImage().setTransparency(0);
                }
                gun.getImage().setTransparency(255);
                gunCooldown = 20;
                timerShowGun = 20;
                gun.shoot();
            }
            if (ammo == 0 && gunCooldown < 0){
                gunCooldown = 70;
                hasGun = false;
                gun.throww();
                lvl.bulletAmmo[0].getImage().setTransparency(0);
                
            }
        }else{
            if(timerShowGun < 0)gun.getImage().setTransparency(0);
        }
        super.act();
    }
    
    public void animate(){
        if(vSpeed<0){
            changeAnimation(2);//jump
            ((border)lvl.border).resetAnimation();
            ((border)lvl.border).animate=false;
        }
        else if(vSpeed>0){
            changeAnimation(3);//fall
            ((border)lvl.border).resetAnimation();
            ((border)lvl.border).animate=false;
        }
        else{
            if(hSpeed!=0){
                changeAnimation(0);//walk
                ((border)lvl.border).animate=true;
                
            }
            else{
                changeAnimation(1);//idle
                ((border)lvl.border).resetAnimation();
                ((border)lvl.border).animate=false;
            }   
        }
        if(isLeft){
            direction=-1;
        }
        else{
            direction=1;
        }
    }
    public void jump()
    {
        jumpParticles idk=new jumpParticles();
        idk.location(x,y);
        getWorld().addObject(idk, this.getX(),this.getY());
        timer = 20;
        vSpeed =-jumpStrength;
    }
    
    public void scaleShiro(int scalar){
        setImage(scaleSprite(getImage(),3));
        //scale collider
        leftUpCorner=leftUpCorner.multiply(scalar);
        rightUpCorner=rightUpCorner.multiply(scalar);
        leftDownCorner=leftDownCorner.multiply(scalar);
        leftDownCorner.x+=(scalar-1);
        rightDownCorner=rightDownCorner.multiply(scalar);
        rightDownCorner.x+=(scalar-1);
    }
    public void reloadGun(){
        for(int i  = 7; i >= 0; i--){
            lvl.bulletAmmo[i].getImage().setTransparency(255);
        }
        reloadTimer = 70;
        ammo = 7;
        hasGun = true;
    }
    public void takeDmg(){
        hp--;
        ((border)lvl.border).currentAnimation++;
    }
    public boolean isKeyJustPressed(String key) {//fixing some of the engines inferiority
        boolean currentKeyPressed = Greenfoot.isKeyDown(key);
        boolean result = currentKeyPressed && !previousKeyPressed;
        previousKeyPressed = currentKeyPressed;
        return result;
    }
}