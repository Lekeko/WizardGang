import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class shiro extends Actor
{
    private int vSpeed=0;
    private int acceleration = 1;
    private boolean jumping=false;
    private int jumpStrength = 16;
    private int speed = 4;
    private int direction = 1; // 1 = right and -1 = left
    private int animationCounter = 0;
    private int frame = 1;
    public shiro(){
        GreenfootImage originalImage = getImage();
        int newWidth = originalImage.getWidth() * 3;
        int newHeight = originalImage.getHeight() * 3;
        GreenfootImage scaledImage = new GreenfootImage(originalImage);
        scaledImage.scale(newWidth, newHeight);
        setImage(scaledImage);
    }
    public void act()
    {
        if(onGround())
        {
            vSpeed = 0;
        }
        else
        {
            fall();
        }
        if(Greenfoot.isKeyDown("right"))
        {
            if(checkRightWall()){
            direction = 1;
            moveRight();
        }
        }
        if(Greenfoot.isKeyDown("left"))
        {
            if(checkLeftWall()){
            direction = -1;
            moveLeft();}
        }
        if(Greenfoot.isKeyDown("up") && jumping == false)
        {
            jump();
        }
        colideTheScreen();//platformAbove();unStuck();
        if(Greenfoot.isKeyDown("q")){
            ((level)getWorld()).nextLevel();
        }
    }
    public void colideTheScreen(){
        //upper border
        if (getY()-getImage().getHeight()/2 < 0) {
            setLocation(getX(), getImage().getHeight()/2);
        }
        //left border
        if (getX()-getImage().getWidth()/2<0){
            setLocation(getImage().getWidth()/2, getY());
        }
        //bottom border
        if (getY()+getImage().getHeight()/2 > getWorld().getHeight()) {
            setLocation(getX(), getWorld().getHeight()-getImage().getHeight()/2);
        }
        //right border
        if (getX()+getImage().getWidth()/2 > getWorld().getWidth()) {
            setLocation(getWorld().getWidth()-getImage().getWidth()/2,getY() );
        }
    }
    public void unStuck(){
        if(isTouching(platform.class)){
            setLocation(getX(), getY()+ 1);
        }
    }
    
        public boolean platformAbove()
    {
        int spriteHeight = getImage().getHeight();
        int yDistance = (int)(spriteHeight/-2);
        Actor ceiling = getOneObjectAtOffset(0, yDistance, platform.class);
        if(ceiling != null)
        {
            vSpeed = 1;
            bopHead(ceiling);
            return true;
        }
        else
        {
            return false;
        }
    }
    public void bopHead(Actor ceiling)
    {
        int ceilingHeight = ceiling.getImage().getHeight();
        int newY = ceiling.getY() + (ceilingHeight + getImage().getHeight())/2;
        setLocation(getX(), newY);
    }
    public void fall()
    {
        setLocation(getX(), getY() + vSpeed);
        if(vSpeed <=9)
        {
            vSpeed = vSpeed + acceleration;
        }
        jumping = true;
    }
    public boolean onGround()
    {
        int spriteHeight = getImage().getHeight();
        int yDistance = (int)(spriteHeight/2) + 5;
        Actor ground1 = getOneObjectAtOffset(5, getImage().getHeight()/2+8, platform.class);
        Actor ground2 = getOneObjectAtOffset(-5, getImage().getHeight()/2+8, platform.class);
        if(ground1 == null && ground2 == null)
        {
            
            jumping = true;
            return false;
        }
        else
        {
            moveOnGround(ground1);
            return true;
        }
    }
    public void moveOnGround(Actor ground)
    {
        
        try{int groundHeight = ground.getImage().getHeight();
        int newY = ground.getY() - (groundHeight + getImage().getHeight())/2;
        setLocation(getX(), newY);
        jumping = false;}catch(Exception e){};
    }
    public void moveLeft()
    {
        setLocation(getX()-speed, getY());
        if(animationCounter %4 == 0)
        {
            //animateLeft();
        }
    }
    /*public void animateLeft()
    {
        if(frame == 1)
        {
            setImage(run1l);
        }
        else if(frame == 2)
        {
            setImage(run2l);
        }
        else if(frame == 3)
        {
            setImage(run3l);
        }
        else if(frame == 4)
        {
            setImage(run4l);
            frame = 1;
            return;
        }
        frame++;
    }*/
    public void moveRight()
    {
        setLocation(getX()+speed, getY());
        if(animationCounter % 4 == 0)
        {
            //animateRight();
        }
    }
    /*public void animateRight()
    {
        if(frame == 1)
        {
            setImage(run1r);
        }
        else if(frame == 2)
        {
            setImage(run2r);
        }
        else if(frame == 3)
        {
            setImage(run3r);
        }
        else if(frame == 4)
        {
            setImage(run4r);
            frame = 1;
            return;
        }
        frame++;
    }*/
    public void jump()
    {
        vSpeed = vSpeed - jumpStrength;
        jumping = true;
        fall();
    }
    public boolean checkLeftWall(){
        int spriteWidth = getImage().getWidth();
        int xDistance = (int)(spriteWidth/-2);
        Actor leftWall = getOneObjectAtOffset(xDistance + 14, 0, platform.class);
        if(leftWall == null){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean checkRightWall(){
        int spriteWidth = getImage().getWidth();
        int xDistance = (int)(spriteWidth/-2);
        Actor leftWall = getOneObjectAtOffset(-xDistance-5, 0, platform.class);
        if(leftWall == null){
            return true;
        }else{
            return false;
        }
    }
}
