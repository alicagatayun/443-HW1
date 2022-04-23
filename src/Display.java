import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Display extends JPanel {
    public Display() {
        this.setBackground(new Color(180, 180, 180));
    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Common.getFoodPrice().draw((Graphics2D) g);
        Common.getElectronicsPrice().draw((Graphics2D) g);
        Common.getGoldPrice().draw((Graphics2D) g);

        g.drawLine(Common.getFirstVerticalLineX(), 0,
                Common.getFirstVerticalLineX(), Common.getHorizontalLineY());
        g.drawLine(Common.getSecondVerticalLineX(), 0,
                Common.getSecondVerticalLineX(), Common.getHorizontalLineY());
        g.drawLine(0, Common.getHorizontalLineY(),
                Common.getWindowWidth(), Common.getHorizontalLineY());

        List<Country> sd = Common.getAllCountry();
        for(int i=0;i<sd.size();i++)
        {
            sd.get(i).draw((Graphics2D) g);
        }

        List<Corporation> csd = Common.getAllCorporation();
        for(int i=0;i<csd.size();i++)
        {
            csd.get(i).draw((Graphics2D) g);
        }
            /*
        while(true){
            File file = new File("./images/boeing.png"); //needs to be an "image" folder in the project folder
            try {
                Image img = ImageIO.read(new File(file.toURI()));
                g.drawImage(img, 4, 5, 150, 150, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }



        }
        */


        //TODO: For Loop to draw countries..

        // TODO: draw other entities
    }
}