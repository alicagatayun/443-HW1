import java.awt.*;

public class BuyGoldOrder extends GoldOrder {
    public BuyGoldOrder(double x, double y, Country country, int amount, int speed, Position target) {
        super(x, y);
        this.country =country;
        this.amount = amount;
        this.speed = speed;
        this.target = target;
    }

    @Override
    public void draw(Graphics2D g2d) {
        this.createOrderStub(g2d, true);
    }

    @Override
    public void step() {
        double[] distance = getDistances(this.target, speed);
        if (distance[2] < 5) {
            this.country.buyGoldOrder(this.amount);
            this.completed = true;

        } else {
            this.position.setX(this.position.getX() + distance[0]);
            this.position.setY(this.position.getY() + distance[1]);
        }

    }

    @Override
    Order createNewOrder(Country country) {
        double[] orderData = getOrderData(country);
        return new BuyGoldOrder(orderData[2], orderData[3], country, (int) orderData[0], (int) orderData[1], new Position(orderData[4], orderData[5]));

    }
    // TODO
    // RGB --> (0, 200, 0)
}