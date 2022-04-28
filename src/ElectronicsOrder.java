import java.awt.*;

public class ElectronicsOrder extends Order {
    public ElectronicsOrder(double x, double y, Country country, int amount, int speed, Position target) {
        super(x, y);
        this.country = country;
        this.amount = amount;
        this.speed = speed;
        this.target = target;
    }

    /***
     * In this method, we used the Graphics2D library to plot all Electronic Orders generated randomly if related country's citizen happiness drops below %50
     * First we write the Color, then the font and finally the Strings in g2d.
     * @param g2d Graphics2D
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setFont(font);
        g2d.setPaint(new Color(0, 182, 204));
        g2d.fillOval(position.getIntX() + 4, position.getIntY() + 4, 24, 24);
        g2d.drawString(country.getCountrySf(), position.getIntX(), position.getIntY());
        //Center the Text in Circle
        g2d.setColor(Color.BLACK);
        g2d.drawString(Integer.toString(this.amount), position.getIntX() + 10, position.getIntY() + 22);
    }

    @Override
    public void step() {
        double TargetX = target.getX() - position.getX();
        double TargetY = target.getY() - position.getY();
        double totalDistance = Math.sqrt(Math.pow(TargetX, 2) + Math.pow(TargetY, 2));
        if (totalDistance < 8) {
            this.country.buyElectronicDevice(this.amount);
            this.completed = true;
        } else {
            this.position.setX(this.position.getX() + (TargetX / (totalDistance / speed)));
            this.position.setY(this.position.getY() + (TargetY / (totalDistance / speed)));
        }
    }

    /***
     * It Creates a new instance of a ElectronicOrder by creating amount and speed of the order randomly.
     * How? It creates a random number between 1-5 by using the instance created in Common class
     * Then we are using these numbers while creating our new instance
     * @param country Country
     * @return It returns a new instance of a ElectronicOrder
     */
    Order createNewOrder(Country country) {
        int amount = Math.abs(Common.getRandomGenerator().nextInt(5) + 1);
        int speed = Math.abs(Common.getRandomGenerator().nextInt(5) + 1);
        int targetPos = Common.getRandomGenerator().nextInt() % Common.getWindowWidth();
        if (targetPos < 0) targetPos *= -1;

        return new ElectronicsOrder(
                country.getPosition().getX() + 25 + Common.getRandomGenerator().nextInt(50),
                country.getPosition().getY() - 15, country,
                amount,
                speed,
                new Position(targetPos, Common.getHorizontalLineY())
        );
    }

}