import java.util.Random;

public class Shake extends State {
    private Position _prevPosition;
    int prevX,prevY;

    void setPrevVals(){
        if(prevY == -2)  prevY = 2; else prevY = -2;
        if(prevX == -2)  prevX = 2; else prevX = -2;

    }
    @Override
    public Position getNextMove(Position _currentPosition) {
        setPrevVals();
        Random ran = new Random();
        int x = ran.nextInt(2) + prevX;
        int y = ran.nextInt(2) + prevY;
        _prevPosition= new Position(_currentPosition.getIntX()+x,
                _currentPosition.getIntY()+y);

        return checkBoundaries() ? _prevPosition :_currentPosition;
    }//
    private boolean checkBoundaries(){
        if(_prevPosition.getIntX()>=100 && _prevPosition.getIntX()<=1500
    && _prevPosition.getIntY()>=110 && _prevPosition.getIntY()<=500)return true;
        return  false;
    }
    @Override
    public Boolean IsAllowed() {
        return true;
    }
}