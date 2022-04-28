public abstract class GoldOrder extends Order {
    public GoldOrder(double x, double y) {
        super(x, y);
    }

    abstract Order createOrder(Country country);
}