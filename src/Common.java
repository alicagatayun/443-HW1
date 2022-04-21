import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
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

    private static final int firstCountryPointX = 100;
    private static final int firstCountryPointY = 650;


    private static final Random randomGenerator = new Random(1234);

    private static final LivePrice foodPrice = new LivePrice(30, 65, "Food Products", 5, 1, 1, 10);
    private static final LivePrice electronicsPrice = new LivePrice(580, 65, "Consumer Electronics", 30, 2, 10, 50);
    private static final LivePrice goldPrice = new LivePrice(1300, 65, "Gold", 75, 3, 50, 100);

    // getters
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

    public static int getFirstCountryPointX() {
        return firstCountryPointX;
    }

    public static int getFirstCountryPointY() {
        return firstCountryPointY;
    }
    public static List<Country> getAllCountry(){
        return country;
    }
    private static final List<Country> country = setCountry();

    public static Country getCountryInformation(int index) {
        return country.get(index);
    }

    public static List<Country> setCountry() {
        int i = 0;
        List<Country> _countries = new ArrayList<>();
        File file = new File("./images/chile.png");
        File file2 = new File("./images/malaysia.png");
        File file3 = new File("./images/mexico.png");
        File file4 = new File("./images/nigeria.png");
        File file5 = new File("./images/poland.png");
        String[] parts;
        try {
            Image img = ImageIO.read(new File(file.toURI()));
            parts = file.getName().split("\\.");
            Country tempCountry = new Country(getFirstCountryPointX() + i, getFirstCountryPointY(), img
                    , "Chile", 8750, 5000, 50, 50.0);
            i += 300;
            _countries.add(tempCountry);

             img = ImageIO.read(new File(file2.toURI()));
             parts = file2.getName().split("\\.");
             tempCountry = new Country(getFirstCountryPointX() + i, getFirstCountryPointY(), img
                    , "Malaysia", 8750, 5000, 50, 50.0);
            i += 300;
            _countries.add(tempCountry);

             img = ImageIO.read(new File(file3.toURI()));
            parts = file3.getName().split("\\.");
             tempCountry = new Country(getFirstCountryPointX() + i, getFirstCountryPointY(), img
                    , "Mexico", 8750, 5000, 50, 50.0);
            i += 300;
            _countries.add(tempCountry);


             img = ImageIO.read(new File(file4.toURI()));
            parts = file4.getName().split("\\.");
             tempCountry = new Country(getFirstCountryPointX() + i, getFirstCountryPointY(), img
                    , "Nigeria", 8750, 5000, 50, 50.0);
            i += 300;

            _countries.add(tempCountry);

             img = ImageIO.read(new File(file5.toURI()));
            parts = file5.getName().split("\\.");
             tempCountry = new Country(getFirstCountryPointX() + i, getFirstCountryPointY(), img
                    , "Poland", 8750, 5000, 50, 50.0);
            i += 300;

            _countries.add(tempCountry);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return _countries;


    }

    static {
        // TODO: Here you can instantiate entities/fields
    }


    public static void stepAllEntities() {
        if (randomGenerator.nextInt(200) == 0) foodPrice.step();
        if (randomGenerator.nextInt(300) == 0) electronicsPrice.step();
        if (randomGenerator.nextInt(400) == 0) goldPrice.step();

        // TODO: call other entities' step()
    }
}