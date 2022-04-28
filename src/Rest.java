public class Rest extends State {


    /***
     * In this method, Like in the other states' It checks whether any order is very close to me so that it can crab.
     * After that, it sets its next move and return.
     * @param corporation Corporation
     */
    @Override
    public void getNextMove(Corporation corporation) {
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

    @Override
    public String getCurrentStateName() {
        return "Rest";
    }
}