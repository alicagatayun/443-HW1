import java.awt.*;

public class BuyGoldOrder extends GoldOrder {
    public BuyGoldOrder(double x, double y, Country country, int amount, int speed, Position target) {
        super(x, y);
        this.country = country;
        this.amount = amount;
        this.speed = speed;
        this.target = target;
    }

    /***
     * It calls the drawGoldOrder method so that the Order can be updated.
     * @param g2d Graphics2D
     */
    @Override
    public void draw(Graphics2D g2d) {
        this.drawGoldOrder(g2d, true);
    }


    /***
     * This method is used to step up the BuyGoldOrder
     * It checks its destination which is the FirstHorizontalLineY at the top.
     * If it didn't reach its destination now, we check whether any Corporation is around
     * GrabGold  method tries to look every corporation around. If cross the threshold distance
     * then it is grabbed by related Corporation
     */
    @Override
    public void step() {
        double TargetX = target.getX() - position.getX();
        double TargetY = target.getY() - position.getY();
        double totalDistance = Math.sqrt(Math.pow(TargetX, 2) + Math.pow(TargetY, 2));
        if (totalDistance < 8) {
            this.country.buyGoldOrder(this.amount);
            this.completed = true;
        } else {
            this.position.setX(this.position.getX() + (TargetX / (totalDistance / speed)));
            this.position.setY(this.position.getY() + (TargetY / (totalDistance / speed)));
            this.GrabGold();
        }
    }

    /***
     * It Creates a new instance of a BuyGoldOrder by creating amount and speed of the order randomly.
     * How? It creates a random number between 1-5 by using the instance created in Common class
     * Then we are using these numbers while creating our new instance
     * @param country Country
     * @return It returns a new instance of a BuyGoldOrder
     */
    @Override
    Order createOrder(Country country) {
        int amount = Math.abs(Common.getRandomGenerator().nextInt(5) + 1);
        int speed = Math.abs(Common.getRandomGenerator().nextInt(5) + 1);
        int targetPos = Common.getRandomGenerator().nextInt() % Common.getWindowWidth();
        if (targetPos < 0) targetPos *= -1;
        return new BuyGoldOrder(
                country.getPosition().getX() + 25 + Common.getRandomGenerator().nextInt(50),
                country.getPosition().getY() - 15, country,
                amount,
                speed,
                new Position(targetPos, Common.getHorizontalLineY()));
    }
}