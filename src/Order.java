import java.awt.*;

public abstract class Order extends Entity {
    //Fields
    protected Country country;
    protected int amount;
    protected Font font = new Font("Verdana", Font.BOLD, 16);
    protected int speed;
    protected Position target;
    protected boolean completed = false;
    protected boolean sell = false;
    protected boolean gold_order = false;
    public Order(double x, double y) {
        super(x, y);
    }

    //Getters
    public Country getCountry() {
        return country;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isSell() {
        return sell;
    }

    //Setters
    public void setSell(boolean isSell) {
        sell = isSell;
    }

    public boolean isGoldBuyOrSellOrder() {
        return gold_order;
    }

    public void setGoldBuyOrSellOrder(boolean isGoldOrder) {
        gold_order = isGoldOrder;
    }

    //Order Shaping

    /***
     * This method get the Created order to draw its circular shape. It puts the order amount inside the circle with
     * the related color of the Order.
     * If it is the BuyGoldOrder, color will be Green
     * Else Color will be Red.
     * We check its type with
     * @param g2d Graphics2D
     * @param isBuyOrSell Refers to its type. BuyGoldOrder or SellGoldOrder
     */
    protected void drawGoldOrder(Graphics2D g2d, boolean isBuyOrSell) {
        // Creates an order drawing according to the order type.
        // The isBuy parameter was added to avoid code duplication.
        g2d.setFont(font);
        if (isBuyOrSell) {
            g2d.setPaint(Color.GREEN);
        } else {
            g2d.setPaint(Color.RED);
        }
        g2d.fillOval(position.getIntX() + 4, position.getIntY() + 4, 24, 24);
        g2d.drawString(country.getCountrySf(), position.getIntX(), position.getIntY());
        //Center the Text in Circle
        g2d.setColor(Color.BLACK);
        g2d.drawString(Integer.toString(this.amount), position.getIntX() + 10, position.getIntY() + 22);
    }

    /***
     * This method calculate the distance between orders and Corporations so that if any order is very close to the
     * any corporation, it is grabbed by Corporation.
     * With that, the values of the Origin country of the Order will also be updated here.
     * Its setter methods are called.
     * Also, Corporation cache is also updated.
     */
    protected void GrabGold() {
        for (Corporation o : Common.getAllCorporation()) {
            double dist = this.getPosition().distanceTo(o.getPosition().getX() + 10, o.getPosition().getY() + 15);
            if (dist <= 30
            ) {
                o.setCorporationCash((Common.getGoldPrice().getCurrentPrice() * this.amount));
                if (this.isSell()) {
                    this.getCountry().setCountryCash(this.amount * Common.getGoldPrice().getCurrentPrice() * -1);

                } else {
                    this.getCountry().setCountryCash(this.amount * -1);

                }
                this.getCountry().setCountryCitizenHappiness(this.amount * 0.1 * -1);
                this.completed = true;
            }
        }
    }
}