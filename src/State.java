import java.util.Random;

public abstract class State {


    public abstract Boolean IsAllowed();


    /*public State chooseRandomState() {
        return pickAnotherState();
    }*/

   /* public enum States {
        GotoXY,
        Chase,
        Rest,
        Shake
    }

    private State pickAnotherState() {

        States currentState = States.values()[(int) (Math.random() * States.values().length)];
        switch (currentState) {

            case Chase -> {
                System.out.println(currentState);

                return new ChaseClosest();
            }
            case Shake -> {
                System.out.println(currentState);

                return new Shake();
            }
            case GotoXY -> {
                System.out.println(currentState);

                return new GotoXY();
            }
            default -> {
                System.out.println(currentState);

                return new Rest();
            }

        }

    }*/

    public abstract String getCurrentStateName();

    public abstract Position getNextMove(Position _currentPosition);
}