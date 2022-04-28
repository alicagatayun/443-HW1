public class Rest extends State {
    @Override
    public void getNextMove(Corporation corporation) {
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

    }// TODO
    int i=0;
    @Override
    public Boolean IsAllowed() {
        if(i == 10){i=0;return true;}
        i++;
        return false;
    }

    @Override
    public String getCurrentStateName() {
        return "Rest";
    }
}