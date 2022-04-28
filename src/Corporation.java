import java.awt.*;

public class Corporation extends Entity {
    private final static int imageSize = 100;
    private final static int badgeSize = 20;
    private final Font boldFont = new Font("Verdana", Font.BOLD, 20);
    private State state;
    private Image corporationImage;
    private String corporationName;
    private double corporationCash;
    private int totalStep = 0;

    public Corporation(double x, double y) {
        super(x, y);
    }

    public Corporation(double x, double y, Image corporationImage, String corporationName, int corporationCash, State state) {
        super(x, y);
        this.corporationImage = corporationImage;
        this.corporationName = corporationName;
        this.corporationCash = corporationCash;
        this.state = state;
    }

    public void setCorporationCash(double corporationCash) {
        this.corporationCash += corporationCash;
    }

    public Image getCorporationImage() {
        return corporationImage;
    }

    public void setCorporationImage(Image corporationImage) {
        this.corporationImage = corporationImage;
    }

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public String getCorporationCashString() {
        return String.valueOf((int) corporationCash);
    }

    public int getCorporationCashInteger() {
        return (int) corporationCash;
    }

    /***
     * In this method, we used the Graphics2D library to plot all Corporations defined as static.
     * First we write the Color, then the font and finally the Strings in g2d.
     * We used the drawImage method while adding an image.     *
     * And if a company's total money is within a certain range, we show rectangular colors above the corporation.
     * @param g2d Graphics2D
     */
    @Override
    public void draw(Graphics2D g2d) {

        g2d.setColor(Color.BLACK);
        g2d.setFont(boldFont);
        g2d.drawString(corporationName, position.getIntX() + 25, position.getIntY() - 7);

        g2d.setColor(Color.BLUE);
        g2d.setFont(boldFont);
        g2d.drawString(state.getCurrentStateName(), position.getIntX() + 25, position.getIntY() + 120);

        g2d.setColor(new Color(180, 0, 0));
        g2d.setFont(boldFont);
        g2d.drawString(getCorporationCashString(), position.getIntX() + 40, position.getIntY() + 145);

        g2d.drawImage(corporationImage, position.getIntX(), position.getIntY(),
                imageSize, imageSize, (img, infoflags, x, y, width, height) -> false);

        if (corporationCash > 6000) {
            g2d.setPaint(Color.WHITE);
            g2d.fillRect(position.getIntX() + 20, position.getIntY() - 45, badgeSize, badgeSize);
            g2d.setPaint(Color.YELLOW);
            g2d.fillRect(position.getIntX() + 50, position.getIntY() - 45, badgeSize, badgeSize);
            g2d.setPaint(Color.RED);
            g2d.fillRect(position.getIntX() + 80, position.getIntY() - 45, badgeSize, badgeSize);
        } else if (corporationCash > 4000) {
            g2d.setPaint(Color.WHITE);
            g2d.fillRect(position.getIntX() + 20, position.getIntY() - 45, badgeSize, badgeSize);
            g2d.setPaint(Color.YELLOW);
            g2d.fillRect(position.getIntX() + 50, position.getIntY() - 45, badgeSize, badgeSize);
        } else if (corporationCash > 2000) {
            g2d.setPaint(Color.WHITE);
            g2d.fillRect(position.getIntX() + 20, position.getIntY() - 45, badgeSize, badgeSize);
        }


    }


    /***
     * Corporation Step Method is used to step up a corporation. In order not to cause state changes instantly, I've determined a threshold.
     * Before reaching the threshold, every step corporation call a method in the States, named as getNextMove(this).
     * It does not return but writes new position of the corporation inside the state variable.
     */
    @Override
    public void step() {
        state.getNextMove(this);
        if (++totalStep % 500 == 0) {
            state = Common.ChangeState();
            totalStep = 0;
        }

    }

}