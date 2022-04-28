import java.awt.*;

public class ElectronicsOrder extends Order {
    public ElectronicsOrder(double x, double y, Country country, int amount, int speed, Position target) {
        super(x, y);
        this.country = country;
        this.amount = amount;
        this.speed = speed;
        this.target = target;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setFont(font);

        g2d.setPaint(new Color(0, 182, 204));

        g2d.fillOval(position.getIntX() + 2, position.getIntY() + 2, 16, 16);
        g2d.drawString(country.getCountryFL(), position.getIntX(), position.getIntY());
        g2d.setColor(Color.BLACK);
        g2d.drawString(Integer.toString(this.amount), position.getIntX() + 6, position.getIntY() + 16);
    }

    @Override
    public void step() {
        double[] distance = getDistances(this.target, speed);
        if (distance[2] < 5) {
            this.country.buyElectronicDevice(this.amount);
            this.completed = true;

        } else {
            this.position.setX(this.position.getX() + distance[0]);
            this.position.setY(this.position.getY() + distance[1]);
        }
    }

    Order createNewOrder(Country country) {
        double[] orderData = new double[6];
        int amount = Math.abs(Common.getRandomGenerator().nextInt() % 5) + 1;
        int speed = Math.abs(Common.getRandomGenerator().nextInt() % 5);
        if (speed < 1) speed = 1;
        Position position = new Position(country.getPosition().getX() + 25 + Common.getRandomGenerator().nextInt(50), country.getPosition().getY() - 15);
        int targetX = Common.getRandomGenerator().nextInt() % Common.getWindowWidth();
        if (targetX < 0) {
            targetX = -targetX;
        }
        Position target = new Position(targetX, Common.getHorizontalLineY());
        orderData[0] = amount;
        orderData[1] = speed;
        orderData[2] = position.getX();
        orderData[3] = position.getY();
        orderData[4] = target.getX();
        orderData[5] = target.getY();
        return new ElectronicsOrder(orderData[2], orderData[3], country, (int) orderData[0], (int) orderData[1], new Position(orderData[4], orderData[5]));

    }
    // TODO
    // RGB --> (0, 182, 204)
}