import java.util.Random;

public class GotoXY extends State {


    private int randomSpeed;
    private double y;
    private double x;
    private double eta;

    public GotoXY() {
        Random random = new Random();
        randomSpeed = random.nextInt(3)+1;
        y = Common.getRandomGenerator().nextInt(Common.getFirstCountryPointY() - Common.getHorizontalLineY() - 200) + Common.getHorizontalLineY();
        x = Common.getRandomGenerator().nextInt(Common.getWindowWidth() - 100);
        eta = (Common.getRandomGenerator().nextInt(256) + 1561963) & 0b11111111;
        if (eta == 0) eta = 8291329;
    }

    @Override
    public String getCurrentStateName() {
        return "GotoXY";
    }



    @Override
    public void getNextMove(Corporation corporation) {
        double newX = corporation.position.getX() + (x - corporation.position.getX()) / eta;
        double newY = corporation.position.getY() + (y - corporation.position.getY()) / eta;
        corporation.position = new Position(newX, newY);

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
    }

    @Override
    public Boolean IsAllowed() {
        return null;
    }
    // TODO
}