import greenfoot.*;
public class shiro extends collision
{
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
    
    private int timeSinceLastFrame = 0;
    private int animateSpeed = 2;
    private int currentFrame = 0;
    
    int gunCooldown = 20;
    int offsetBullet = 0;
    int offsetGun = 0;
    int ammo = 7;
    int timerShowGun = -1;
    boolean hasGun = true;
    Gun gun = new Gun();
    level lvl;
    public shiro(){
        
        leftUpCorner=new vector2(4,2);
        rightUpCorner=new vector2(28,2);
        leftDownCorner=new vector2(4,32);
        rightDownCorner=new vector2(28,32);
        isLeft = false;//"but you already initialized that" yea sure if i move left and restart the game, shiro will face left even it its initialized right why? idk greenfot inferior engine
        scaleShiro(3);
        image=getImage();
        Actor bulletShowcase = new bulletShowcase();
        
        
        spriteHeight=getImage().getHeight();
        halfWidthSprite=getImage().getWidth()/2;
        halfHeightSprite=getImage().getHeight()/2;
        
    }
    public void addedToWorld(World world) {
        lvl=((level)getWorld());
        getWorld().addObject(gun, x+72, y-2);
    }
    public void act()
    {
        //move
        if(Greenfoot.isKeyDown("right"))
        {//press right and push shiro right
            hSpeed=speed;
            processFrame();
            if(isLeft){
                gun.flip();
                isLeft = false;
            }
        }
        else if(Greenfoot.isKeyDown("left"))
        {//press left and push shiro left
            hSpeed=-speed;
            processFrame();
            if(!isLeft){
                gun.flip();
                isLeft = true;
            }
        }
        else
        {//press nothing and do nothing
            hSpeed=0;
        }
        if(Greenfoot.isKeyDown("up") &&onGround())
        {//press up and push shiro up
            jump();
        }  
        animate();//move shiro based on where you pushed her (left right up dow)
        if(timer > 0)
            timer--;       
        
        gunCooldown--;
        timerShowGun--;
        
        try{
            gun.location(x,y);
            if(timerShowGun > 0){
                gun.getImage().setTransparency(255);
            }
            if(Greenfoot.isKeyDown("z") && hasGun){
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
                    gunCooldown = 600;
                    hasGun = false;
                    gun.throww();
                    lvl.bulletAmmo[0].getImage().setTransparency(0);
                    ammo = 7;
                }
            }else{
                if(timerShowGun < 0)gun.getImage().setTransparency(0);
            }
        }catch(Exception e){}
        super.act();
    }
    
    public void animate(){
        if(hSpeed>0){
            setImage(imagini[currentFrame]);
        }
        else if(hSpeed<0){
            setImage(imagini[currentFrame]);
        }
        else{
            setImage("jhonnyIdle.png");
        }
        if(vSpeed<0){
            setImage("jhonnyJum.png");
        }
        else if(vSpeed>0){
            setImage("jhonnyFall.png");
        }
        setImage(scaleSprite(getImage(),3));
        if(isLeft){
            getImage().mirrorHorizontally();   
        }
    }
    public void processFrame(){
        if(timeSinceLastFrame < animateSpeed){
            timeSinceLastFrame++;
        }
        else
        {
            timeSinceLastFrame = 0;
            if(currentFrame < imagini.length-1){
                currentFrame++;
            }
            else
            {
                currentFrame = 0;
            }
        }
    }
    public void jump()
    {
        entity idk=(entity)fart;
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
    /*public void flipCollider(){//creazy IT DOSENT WORK WTF SHADOW WIZZARD MONEy GANG
        int leftDistance = getImage().getWidth() - rightUpCorner.x;
        int rightDistance = getImage().getWidth() - leftUpCorner.x;
        leftUpCorner=new vector2(leftDistance,2);
        rightUpCorner=new vector2(rightDistance,2);
        leftDownCorner=new vector2(leftDistance,32);
        rightDownCorner=new vector2(rightDistance,32);
    }*/
}