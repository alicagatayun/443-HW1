public class Rest extends State {
    @Override
    public Position getNextMove(Position _currentPosition) {
        return _currentPosition;
    }// TODO
    int i=0;
    @Override
    public Boolean IsAllowed() {
        if(i == 10){i=0;return true;}
        i++;
        return false;
    }
}