import greenfoot.*;
public class menu extends idk
{
    static GreenfootSound sound ;
    public menu()
    {
        sound=new GreenfootSound("level2.mp3");
        sound.setVolume(20);
        sound.play();
        playButton playbutton=new playButton();
        quitButton quitbutton=new quitButton();
        continueButton continuebutton=new continueButton();
        addObject(playbutton,400,600);
        addObject(quitbutton,650,650);
        addObject(continuebutton,150,650);
    }
    public void act(){
    }
}
