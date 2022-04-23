import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Random;

public class Corporation extends Entity {
    public Corporation(double x, double y) {
        super(x, y);
    }
    private State state;
    private Image corporationImage;
    private String corporationName;
    private int corporationCash;
    private final static int imageSize = 100;
    private final static int badgeSize=20;

    enum Colors{
        YELLOW,
        RED,
        WHITE,
        NONE
    }
    enum States{
        Rest,
        GotoXY,
        Shake,
        Chase
    }
    private States currentState=States.Rest;
    Colors  getCurrentBadgesColor(){
        if(corporationCash > 6000 ){
            return Colors.RED;
        }
        else if(corporationCash > 4000){
            return Colors.YELLOW;
        }
        else if(corporationCash > 2000){
            return Colors.WHITE;
        }
        return Colors.NONE;
    }
    private Rest _restState = new Rest();
    private ChaseClosest _chaseClosestState = new ChaseClosest();
    private GotoXY _gotoXYState=new GotoXY();
    private Shake _shakeState=new Shake();

    public Corporation(double x, double y, Image corporationImage, String corporationName, int corporationCash) {
        super(x, y);
        this.corporationImage = corporationImage;
        this.corporationName = corporationName;
        this.corporationCash = corporationCash;

    }

    public void setCorporationImage(Image corporationImage) {
        this.corporationImage = corporationImage;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public void setCorporationCash(int corporationCash) {
        this.corporationCash = corporationCash;
    }

    public Image getCorporationImage() {
        return corporationImage;
    }

    public String getCorporationName() {
        return corporationName;
    }

    public String getCorporationCashString() {
        return String.valueOf(corporationCash);
    }
    public int getCorporationCashInteger() {
        return corporationCash;
    }

    private final Font boldFont = new Font("Verdana", Font.BOLD, 20);
    @Override
    public void draw(Graphics2D g2d) {
        Position pos;
        switch (currentState){
            case Rest:
                _restState.getNextMove(position);
                break;
            case Shake:
                pos = _shakeState.getNextMove(position);
                position.setX(pos.getIntX());
                position.setY(pos.getIntY());
                break;
            case Chase:
                _chaseClosestState.getNextMove(position);
                break;
            case GotoXY:
                pos = _gotoXYState.getNextMove(position);
                position.setX(pos.getIntX());
                position.setY(pos.getIntY());
                if(_gotoXYState.IsReachedDestination())
                    pickAnotherState();

                break;
        }
        g2d.setColor(Color.BLACK);
        g2d.setFont(boldFont);
        g2d.drawString(corporationName, getPosition().getIntX() +25, getPosition().getIntY() -7);

        g2d.setColor(Color.BLUE);
        g2d.setFont(boldFont);
        g2d.drawString(currentState.name(), getPosition().getIntX() +25, getPosition().getIntY() +120);

        g2d.setColor(new Color(180,0,0));
        g2d.setFont(boldFont);
        g2d.drawString(getCorporationCashString(), getPosition().getIntX() +40, getPosition().getIntY() +145);

        g2d.drawImage(corporationImage, getPosition().getIntX(), getPosition().getIntY(),
                imageSize, imageSize, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y,int width, int height) {
                        return false;
                    }
                });
    }
    private void pickAnotherState(){
        Random rnd = new Random();
        currentState = States.values()[(int)(Math.random()*States.values().length)];
        System.out.println(currentState);
        if(currentState==States.GotoXY){
            _gotoXYState.setRandomSpeed();
            _gotoXYState.setPrevVals(position);
        }
    }
    @Override
    public void step() {
        //Burada state'e geçebilir miyim diye soracağız. eğer isAllowedsa random bir state'e geçeceğiz.
        pickAnotherState();


    }
    // TODO
    // Corporation image is 100 x 100
    // Cash RGB --> (180, 0, 0)
    // Badge is 20 x 20
}