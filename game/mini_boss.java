import greenfoot.*;
import java.util.Random;
public class mini_boss extends enemies{
    boolean isMoving = false;
    int movingCooldown = 0;
    int hp = 5;
    public mini_boss(){
        leftUpCorner=new vector2(0,0);
        rightUpCorner=new vector2(32,0);
        leftDownCorner=new vector2(0,32);
        rightDownCorner=new vector2(32,32);
        scaleShiro(3);
        image=getImage();
        spriteHeight=getImage().getHeight();
        halfWidthSprite=getImage().getWidth()/2;
        halfHeightSprite=getImage().getHeight()/2;
    }
    public void act()
    {
        
        //goombaWalk();
        super.act();
        if (!isOnScreen){
            hSpeed=0;
        }
        if(x < 798 && x > 2 && !isMoving){
            followPlayer();
            
    
        }
        if(isMoving && vSpeed < 0){
            if(this.getX() > 400){
                direction = -1;
            }else direction = 1;
            if(this.getX() == 0){
                direction = 0;
            }
            hSpeed = 4 * direction; 
        }
        Random random = new Random();
        int shouldStop = random.nextInt(100) + 1;
        if(shouldStop > 90){
            hSpeed = 0;
        }
        
        if(movingCooldown <= 0){
            isMoving = false;
        }
        movingCooldown--;
        //jumpOverWall();
        super.act();
    }
    
    
    
    public void followPlayer(){
        Random random= new Random();
        int direction = 0;
        
        
        if(this.getX() > 400){
            direction = -1;
        }else direction = 1;
        if(this.getX() == 0){
            direction = 0;
        }
       
        if(random.nextInt(300) + 1 > 50){
            vSpeed = -15;
            hSpeed = 4 * direction;
        }
        
        
        
    
        
        hSpeed = 4 * direction;
        isMoving = true;
        movingCooldown = 85;
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
}
    


