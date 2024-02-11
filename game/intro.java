import greenfoot.*;

public class intro extends idk {
    private int timer = 0; //timer for controlling the fade effect
    private int fadeInDuration = 120; //duration of the fade-in effect (in frames)
    private int imageIndex = 0; //index of the current image
    private GreenfootImage[] images = { //array to store the images
        new GreenfootImage("STORY1.png"),
        new GreenfootImage("STORY2.png"),
        new GreenfootImage("STORY3.png")
    };
    
    public intro() {
        GreenfootImage img = new GreenfootImage(22, 222);
        img.setColor(Color.BLACK);
        img.drawString("Press \"q\" to continue!", 21 ,1);
        Label label = new Label();
        addObject(label, 222, 800);
    }
    
    public void act() {
        if (timer < fadeInDuration) {
            // Apply fade-in effect
            int alpha = (255 * timer) / fadeInDuration; //calculate alpha value
            images[imageIndex].setTransparency(alpha); //apply transparency to the current image
            getBackground().drawImage(images[imageIndex], 0, 0); //draw the current image with transparency
            timer++;
            if(timer>=fadeInDuration){
                Greenfoot.delay(180);
            }
        } else {
            //switch to the next image after the fade-in effect completes
            imageIndex++;
            timer = 0; // reset the timer
            if (imageIndex >= images.length) {
                //transition to lvl1 after displaying all images
                Greenfoot.setWorld(new lvl1());
            }
        }
        
        if(Greenfoot.isKeyDown("q")){
            Greenfoot.setWorld(new lvl1());
        }
    }
}

class Label extends Actor{
    public Label(){
        GreenfootImage img = new GreenfootImage(530,130);
        img.setColor(Color.BLACK);
        
        img.drawString("Press \"q\" to skip intro!", 111 ,22);
        setImage(img);
    }
    
    public void act(){
    
    }
}