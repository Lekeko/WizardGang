import greenfoot.*;
import java.util.Random;
public class enemy extends enemies
{
    private boolean isMoving = false;
    private int movingCooldown = 0;
    private int hp = 3;
    private boolean isLeft=false;
    private int speed=9;
    private int jumpForce=19;
    private int shootTimer=0;
    private boolean SHOOT=false;
    public enemy(){
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
                new GreenfootImage("enemy1.png"),
            },
            {
                new GreenfootImage("enemy1_walk1.png"),
                new GreenfootImage("enemy1_walk2.png"),
                new GreenfootImage("enemy1_walk3.png"),
                new GreenfootImage("enemy1_walk4.png"),
                new GreenfootImage("enemy1_walk5.png"),
                new GreenfootImage("enemy1_walk6.png"),
            },
            {
                new GreenfootImage("enemy1_gun1.png"),
            },
            {
                new GreenfootImage("enemy1_gun1.png"),
                new GreenfootImage("enemy1_gun2.png"),
                new GreenfootImage("enemy1_gun3.png"),
            },
            {
                new GreenfootImage("enemy1_jump.png"),
            },
            {
                new GreenfootImage("enemy1_fall.png"),
            },
        };

    }
    public void addedToWorld(World world) {
        lvl=((level)getWorld());
    }
    public void act()
    {
        if(isOnScreen){
            getImage().setTransparency(255);
            if(lvl.player.x<x&&!isLeft){
                isLeft=true;
            }
            else if(lvl.player.x>x&&isLeft){
                isLeft=false;
            }
            super.act();
            animate();
            if(damageCooldown <= 0){
                if((this.isTouching(knife.class)&&lvl.player.knifee.dealingDmg)){
                    takeDmg(2);
                    damageCooldown=27;
                }
                else if(this.isTouching(playerBoom.class)){
                    takeDmg(1);
                    damageCooldown=27;
                }
            }
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
                
                else{
                    if(lvl.player.x>x)
                        aimGun(1);
                    else{
                        aimGun(-1);
                    }   
                }
            
            if(hp<=0){
                getWorld().removeObject(this);
            }   
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
    }
    private void animate(){
        if(vSpeed<0){
            changeAnimation(4);//jump
        }
        else if(vSpeed>0){
            changeAnimation(5);//fall
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
            else if(lvl.player.y<y-200){
                changeAnimation(0);//idle
            }
            else{
                if(SHOOT&&currentAnimation!=3){
                    changeAnimation(3);
                    oneTimeAnimation=true;
                    
                    bullet blt=new enemyBullet();
                    blt.location(x+27*direction, y-10);
                    if(direction<0){
                        blt.flip();
                    }
                    getWorld().addObject(blt, x+27*direction, y-10);
                }
                if(finishedAnimating){
                    finishedAnimating=false;
                    SHOOT=false;
                }
                if(!SHOOT){
                    changeAnimation(2);//aim
                }
            }
        }
        if(isLeft){
            direction=-1;
        }
        else{
            direction=1;
        }
    }
    private void aimGun(int idk){
        hSpeed=0;
        shootTimer++;
        if(shootTimer>=77){
            shootTimer=0;
            SHOOT=true;
        }
        //changeAnimation(2);
    }
}
