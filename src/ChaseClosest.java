public class ChaseClosest extends State {

    @Override
    public Position getNextMove(Position _currentPosition) {
        return new Position(3,4);
    }

    @Override
    public Boolean IsAllowed() {
        return null;
    }
}