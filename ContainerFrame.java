// Reg Num: 2405196
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ContainerFrame extends JFrame{
    ArrayList<RegPolygon> polygonList = new ArrayList<>();
    JButton drawPolyButton;
    JButton createPolyButton;
    JButton deletePolyButton;
    JButton sortPolyButton;

    // ID of polygon currently selected for drawing
    int drawSelection = 0;

    public JComboBox<String> colourBox;
    public JTextField sidesField;
    public JTextField angleField;
    public JTextField radiusField;
    public JTextField idField;

    public void createComponents() {
        // top panels
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));   // <-- CENTER FIX
        sidesField = new JTextField(5);
        angleField = new JTextField(5);
        radiusField = new JTextField(5);
        idField = new JTextField(6);
        String[] colours = {"RED", "BLUE", "GREEN", "BLACK"};
        colourBox = new JComboBox<>(colours);

        topPanel.add(new JLabel("Sides:"));
        topPanel.add(sidesField);

        topPanel.add(new JLabel("Angle:"));
        topPanel.add(angleField);

        topPanel.add(new JLabel("Radius:"));
        topPanel.add(radiusField);

        topPanel.add(new JLabel("Colour:"));
        topPanel.add(colourBox);

        topPanel.add(new JLabel("ID:"));
        topPanel.add(idField);

        add(topPanel, BorderLayout.NORTH);

        // Bottom buttons
        ContainerButtonHandler cbHandler = new ContainerButtonHandler(this);

        // Buttons
        drawPolyButton = new JButton("Draw Polygon");
        createPolyButton = new JButton("Create Polygon");
        deletePolyButton = new JButton("Delete Polygon");
        sortPolyButton = new JButton("Sort List");

        drawPolyButton.addActionListener(cbHandler);
        createPolyButton.addActionListener(cbHandler);
        deletePolyButton.addActionListener(cbHandler);
        sortPolyButton.addActionListener(cbHandler);

        // Centre drawing panel
        JPanel drawPanel = new ContainerPanel(this);
        add(drawPanel, BorderLayout.CENTER);

        // Bottom button panel
        JPanel buttPanel = new JPanel();
        buttPanel.add(drawPolyButton);
        buttPanel.add(createPolyButton);
        buttPanel.add(deletePolyButton);
        buttPanel.add(sortPolyButton);
        add(buttPanel, BorderLayout.SOUTH);

        setSize(750, 750);
        setVisible(true);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	// Close action.
    }

    public static void main(String[] args) {
        ContainerFrame cFrame = new ContainerFrame();
        cFrame.createComponents();
    }
}
