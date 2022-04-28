public class ChaseClosest extends State {

    /***
     * This method firstly calculate the distance between itself and CLOSEST order then starts moving to it. In the next step if it detects closer order
     * it changes its direction to the closest one.
     * How? It s actually a basic search implementation. Firstly create an MAX_DISTANCE, if new distance is smaller than the MAX_DISTANCE then replace them until it reaches the end of the list of orders.
     * Then, Like in the other states' It checks whether any order is very close to me so that it can crab.
     * After that, it sets its next move and return.
     * @param corporation Corporation
     */
    @Override
    public void getNextMove(Corporation corporation) {

        double distance = 999999.9;
        Position currentPositionToGo = null;
        for (Order o : Common.getOrders()) {
            if (!o.isCompleted() && o.isGoldBuyOrSellOrder()) {
                double tmp = o.getPosition().distanceTo(corporation.getPosition().getX(),
                        corporation.getPosition().getY());
                if (tmp < distance) {
                    currentPositionToGo = o.getPosition();
                    distance = tmp;
                }
            }
        }
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
        Position position = corporation.getPosition();
        if (currentPositionToGo == null) {
            return;
        }
        int speed = Math.abs(Common.getRandomGenerator().nextInt(2) + 1);
        double newX = position.getX() + (currentPositionToGo.getX() - position.getX()) / 250.0 * speed;
        double newY = position.getY() + (currentPositionToGo.getY() - position.getY()) / 250.0 * speed;
        position.setX(newX);
        position.setY(newY);
        corporation.position = position;
    }

    @Override
    public String getCurrentStateName() {
        return "Chase";
    }
}