// Reg Num: 2405196
import java.awt.*;

public class RegPolygon implements Comparable<RegPolygon>{

    Color pColor = Color.BLACK;
    int pId = 000000;
    int pSides;
    double pStarting_angle;
    double pRadius;

    int polyCenX;
    int polyCenY;
    double [] pointsX;
    double [] pointsY;

    public RegPolygon(int id, Color col, int p_sides, double st_angle, double rad) {

        this.pId = id;
        this.pColor = col;
        this.pSides = p_sides;              // user defined number of sides should be non-negative
        this.pStarting_angle = st_angle;   // user defined starting angle
        this.pRadius = rad;                // user defined radius
        pointsX = new double[pSides];
        pointsY = new double[pSides];
        this.polyCenX = 250;
        this.polyCenY = 250;
    }

    private Polygon getPolygonPoints(Dimension dim) {
        polyCenX = dim.width / 2;
        polyCenY = dim.height / 2;
        double angleIncrement = 2 * Math.PI / pSides;
        Polygon p = new Polygon();

        double angle = Math.toRadians(pStarting_angle);

        for(int i = 0; i < pSides; i++)
        {
            double x = polyCenX + pRadius * Math.cos(angle);
            double y = polyCenY + pRadius * Math.sin(angle);

            pointsX[i] = x;
            pointsY[i] = y;

            p.addPoint((int)x, (int)y);

            angle = angle + angleIncrement;
        }
        return p;
    }

    public void drawPolygon(Graphics2D g, Dimension d) {
        g.setColor(pColor);
        g.drawPolygon(getPolygonPoints(d));
    }


    public int getID() {
        return pId;
    }


    @Override
    // method used for comparing PolygonContainer objects based on stored ids
    public int compareTo(RegPolygon o) {
        return Integer.compare(this.pId, o.pId);
    }


    // outputs a string representation of the PolygonContainer object
    @Override
    public String toString() {
        return "ID: " + pId + ", Sides: " + pSides + ", Angle: " + pStarting_angle + ", Radius: " + pRadius + ", Colour: " + pColor;
    }

}
