import greenfoot.*;
import java.util.Random;
public class enemy extends collision
{
    boolean isMoving = false;
    int movingCooldown = 0;
    public enemy(){
        leftUpCorner=new vector2(0,0);
        rightUpCorner=new vector2(32,0);
        leftDownCorner=new vector2(0,32);
        rightDownCorner=new vector2(32,32);
        scaleShiro(3);
        image=getImage();
        spriteHeight=getImage().getHeight();
        halfWidthSprite=getImage().getWidth()/2;
        halfHeightSprite=getImage().getHeight()/2;
        /*Random random = new Random();

        // Generate a random integer (0 or 1)
        int randomNumber = random.nextInt(2); // Generates a random integer between 0 (inclusive) and 2 (exclusive)

        // Map 0 to -1 and 1 to 1
        int result = (randomNumber == 0) ? -1 : 1;
        hSpeed=6*result;*/
    }
    public void act()
    {
        //goombaWalk();
        if (!isOnScreen){
            hSpeed=0;
        }
        if(x < 798 && x > 2 && !isMoving){
            wonderAround();
        }
        
        if(movingCooldown <= 0){
            isMoving = false;
        }
        movingCooldown--;
        //jumpOverWall();
        super.act();
    }
    
    
    
    public void wonderAround(){
        Random random= new Random();
        int option = random.nextInt(3) + 1 - 2;
        int distance = random.nextInt(40) + 1;
        hSpeed = option * 10 ;
        isMoving = true;
        movingCooldown = distance * 2;
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
    public void goombaWalk(){
        shiro player = (shiro)getWorld().getObjects(shiro.class).get(0);
        if(checkRightWall()){
            hSpeed=-10;
        }
        else if (checkLeftWall()){
            hSpeed=10;
        }
        if((checkRightWall()||checkLeftWall())&&onGround()){
            vSpeed=-20;
        }
    }
}
