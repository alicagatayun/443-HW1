import java.util.Random;

public class ChaseClosest extends State {

    private Order order;
    private double speed = -1.0;

    private Position findPositionOfClosestOrder(Corporation corporation) {
        double closestDist = 10000000.0;
        Position bestPosition = null;
        for(int i = 0; i < Common.getOrders().size(); ++i) {
            if(!Common.getOrders().get(i).isCompleted() && Common.getOrders().get(i).isGoldBuyOrSellOrder())
            {
                double dist = Common.getOrders().get(i).getPosition().distanceTo(corporation.getPosition().getX(), corporation.getPosition().getY());
                if(dist < closestDist) {
                    bestPosition = Common.getOrders().get(i).getPosition();
                    closestDist = dist;
                    order = Common.getOrders().get(i);
                }
            }

        }
        return bestPosition;
    }
    private int  randomNum(int left, int right){
        Random random = new Random();
        return random.nextInt(right-left) + left;
    }
    @Override
    public void getNextMove(Corporation corporation) {
        Position currentPositionToGo = findPositionOfClosestOrder(corporation);
        if(speed == -1) {
            speed = randomNum(1, 2);
        }

        for(Order o : Common.getOrders()){
            if(!o.isCompleted()){
                //Eğer bitmemiş ve satılmamışsa sat hemen satt koy  koyy sat gitsin
                double dist = o.getPosition().distanceTo(corporation.getPosition().getX(),corporation.getPosition().getY());
                if(dist <= 20){
                    if(o.isGoldBuyOrSellOrder())//GOLD BUY{
                    {
                        corporation.setCorporationCash((Common.getGoldPrice().getCurrentPrice()*o.amount));
                        if(o.isSell()){//SATIŞ
                            o.getCountry().setCountryCash(o.amount*Common.getGoldPrice().getCurrentPrice()*-1);

                        }
                        else{//ALIŞ
                            o.getCountry().setCountryCash(o.amount*-1);

                        }
                        o.getCountry().setCountryCitizenHappiness(o.amount*0.1*-1);
                        o.completed=true;
                    }
                    else
                    {

                    }


                }

            }
        }
        Position position = corporation.getPosition();
        if(currentPositionToGo==null){
            return;
        }
        double newX = position.getX() + (currentPositionToGo.getX()-position.getX()) / 100.0 * speed;
        double newY = position.getY() + (currentPositionToGo.getY()-position.getY()) / 100.0 * speed;
        position.setX(newX);
        position.setY(newY);
        corporation.position = position;
    }

    @Override
    public Boolean IsAllowed() {
        return null;
    }

    @Override
    public String getCurrentStateName() {
        return "Chase";
    }
}