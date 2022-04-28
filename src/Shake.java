import java.util.Random;

public class Shake extends State {
    private Position _prevPosition;
    int prevX,prevY;

    void setPrevVals(){
        if(prevY == -2)  prevY = 2; else prevY = -2;
        if(prevX == -2)  prevX = 2; else prevX = -2;

    }

    @Override
    public String getCurrentStateName() {
        return "Shake";
    }
    int randomNum(int left){
        Random random = new Random();
        return random.nextInt(2 -left) + left;
    }

    @Override
    public void getNextMove(Corporation corporation) {
        int Xoffset = randomNum(1), Yoffset = randomNum(1);

        Position position = null;
        switch (randomNum(0)) {
            case 0:
                position = new Position(corporation.position.getIntX() + Xoffset, corporation.position.getIntY() + Yoffset);
                break;
            case 1:
                position = new Position(corporation.position.getIntX() - Xoffset, corporation.position.getIntY() - Yoffset);
                break;
        }
        corporation.position=(position);

        //Check Any Order Around
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
        return true;
    }
}