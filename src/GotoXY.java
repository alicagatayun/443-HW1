import java.util.Random;

public class GotoXY extends State {

    private Position _prevPosition;
    private Position _targetPosition;
    private int randomSpeed;
    void setPrevVals(Position _initialPosition){

        Random random = new Random();
        int x =  random.ints(100, 1500)
                .findFirst()
                .getAsInt();
        int y =  random.ints(110, 500)
                .findFirst()
                .getAsInt();
        _targetPosition=new Position(x,y);


        _prevPosition=_initialPosition;
    }
    public boolean IsReachedDestination(){
        if(_targetPosition.getIntX() == _prevPosition.getIntX() &&
        _targetPosition.getIntY() == _prevPosition.getIntY()){
            return true;
        }
        return false;
    }
    public void setRandomSpeed(){
        Random random = new Random();
        randomSpeed = random.nextInt(3)+1;
    }
    @Override
    public Position getNextMove(Position _currentPosition) {
//TODO EĞER DESTINATION'A ÇOK YAKINSA EŞİTLEYİP STATE DEĞİŞTİRİLSİN.
        if(_targetPosition.getIntX() > _prevPosition.getIntX()){
            _prevPosition.setX(_prevPosition.getIntX()+randomSpeed);
        }
        else if(_targetPosition.getIntX() < _prevPosition.getIntX()){
            _prevPosition.setX(_prevPosition.getIntX()-randomSpeed);
        }
        else{

        }

        if(_targetPosition.getIntY() > _prevPosition.getIntY()){
            _prevPosition.setY(_prevPosition.getIntY()+randomSpeed);
        }
        else if(_targetPosition.getIntY()<  _prevPosition.getIntY()){
            _prevPosition.setY(_prevPosition.getIntY()-randomSpeed);
        }


        return _prevPosition;
    }

    @Override
    public Boolean IsAllowed() {
        return null;
    }
    // TODO
}