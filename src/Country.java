import java.awt.*;

public class Country extends Entity {

    private final static int size = 150;
    // Private Fields :
    private final Font lightFont = new Font("Verdana", Font.PLAIN, 20);
    private final Font boldFont = new Font("Verdana", Font.BOLD, 20);
    private final String countrySF;
    private Image countryImage;
    private String countryName;
    private int countryWorth;
    private double countryCash;
    private int countryGold;
    private double countryCitizenHappiness;

    // Constructor
    public Country(double x, double y, Image countryImage,
                   String countryName, int countryWorth, int countryCash, int countryGold, double countryCitizenHappiness, String countrySF) {
        super(x, y);
        this.countryImage = countryImage;
        this.countrySF = countrySF;
        this.countryName = countryName;
        this.countryWorth = countryWorth;
        this.countryCash = countryCash;
        this.countryGold = countryGold;
        this.countryCitizenHappiness = countryCitizenHappiness;
    }


    //Getter
    public String getCountrySf() {
        return countrySF;
    }

    public Image getCountryImage() {
        return countryImage;
    }

    //Setter
    public void setCountryImage(Image countryImage) {
        this.countryImage = countryImage;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryWorth() {
        return countryWorth;
    }

    public void setCountryWorth(int countryWorth) {
        this.countryWorth = countryWorth;
    }

    public int getCountryCash() {
        return (int) countryCash;
    }

    public void setCountryCash(double countryCash) {
        this.countryCash += countryCash;
    }

    public int getCountryGold() {
        return countryGold;
    }

    public void setCountryGold(int countryGold) {
        this.countryGold = countryGold;
    }

    public double getCountryCitizenHappiness() {
        return countryCitizenHappiness;
    }

    public void setCountryCitizenHappiness(double countryCitizenHappiness) {
        this.countryCitizenHappiness += countryCitizenHappiness;
    }


    //Overridden Methods

    /***
     * In this method, we used the Graphics2D library to plot all Countries defined as static.
     * First we write the Color, then the font and finally the Strings in g2d.
     * We used the drawImage method while adding an image.     *
     * @param g2d Graphics2D
     */
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
        g2d.drawString(String.format("%s: %d$", "Cash", getCountryCash()), position.getIntX(), position.getIntY() + 220);

        g2d.setColor(Color.YELLOW);
        g2d.setFont(lightFont);
        g2d.drawString(String.format("%s: %d$", "Gold", getCountryGold()), position.getIntX(), position.getIntY() + 246);

        g2d.setColor(new Color(180, 0, 0));
        g2d.setFont(lightFont);
        g2d.drawString(String.format("%s: %.1f$", "Happiness", getCountryCitizenHappiness()), position.getIntX(), position.getIntY() + 272);

        g2d.drawImage(countryImage, getPosition().getIntX(), getPosition().getIntY(),
                size, size, (img, infoflags, x, y, width, height) -> false);

    }


    /**
     * This method is created to make the Country Object generate Gold Orders at Random times. If the happiness level of the citizens of the country
     * drops below 50 percent, the Country Object starts to produce Electronic and Food Orders as well.
     * Gold Production :
     * - First, generate a random number between 0 and 1. If random number is equal to 0, then Country Creates BuyGoldOrder, else SellGoldOrder
     * - We are sending some parameters to GoldOrder subclasses, such as position, and  a random amount number between 1-5
     *
     * @author Ali Çağatay ÜN
     */

    @Override
    public void step() {
        if (countryCitizenHappiness < 50) {
            int rnd = Common.getRandomGenerator().nextInt() & 0x1;
            if (rnd == 0) {
                ElectronicsOrder by = new ElectronicsOrder(position.getIntX() + 24
                        + Common.getRandomGenerator().nextInt(50),
                        position.getIntY() + 24
                                + Common.getRandomGenerator().nextInt(50),
                        this, Math.abs(Common.getRandomGenerator().nextInt() % 5) + 1,
                        Math.abs(Common.getRandomGenerator().nextInt() % 5),
                        position
                );
                ElectronicsOrder sd = (ElectronicsOrder) by.createNewOrder(this);
                sd.setGoldBuyOrSellOrder(false);
                Common.getOrders().add(sd);
            } else {
                FoodOrder by = new FoodOrder(position.getIntX() + 24
                        + Common.getRandomGenerator().nextInt(50),
                        position.getIntY() + 24
                                + Common.getRandomGenerator().nextInt(50),
                        this, Math.abs(Common.getRandomGenerator().nextInt() % 5) + 1,
                        Math.abs(Common.getRandomGenerator().nextInt() % 5),
                        position
                );
                FoodOrder sd = (FoodOrder) by.createNewOrder(this);
                sd.setGoldBuyOrSellOrder(false);
                Common.getOrders().add(sd);
            }
        }
        int rnd = Common.getRandomGenerator().nextInt() & 0x1;
        if (rnd == 0) {
            BuyGoldOrder by = new BuyGoldOrder(position.getIntX() + 24
                    + Common.getRandomGenerator().nextInt(50),
                    position.getIntY() + 24
                            + Common.getRandomGenerator().nextInt(50),
                    this, Math.abs(Common.getRandomGenerator().nextInt() % 5) + 1,
                    Math.abs(Common.getRandomGenerator().nextInt() % 5),
                    position
            );
            BuyGoldOrder sd = (BuyGoldOrder) by.createOrder(this);
            sd.setGoldBuyOrSellOrder(true);
            Common.getOrders().add(sd);
        } else {
            SellGoldOrder by = new SellGoldOrder(position.getIntX() + 24
                    + Common.getRandomGenerator().nextInt(50),
                    position.getIntY() + 24
                            + Common.getRandomGenerator().nextInt(50),
                    this, Math.abs(Common.getRandomGenerator().nextInt() % 5) + 1,
                    Math.abs(Common.getRandomGenerator().nextInt() % 5),
                    position
            );

            if (getCountryGold() - by.amount > 0) {
                SellGoldOrder sd = (SellGoldOrder) by.createOrder(this);
                sd.setGoldBuyOrSellOrder(true);
                sd.setSell(true);
                Common.getOrders().add(sd);
            }

        }

    }

    // Other Methods

    public void buyGoldOrder(int _buyedGoldAmount) {
        this.countryGold += _buyedGoldAmount;
        this.countryCash -= Common.getGoldPrice().getCurrentPrice() * _buyedGoldAmount;
    }

    public void sellGoldOrder(int _selledGoldAmount) {
        this.countryGold -= _selledGoldAmount;
        this.countryCash += Common.getGoldPrice().getCurrentPrice() * _selledGoldAmount;
    }

    public int dynamicWorth() {
        return (int) (this.countryCash + this.countryGold * Common.getGoldPrice().getCurrentPrice());
    }

    public void buyElectronicDevice(int amount) {
        this.countryCash -= Common.getElectronicsPrice().getCurrentPrice() * amount;
        this.countryCitizenHappiness += 0.4 * amount;
    }

    public void buyFood(int amount) {
        this.countryCash -= Common.getFoodPrice().getCurrentPrice() * amount;
        this.countryCitizenHappiness += 0.2 * amount;
    }

}