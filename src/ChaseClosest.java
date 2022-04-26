public class ChaseClosest extends State {

    @Override
    public Position getNextMove(Position _currentPosition) {
        return _currentPosition;
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