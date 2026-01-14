// Reg Num: 2405196
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

public class ContainerPanel extends JPanel {

    ContainerFrame conFrame;

    public ContainerPanel(ContainerFrame cf) {

        conFrame = cf;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D comp = (Graphics2D) g;
        Dimension size = getSize();

        // Retrieve polygon based on drawSelection ID
        for (RegPolygon p : conFrame.polygonList) {
            if (p.getID() == conFrame.drawSelection) {
                p.drawPolygon(comp, size);
                return;
            }
        }
    }
}
