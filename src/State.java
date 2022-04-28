public abstract class State {
    public abstract String getCurrentStateName();

    public abstract void getNextMove(Corporation corporation);
}