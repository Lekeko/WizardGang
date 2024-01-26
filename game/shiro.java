import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class shiro extends Actor
{
    public shiro(){
        GreenfootImage originalImage = getImage();
        int newWidth = originalImage.getWidth() * 2;
        int newHeight = originalImage.getHeight() * 2;
        GreenfootImage scaledImage = new GreenfootImage(originalImage);
        scaledImage.scale(newWidth, newHeight);
        setImage(scaledImage);
    }
    public void act()
    {
        gravity();
        moveAround();
        colideTheScreen();
        if(Greenfoot.isKeyDown("q")){
            ((level)getWorld()).nextLevel();
        }
    }
        public void moveAround(){
        if(Greenfoot.isKeyDown("right")){
            setLocation(getX()+7, getY());
        }
        if(Greenfoot.isKeyDown("left")){
            setLocation(getX()-7, getY());
        }
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - 7);
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + 7);
        }
    }
    public void gravity(){
        setLocation(getX(), getY()+2);
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
}
