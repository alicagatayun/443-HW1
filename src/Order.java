import java.awt.*;

public abstract class Order extends Entity {
    public Order(double x, double y) {
        super(x, y);
    }
    // TODO
    protected Country country;
    protected int amount;
    protected Font font = new Font("Verdana", Font.BOLD, 14);
    protected int speed;
    protected Position target;
    protected boolean completed = false;
    protected boolean sell = false;
    protected boolean gold_order = false;
    public Country getCountry() {
        return country;
    }

    public boolean isCompleted() {
        return completed;
    }
    public boolean isSell() {  return sell; }
    public void setSell(boolean isSell) {   sell=isSell; }
    public boolean isGoldBuyOrSellOrder() {  return gold_order; }
    public void setGoldBuyOrSellOrder(boolean isGoldOrder) {   gold_order=isGoldOrder; }
    protected double[] getDistances(Position target, int speed) {
        // Returns an array of doubles to move orders to the target.
        // r[0] = speed on x axis
        // r[1] = speed on y axis
        // r[2] = Euclidean distance
        double[] r = new double[3];
        double differenceX = target.getX() - position.getX();
        double differenceY = target.getY() - position.getY();
        double totalDistance = Math.sqrt(differenceX * differenceX + differenceY * differenceY);
        double eta = totalDistance / speed;
        r[0] = differenceX / eta;
        r[1] = differenceY / eta;
        r[2] = totalDistance;
        return r;
    }

    protected void createOrderStub(Graphics2D g2d, boolean isBuy) {
        // Creates an order drawing according to the order type.
        // The isBuy parameter was added to avoid code duplication.
        g2d.setFont(font);
        if (isBuy) {
            g2d.setPaint(Color.GREEN);
        } else {
            g2d.setPaint(Color.RED);
        }
        g2d.fillOval(position.getIntX() + 2, position.getIntY() + 2, 16, 16);
        g2d.drawString(country.getCountryFL(), position.getIntX(), position.getIntY());
        g2d.setColor(Color.BLACK);
        g2d.drawString(Integer.toString(this.amount), position.getIntX() + 6, position.getIntY() + 16);
    }


    // Order is 24 x 24
}