import java.awt.*;
import java.awt.image.ImageObserver;

public class Country extends Entity {
    public Country(double x, double y) {
        super(x, y);
    }

    private final Font lightFont = new Font("Verdana", Font.PLAIN, 20);
    private final Font boldFont = new Font("Verdana", Font.BOLD, 20);
    private Image countryImage;
    private String countryName;
    private int countryWorth;
    private int countryCash;
    private int countryGold;
    private double countryCitizenHappiness;
    private final static int size = 150;

    public Image getCountryImage() {
        return countryImage;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getCountryWorth() {
        return countryWorth;
    }

    public int getCountryCash() {
        return countryCash;
    }

    public void setCountryImage(Image countryImage) {
        this.countryImage = countryImage;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Country(double x, double y, Image countryImage,
                   String countryName, int countryWorth, int countryCash, int countryGold, double countryCitizenHappiness) {
        super(x, y);
        this.countryImage = countryImage;
        this.countryName = countryName;
        this.countryWorth = countryWorth;
        this.countryCash = countryCash;
        this.countryGold = countryGold;
        this.countryCitizenHappiness = countryCitizenHappiness;
    }

    public void setCountryWorth(int countryWorth) {
        this.countryWorth = countryWorth;
    }

    public void setCountryCash(int countryCash) {
        this.countryCash = countryCash;
    }

    public void setCountryGold(int countryGold) {
        this.countryGold = countryGold;
    }

    public void setCountryCitizenHappiness(double countryCitizenHappiness) {
        this.countryCitizenHappiness = countryCitizenHappiness;
    }

    public int getCountryGold() {
        return countryGold;
    }

    public double getCountryCitizenHappiness() {
        return countryCitizenHappiness;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setFont(boldFont);
        g2d.drawString(countryName, getPosition().getIntX() + 35, getPosition().getIntY() + 170);

        g2d.setColor(Color.BLUE);
        g2d.setFont(lightFont);
        g2d.drawString(String.format("%s: %d$", "Worth", countryWorth), position.getIntX(), position.getIntY() + 194);

        g2d.setColor(new Color(0,100,0));
        g2d.setFont(lightFont);
        g2d.drawString(String.format("%s: %d$", "Cash", countryCash), position.getIntX(), position.getIntY() + 220);

        g2d.setColor(Color.YELLOW);
        g2d.setFont(lightFont);
        g2d.drawString(String.format("%s: %d$", "Gold", countryGold), position.getIntX(), position.getIntY() + 246);

        g2d.setColor(new Color(180,0,0));
        g2d.setFont(lightFont);
        g2d.drawString(String.format("%s: %.1f$", "Happiness", countryCitizenHappiness), position.getIntX(), position.getIntY() + 272);

        g2d.drawImage(countryImage, getPosition().getIntX(), getPosition().getIntY(),
                size, size, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });

        //g2d.drawString(String.format("%s: %.2f$", countryName, currentPrice), position.getIntX(), position.getIntY()+10);
    }

    @Override
    public void step() {

    }
    // TODO
    // Country image is 150 x 150
    // Name RGB --> Black
    // Worth RGB --> Blue
    // Cash RGB --> (0, 100, 0)
    // Gold RGB --> Yellow
    // Happiness RGB --> (180, 0, 0)
}