public class Shake extends State {


    @Override
    public String getCurrentStateName() {
        return "Shake";
    }

    /***
     * In this method, it creates a random number between 0 and 1.
     * If the number is 0 then this method add corporation position  1 in the axis of X and Y
     * Else  this method subtract corporation position  1 in the axis of X and Y
     * Then, Like in the other states' It checks whether any order is very close to me so that it can crab.
     * After that, it sets its next move and return.
     * @param corporation Corporation
     */
    @Override
    public void getNextMove(Corporation corporation) {
        Position position = switch (Common.getRandomGenerator().nextInt() & 0x1) {
            case 0 -> new Position(corporation.position.getIntX() + 1, corporation.position.getIntY() + 1);
            case 1 -> new Position(corporation.position.getIntX() - 1, corporation.position.getIntY() - 1);
            default -> null;
        };
        corporation.position = (position);
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