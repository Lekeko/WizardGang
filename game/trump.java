import greenfoot.*;
import java.util.Random;
public class trump extends enemies
{
    private GreenfootSound sound = new GreenfootSound("hurt.mp3");
    private boolean isMoving = false;
    private int movingCooldown = 0;
    private int hp = 40;
    private boolean isLeft=false;
    private int speed=4;
    private int jumpForce=19;
    private int shootTimer=0;
    private boolean SHOOT=false;
    private int bulletTimer=0;
    private int eagleTimer=0;
    public trump(){
        sound.setVolume(20);
        leftUpCorner=new vector2(8,1);
        rightUpCorner=new vector2(25,1);
        leftDownCorner=new vector2(8,32);
        rightDownCorner=new vector2(25,32);
        scalar=3;
        setImage(scaleSprite(getImage(), scalar));
        scaleCollider(scalar);
        image=getImage();
        spriteHeight=getImage().getHeight();
        halfWidthSprite=getImage().getWidth()/2;
        halfHeightSprite=getImage().getHeight()/2;
        animate=true;
        animateSpeed = 4;
        imagini = new GreenfootImage[][]{
            {
                new GreenfootImage("boss.png"),
            },
            {
                new GreenfootImage("boss_walk1.png"),
                new GreenfootImage("boss_walk2.png"),
                new GreenfootImage("boss_walk3.png"),
                new GreenfootImage("boss_walk4.png"),
                new GreenfootImage("boss_walk5.png"),
                new GreenfootImage("boss_walk6.png"),
            },
            {
                new GreenfootImage("boss_jump.png"),
            },
            {
                new GreenfootImage("boss_fall.png"),
            },
        };

    }
    public void addedToWorld(World world) {
        lvl=((level)getWorld());
    }
    public void act()
    {
        if(isOnScreen){
            shootTimer++;
            bulletTimer++;
            eagleTimer++;
            getImage().setTransparency(255);
            if(lvl.player.x<x&&!isLeft){
                isLeft=true;
            }
            else if(lvl.player.x>x&&isLeft){
                isLeft=false;
            }
            super.act();
            animate();
            movingCooldown--;
            if(movingCooldown >0){
                if(checkLeftWall()&&!isLeft){
                followPlayer(1);
                }
                else if(checkRightWall()&&isLeft){
                    followPlayer(-1);
                }
                hSpeed=speed*direction;
                if(((checkLeftWall()||checkRightWall())||lvl.player.y<y-200)&&onGround()){
                    vSpeed=-jumpForce;
                }
            }
            else{
                if(lvl.player.x>x+300){
                    followPlayer(1);
                }
                else if(lvl.player.x<x-300){
                    followPlayer(-1);
                }
            if (shootTimer>277&&shootTimer<700){
                if(bulletTimer>=4){
                    SHOOT=true;
                    trumpBullet blt=new trumpBullet();
                    blt.location(x+27*direction, y-10);
                    int rotation = Greenfoot.getRandomNumber(41) - 20; // Generates a random number between -10 and 10
                    blt.setRotation(rotation);
                    if(direction<0){
                        blt.flip();
                    }
                    getWorld().addObject(blt, x+27*direction, y);
                    bulletTimer=0;
                }
                if(shootTimer>1000){
                    shootTimer=0;
                }
            }
        }
        if(damageCooldown <= 4){
                if(this.isTouching(playerBoom.class)){
                    takeDmg(3);
                    damageCooldown=18;
                }
                if(this.isTouching(BoomGun.class)){
                    takeDmg(6);
                    damageCooldown=18;
                }
            }
            if(damageCooldown <= 18){
                if((this.isTouching(knife.class)&&lvl.player.knifee.active)){
                    takeDmg(5);
                    damageCooldown=28;
                }
            }
        if(eagleTimer>=457){
            eagleTimer=0;
            eagle idk = new eagle();

            // Create a Random object
            Random random = new Random();
            
            // Generate a random number between 0 and 1
            int randomValue = random.nextInt(2);
            
            // Adjust the random value to be either 1 or -1
            int result = randomValue == 0 ? -1 : 1;
            idk.location((getWorld().getWidth()+100)*(randomValue),lvl.player.y+21);
            getWorld().addObject(idk, (getWorld().getWidth()+100)*((int)Math.random() * 2 - 1),lvl.player.y+21);
        }
        if(hp<=0){
            getWorld().removeObject(this);
            sound.play();
        } 
    }
    else{
        getImage().setTransparency(0);
    }
}
    
    
    
    public void followPlayer(int idk){
        Random random= new Random();
        hSpeed = speed * idk;
        int idk2=random.nextInt(21) + 17;
        isMoving = true;
        movingCooldown = idk2;   
    }
    private void takeDmg(int dmg){
        hp-=dmg;
        knifeParticles particles = new knifeParticles();
        particles.location(x, y);
        getWorld().addObject(particles, x, y);
        if(hp<=0){
            Greenfoot.setWorld(new finale());
        }
    }
    private void animate(){
        if(vSpeed<0){
            changeAnimation(2);//jump
        }
        else if(vSpeed>0){
            changeAnimation(3);//fall
        }
        else{
            if(hSpeed!=0){
                changeAnimation(1);//walk
                if(hSpeed>0){
                    isLeft=false;
                }
                else{
                    isLeft=true;
                }
                
            }
            else{
                changeAnimation(0);//idle
            }
        }
        if(isLeft){
            direction=-1;
        }
        else{
            direction=1;
        }
    }
}
