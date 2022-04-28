import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Common {
    private static final String title = "Arms Race";
    private static final int windowWidth = 1650;
    private static final int windowHeight = 1000;

    private static final int firstVerticalLineX = 500;
    private static final int secondVerticalLineX = 1250;
    private static final int horizontalLineY = 100;

    private static final int firstCountryPointX = 175;
    private static final int firstCountryPointY = 650;

    private static final int initialCorporationPointX = 125;
    private static final int initialCorporationPointY = 300;

    private static final Random randomGenerator = new Random(1234);

    private static final LivePrice foodPrice = new LivePrice(30, 65, "Food Products", 5, 1, 1, 10);
    private static final LivePrice electronicsPrice = new LivePrice(580, 65, "Consumer Electronics", 30, 2, 10, 50);
    private static final LivePrice goldPrice = new LivePrice(1300, 65, "Gold", 75, 3, 50, 100);
    private static final ArrayList<Order> orders;
    private static final List<Country> country = setCountry();
    public static List<State> _states = setStates();
    private static final List<Corporation> corporation = setCorporation();

    static {
        setCountry();
        orders = new ArrayList<>();
    }

    // Getters
    public static String getTitle() {
        return title;
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    public static int getFirstVerticalLineX() {
        return firstVerticalLineX;
    }

    public static int getSecondVerticalLineX() {
        return secondVerticalLineX;
    }

    public static int getHorizontalLineY() {
        return horizontalLineY;
    }

    public static Random getRandomGenerator() {
        return randomGenerator;
    }

    public static LivePrice getFoodPrice() {
        return foodPrice;
    }

    public static LivePrice getElectronicsPrice() {
        return electronicsPrice;
    }

    public static LivePrice getGoldPrice() {
        return goldPrice;
    }

    public static int getInitialCorporationPointX() {
        return initialCorporationPointX;
    }

    public static int getInitialCorporationPointY() {
        return initialCorporationPointY;
    }

    public static List<Corporation> getAllCorporation() {
        return corporation;
    }

    public static ArrayList<Order> getOrders() {
        return orders;
    }

    public static List<State> setStates() {
        List<State> _states = new ArrayList<>();
        _states.add(new Shake());
        _states.add(new Rest());
        _states.add(new ChaseClosest());
        _states.add(new GotoXY());
        return _states;
    }

    public static int getFirstCountryPointX() {
        return firstCountryPointX;
    }

    public static int getFirstCountryPointY() {
        return firstCountryPointY;
    }

    public static List<Country> getAllCountry() {
        return country;
    }

    public static List<Country> setCountry() {
        int i = 0;
        List<Country> _countries = new ArrayList<>();
        File file = new File("./images/chile.png");
        File file2 = new File("./images/malaysia.png");
        File file3 = new File("./images/mexico.png");
        File file4 = new File("./images/nigeria.png");
        File file5 = new File("./images/poland.png");
        try {
            Image img = ImageIO.read(new File(file.toURI()));
            Country tempCountry = new Country(getFirstCountryPointX() + i, getFirstCountryPointY(), img
                    , "Chile", 8750, 5000, 50, 50.0, "CL");
            i += 260;
            _countries.add(tempCountry);

            img = ImageIO.read(new File(file2.toURI()));
            tempCountry = new Country(getFirstCountryPointX() + i, getFirstCountryPointY(), img
                    , "Malaysia", 8750, 5000, 50, 50.0, "ML");
            i += 260;
            _countries.add(tempCountry);

            img = ImageIO.read(new File(file3.toURI()));
            tempCountry = new Country(getFirstCountryPointX() + i, getFirstCountryPointY(), img
                    , "Mexico", 8750, 5000, 50, 50.0, "MX");
            i += 260;
            _countries.add(tempCountry);


            img = ImageIO.read(new File(file4.toURI()));
            tempCountry = new Country(getFirstCountryPointX() + i, getFirstCountryPointY(), img
                    , "Nigeria", 8750, 5000, 50, 50.0, "NG");
            i += 260;
            _countries.add(tempCountry);


            img = ImageIO.read(new File(file5.toURI()));
            tempCountry = new Country(getFirstCountryPointX() + i, getFirstCountryPointY(), img
                    , "Poland", 8750, 5000, 50, 50.0, "PO");
            _countries.add(tempCountry);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return _countries;


    }

    public static List<Corporation> setCorporation() {
        int i = 0;
        List<Corporation> _corporations = new ArrayList<>();
        File file = new File("./images/lockheed_martin.png");
        File file2 = new File("./images/raytheon.png");
        File file3 = new File("./images/boeing.png");
        File file4 = new File("./images/northrop_grumman.png");
        File file5 = new File("./images/general_dynamics.png");
        try {
            Image img = ImageIO.read(new File(file.toURI()));
            List<Order> tmp = new ArrayList<>();
            Corporation tempCorporation = new Corporation(getInitialCorporationPointX() + i, getInitialCorporationPointY(), img
                    , "LMT", 0, ChangeState());
            i += 300;
            _corporations.add(tempCorporation);

            img = ImageIO.read(new File(file2.toURI()));
            tmp = new ArrayList<>();
            tempCorporation = new Corporation(getInitialCorporationPointX() + i, getInitialCorporationPointY(), img
                    , "RTX", 0, ChangeState());
            i += 300;
            _corporations.add(tempCorporation);

            img = ImageIO.read(new File(file3.toURI()));
            tmp = new ArrayList<>();
            tempCorporation = new Corporation(getInitialCorporationPointX() + i, getInitialCorporationPointY(), img
                    , "BA", 0, ChangeState());
            i += 300;
            _corporations.add(tempCorporation);


            img = ImageIO.read(new File(file4.toURI()));
            tmp = new ArrayList<>();
            tempCorporation = new Corporation(getInitialCorporationPointX() + i, getInitialCorporationPointY(), img
                    , "NOC", 0, ChangeState());
            i += 300;


            _corporations.add(tempCorporation);

            img = ImageIO.read(new File(file5.toURI()));
            tmp = new ArrayList<>();
            tempCorporation = new Corporation(getInitialCorporationPointX() + i, getInitialCorporationPointY(), img
                    , "GD", 0, ChangeState());

            _corporations.add(tempCorporation);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return _corporations;


    }

    public static State ChangeState() {
        State newState = _states.get((int) (Math.random() * _states.size()));
        return switch (newState.getCurrentStateName()) {
            case "Chase" -> new ChaseClosest();
            case "Shake" -> new Shake();
            case "Rest" -> new Rest();
            default -> new GotoXY();
        };

    }

    /***
     * It identifies the orders that complete its lifetime, then delete.
     */
    private static void deleteCompletedOrSelled() {
        List<Order> deleteList = new ArrayList<>();
        for (Order o : orders) {
            if (o.isCompleted()) {
                deleteList.add(o);
            }
        }
        for (Order o : deleteList) {
            orders.remove(o);
        }
    }

    /***
     * This method is used to step all entities in the GUI.
     * For every Entity, I have created loop.
     * For example, In order to step an order, first I create a loop, and start stepping orders one by one.
     * After stepping corporations and orders, we are identifying the orders that should be deleted due to the selling or reaching the destination.
     * After identifying orders, deleteCompletedOrSelled method is executed to perform delete operation.
     *
     */
    public static void stepAllEntities() {
        if (randomGenerator.nextInt(200) == 0) foodPrice.step();
        if (randomGenerator.nextInt(300) == 0) electronicsPrice.step();
        if (randomGenerator.nextInt(400) == 0) goldPrice.step();
        for (Order order : orders) {
            if (!order.completed)
                order.step();
        }
        for (Corporation value : corporation) {
            value.step();
        }
        deleteCompletedOrSelled();
        for (int i = 0; i < country.size(); i++) {
            if (randomGenerator.nextInt(i + 400) == 0) {
                country.get(i).step();
            }
        }
    }


}