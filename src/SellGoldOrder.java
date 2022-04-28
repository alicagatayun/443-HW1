import java.awt.*;

public class SellGoldOrder extends GoldOrder {
    public SellGoldOrder(double x, double y, Country country, int amount, int speed, Position target) {
        super(x, y);
        this.country = country;
        this.amount = amount;
        this.speed = speed;
        this.target = target;
        this.sell = true;
    }

    @Override
    Order createNewOrder(Country country) {
        double[] orderData = getOrderData(country);
        return new SellGoldOrder(orderData[2], orderData[3], country, (int) orderData[0], (int) orderData[1], new Position(orderData[4], orderData[5]));
    }

    @Override
    public void draw(Graphics2D g2d) {
        this.createOrderStub(g2d, false);
    }

    @Override
    public void step() {
        double[] distance = getDistances(this.target, speed);
        if (distance[2] < 5) {
            this.country.sellGoldOrder(this.amount);
            this.completed = true;
        } else {
            this.position.setX(this.position.getX() + distance[0]);
            this.position.setY(this.position.getY() + distance[1]);


        }
    }
    // TODO
    // RGB --> (180, 0, 0)
}