import greenfoot.*;
public class shiro extends collision
{
    public static boolean isLeft = false; //if the player is facing left
    private boolean previousKeyPressed = false;
    private boolean hasGun = true;
    private int jumpStrength = 18;
    private int animationCounter = 0;
    private int frame = 1;
    private int speed = 5;
    private int hp= 3;
    private int timeSinceLastFrame = 0;
    private int currentFrame = 0;
    private int gunCooldown = 17;
    private int offsetBullet = 0;
    private int offsetGun = 0;
    private int ammo = 7;
    private int timerShowGun = -1;
    private int reloadTimer = 70;
    private Gun gun = new Gun();
    public knife knifee = new knife();
    private level lvl;
    private boolean usesKnife = false;
    private boolean usesGun = false;
    private boolean knockedUp=false;
    public shiro(){
        
        leftUpCorner=new vector2(4,2);
        rightUpCorner=new vector2(28,2);
        leftDownCorner=new vector2(4,32);
        rightDownCorner=new vector2(28,32);
        isLeft = false;//"but you already initialized that" yea sure if i move left and restart the game, shiro will face left even it its initialized right why? idk greenfot inferior engine
        scalar=3;
        setImage(scaleSprite(getImage(), scalar));
        scaleCollider(scalar);
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
        lvl.player=this;
        getWorld().addObject(gun, x+72, y-2);
        getWorld().addObject(knifee, x+82, y-2);
    }
    int knifeCooldown = 20;
    public void act()
    {
        damageCooldown--;
        knifeCooldown--;
        gunCooldown--;
        timerShowGun--;
        
        //move
        if(!hasGun){
            reloadTimer--;
        }
        
        if(reloadTimer < 0){
            reloadGun();
        }
        animate();//move shiro based on where you pushed her (left right up dow)
        if(!knockedUp){
            if(Greenfoot.isKeyDown("right"))
            {//press right and push shiro right
                hSpeed=speed;
                if(isLeft){
                    gun.flip();
                    knifee.flip();
                    isLeft = false;
                }
            }
            else if(Greenfoot.isKeyDown("left"))
            {//press left and push shiro left
                hSpeed=-speed;
                if(!isLeft){
                    gun.flip();
                    knifee.flip();
                    isLeft = true;
                }
            }
            else
            {//press nothing and do nothing
                hSpeed=0;
            }   
        }
        if(onGround()){
            knockedUp=false;
            if(Greenfoot.isKeyDown("up") &&vSpeed>=0)
            {//press up and push shiro up
                jump();
            }     
        }
        if(damageCooldown <= 0 && hp > 0){
            if(isTouching(BoomEnemy.class)){
                if(checkLeftWall(BoomEnemy.class)){
                    hSpeed=15;
                }
                else{
                    hSpeed=-15;
                }
                vSpeed=-12;
                takeDmg();
                knockedUp=true;
            }
            else if(isTouching(slashEffect.class)){
                if(checkLeftWall(slashEffect.class)){
                    hSpeed=9;
                }
                else{
                    hSpeed=-9;
                }
                vSpeed=-12;
                takeDmg();
                knockedUp=true;
            }
            else if(isTouching(dash.class)){
                if(checkLeftWall(dash.class)){
                    hSpeed=5;
                }
                else{
                    hSpeed=-5;
                }
                vSpeed=-17;
                takeDmg();
                knockedUp=true;
            }
            else if(isTouching(boomTrump.class)){
                if(checkLeftWall(boomTrump.class)){
                    hSpeed=5;
                }
                else{
                    hSpeed=-5;
                }
                vSpeed=-17;
                takeDmg();
                knockedUp=true;
            }
            else if(isTouching(eagle.class)){
                if(checkLeftWall(eagle.class)){
                    hSpeed=2;
                }
                else{
                    hSpeed=-2;
                }
                vSpeed=-2;
                takeDmg();
                knockedUp=true;
            }
        }
        gun.location(x,y);
        knifee.location(x, y);
        if(timerShowGun > 0){
            gun.getImage().setTransparency(255);
        }
        if(knifeCooldown < 0)
            usesKnife = false;
        if(Greenfoot.isKeyDown("x") && knifeCooldown < 0&&!usesGun){
            knifee.swing();
            knifeCooldown = 61;
            usesKnife = true;
            if(checkRightWall() || checkLeftWall()){
                vSpeed = -17;
            }
        }
        if(gunCooldown<0){
            usesGun=false;            
        }
        if(Greenfoot.isKeyDown("z") && hasGun && !knifee.active){
            if (gunCooldown < 0 && ammo > 0){
                ammo--;
                for(int i  = 7; i >= ammo + 1; i--){
                    lvl.bulletAmmo[i].getImage().setTransparency(0);
                }
                gun.getImage().setTransparency(255);
                gunCooldown = 17;
                timerShowGun = 17;
                gun.shoot();
                usesGun=true;
            }
            if (ammo == 0 && gunCooldown < 0){
                gunCooldown = 50;
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
        vSpeed =-jumpStrength;
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
        damageCooldown =77;
        hp--;
        if(((border)lvl.border).currentAnimation<3){
            ((border)lvl.border).currentAnimation++;   
        }
        if(hp<=0){
            Greenfoot.setWorld(new deathScreen());
        }
    }
    public boolean isKeyJustPressed(String key) {//fixing some of the engines inferiority
        boolean currentKeyPressed = Greenfoot.isKeyDown(key);
        boolean result = currentKeyPressed && !previousKeyPressed;
        previousKeyPressed = currentKeyPressed;
        return result;
    }
    private void die(){
        Greenfoot.setWorld(new finale());
    }
}