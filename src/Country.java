import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.List;

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

    public String getCountryFL() {
        return countryFL;
    }

    private String countryFL;
    private double countryCitizenHappiness;
    private final static int size = 150;
    private List<Order> order;

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
                   String countryName, int countryWorth, int countryCash, int countryGold, double countryCitizenHappiness,String countryFL) {
        super(x, y);
        this.countryImage = countryImage;
        this.countryFL = countryFL;
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
        g2d.drawString(String.format("%s: %d$", "Worth", dynamicWorth()), position.getIntX(), position.getIntY() + 194);

        g2d.setColor(new Color(0, 100, 0));
        g2d.setFont(lightFont);
        g2d.drawString(String.format("%s: %d$", "Cash", countryCash), position.getIntX(), position.getIntY() + 220);

        g2d.setColor(Color.YELLOW);
        g2d.setFont(lightFont);
        g2d.drawString(String.format("%s: %d$", "Gold", countryGold), position.getIntX(), position.getIntY() + 246);

        g2d.setColor(new Color(180, 0, 0));
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
    public void buyGoldOrder(int _buyedGoldAmount){
        this.countryGold += _buyedGoldAmount;
        this.countryCash -= Common.getGoldPrice().getCurrentPrice()*_buyedGoldAmount;
    }
    public void sellGoldOrder(int _selledGoldAmount){
        this.countryGold -= _selledGoldAmount;
        this.countryCash += Common.getGoldPrice().getCurrentPrice()*_selledGoldAmount;
    }
    public int dynamicWorth(){
        return (int) (this.countryCash+this.countryGold*Common.getGoldPrice().getCurrentPrice());
    }
    @Override
    public void step() {
        if (countryCitizenHappiness < 50) {
            //GenerateFoodOrElectronic()
        }
        int rnd = Common.getRandomGenerator().nextInt() & 0x1;
        if(rnd == 0){
            Common.getOrders().add(new BuyGoldOrder(position.getIntX()+24
            +Common.getRandomGenerator().nextInt(50),
                    position.getIntY()+24
                            +Common.getRandomGenerator().nextInt(50),
                    this,Math.abs(Common.getRandomGenerator().nextInt() % 5) + 1,
                    Math.abs(Common.getRandomGenerator().nextInt() % 5),
                    position
                    ).createNewOrder(this));
        }
        else{
           // Common.getOrders().add(Common.getGoldOrders().get(rnd));
        }
    }
    // TODO
    // Country image is 150 x 150
    // Name RGB --> Black
    // Worth RGB --> Blue
    // Cash RGB --> (0, 100, 0)
    // Gold RGB --> Yellow
    // Happiness RGB --> (180, 0, 0)
}