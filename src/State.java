public abstract class State {

   public abstract Position getNextMove(Position _currentPosition);
   public abstract Boolean IsAllowed();
}