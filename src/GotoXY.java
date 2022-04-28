public class GotoXY extends State {


    private final int speed;
    private final double y;
    private final double x;

    public GotoXY() {
        speed = Common.getRandomGenerator().nextInt(4) + 1;
        y = Common.getRandomGenerator().nextInt(Common.getFirstCountryPointY() - 50 - Common.getHorizontalLineY()) + Common.getHorizontalLineY();
        x = Common.getRandomGenerator().nextInt(Common.getWindowWidth() - 80);
    }

    @Override
    public String getCurrentStateName() {
        return "GotoXY";
    }

    /***
     * When this class is created, target destinations are determined firstly. i.e., x,y.
     * In this method, it is just trying to reach its target destination
     * Then, Like in the other states' It checks whether any order is very close to me so that it can crab.
     * After that, it sets its next move and return.
     * @param corporation Corporation
     */
    @Override
    public void getNextMove(Corporation corporation) {

        double newX = corporation.position.getX() + (x - corporation.position.getX()) / 250.00 * speed;
        double newY = corporation.position.getY() + (y - corporation.position.getY()) / 250.00 * speed;
        corporation.position = new Position(newX, newY);
        for (Order o : Common.getOrders()) {
            if (!o.isCompleted()) {
                double dist = o.getPosition().distanceTo(corporation.getPosition().getX() + 5, corporation.getPosition().getY() + 15);
                if (dist <= 30) {
                    if (o.isGoldBuyOrSellOrder()) {
                        corporation.setCorporationCash((Common.getGoldPrice().getCurrentPrice() * o.amount));
                        if (o.isSell()) {
                            o.getCountry().setCountryCash(o.amount * Common.getGoldPrice().getCurrentPrice() * -1);

                        } else {
                            o.getCountry().setCountryCash(o.amount * -1);

                        }
                        o.getCountry().setCountryCitizenHappiness(o.amount * 0.1 * -1);
                        o.completed = true;
                    }
                }
            }
        }
    }
}