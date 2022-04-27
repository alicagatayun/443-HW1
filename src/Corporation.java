import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.List;

public class Corporation extends Entity {
    public Corporation(double x, double y) {
        super(x, y);
    }
    private State state;
    private List<Order> _liveOrders;
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
/*
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

*/
    public Corporation(double x, double y, Image corporationImage, String corporationName, int corporationCash,State _initialState,List<Order> _orders) {
        super(x, y);
        this.corporationImage = corporationImage;
        this.corporationName = corporationName;
        this.corporationCash = corporationCash;
        this.state = _initialState;
        this._liveOrders = _orders;
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
        pos = state.getNextMove(position);
        position.setX(pos.getIntX());
        position.setY(pos.getIntY());

        g2d.setColor(Color.BLACK);
        g2d.setFont(boldFont);
        g2d.drawString(corporationName, position.getIntX() +25, position.getIntY() -7);

        g2d.setColor(Color.BLUE);
        g2d.setFont(boldFont);
        g2d.drawString(state.getCurrentStateName(), position.getIntX() +25, position.getIntY() +120);

        g2d.setColor(new Color(180,0,0));
        g2d.setFont(boldFont);
        g2d.drawString(getCorporationCashString(), position.getIntX() +40, position.getIntY() +145);

        g2d.drawImage(corporationImage, position.getIntX(), position.getIntY(),
                imageSize, imageSize, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y,int width, int height) {
                        return false;
                    }
                });
    }

    @Override
    public void step() {
        state = Common.ChangeState();

    }
    // TODO
    // Corporation image is 100 x 100
    // Cash RGB --> (180, 0, 0)
    // Badge is 20 x 20
}