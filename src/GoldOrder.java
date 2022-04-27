public abstract class GoldOrder extends Order {
    public GoldOrder(double x, double y) {
        super(x, y);
    }
    // TODO
    protected double[] getOrderData(Country country) {

        double[] r = new double[6];
        int amount = Math.abs(Common.getRandomGenerator().nextInt() % 5) + 1;
        int speed = Math.abs(Common.getRandomGenerator().nextInt() % 5);
        if (speed < 1) speed = 1;
        Position position = new Position(country.getPosition().getX() + 25 + Common.getRandomGenerator().nextInt(50), country.getPosition().getY() - 15);
        int targetX = Common.getRandomGenerator().nextInt() % Common.getWindowWidth();
        if (targetX < 0) {
            targetX = -targetX;
        }
        Position target = new Position(targetX, Common.getHorizontalLineY());
        r[0] = amount;
        r[1] = speed;
        r[2] = position.getX();
        r[3] = position.getY();
        r[4] = target.getX();
        r[5] = target.getY();
        return r;
    }
    abstract Order createNewOrder(Country country);
}