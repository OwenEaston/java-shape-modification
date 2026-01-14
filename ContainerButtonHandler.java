// Reg Num: 2405196
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

class ContainerButtonHandler implements ActionListener {
    ContainerFrame theApp;

    // Validates the given ID, checking if its:
    // 1. id is empty
    // 2. exactly 6 digits long
    // 3. contains digits from 0-9
    // Due to the conversion from ID to String, this preserves leading zeros so
    // id's like "000001" are valid
    private boolean isValidSixDigitID(String idStr) {
        if (idStr.isEmpty()) {
            System.out.println("No ID entered.");
            return false;
        }
        if (idStr.length() != 6) {
            System.out.println("ID has to be 6 digits long");
            return false;
        }
        for (int i = 0; i < 6; i++) {
            if (!Character.isDigit(idStr.charAt(i))) {
                System.out.println("ID can only contain digits 0-9");
                return false;
            }
        }
        return true;
    }

    // Constructor
    ContainerButtonHandler(ContainerFrame app) {
        theApp = app;
    }

    // Handles all button clicks and triggers events upon activation
    public void actionPerformed(ActionEvent e) {

        // --- DRAW POLYGON BUTTON ---
        if (e.getSource() == theApp.drawPolyButton) {
            String idText = theApp.idField.getText().trim();
            if (!isValidSixDigitID(idText)) return;
            int id = Integer.parseInt(idText);

            // Search through every polygon to find the target
            RegPolygon target = null;
            for (RegPolygon p : theApp.polygonList) {
                if (p.getID() == id) {
                    target = p;
                    break;
                }
            }

            // If target is valid, it is then selected and drawn. Error is returned otherwise.
            if (target != null) {
                theApp.drawSelection = id;
                System.out.println("Selected polygon " + idText + " for drawing.");
            } else {
                System.out.println("No polygon with ID " + idText + " found.");
            }
        }

        // --- DELETE POLYGON BUTTON ---
        if (e.getSource() == theApp.deletePolyButton) {
            String idText = theApp.idField.getText().trim();
            if (!isValidSixDigitID(idText)) return;
            int id = Integer.parseInt(idText);
            theApp.drawSelection = id;

            // Searches for polygon to delete
            RegPolygon polygonToDelete = null;
            for (RegPolygon p : theApp.polygonList) {
                if (p.getID() == id) {
                    polygonToDelete = p;
                    break;
                }
            }

            // If the target polygon is found, remove from list, otherwise throw error and ignore.
            if (polygonToDelete != null) {
                theApp.polygonList.remove(polygonToDelete);
                System.out.println("Deleted polygon " + idText);
            } else {
                System.out.println("No polygon with ID " + idText + " found.");
            }
        }

        // --- SORT POLYGON BUTTON ---
        if (e.getSource() == theApp.sortPolyButton) {
            // Checks if list is empty first
            if (theApp.polygonList.isEmpty()) {
                System.out.println("List is empty");
            } else {
                // If not empty, sort using RegPolygon's compareTo method
                Collections.sort(theApp.polygonList);
                System.out.println("Sorted ID List:");
                // Prints out every polygon in the now sorted list. (ID ascending)
                for (RegPolygon p : theApp.polygonList) {
                    System.out.println(p.toString());
                }
            }
        }

        // --- CREATE POLYGON BUTTON ---
        if (e.getSource() == theApp.createPolyButton) {
            String idText = theApp.idField.getText().trim();
            if (!isValidSixDigitID(idText)) return;

            int id;

            // Attempts to parse polygon parameters from the text fields
            int sides, angle, radius;
            try {
                sides  = Integer.parseInt(theApp.sidesField.getText());
                angle  = Integer.parseInt(theApp.angleField.getText());
                radius = Integer.parseInt(theApp.radiusField.getText());
                // if NumberFormatException error occurs, return an error message and end the process
            } catch (NumberFormatException ex) {
                System.out.println("Polygon text field(s) is invalid.");
                return;
            }

            // verifies that polygon has 3 or more sides
            if (sides < 3) {
                System.out.println("Polygon must have at least 3 sides.");
                return;
            }

            id = Integer.parseInt(idText);
            theApp.drawSelection = id;

            // Checks for duplicates
            for (RegPolygon p : theApp.polygonList) {
                // compares the ID given in the text box with the ID of every RegPolygon in the list
                if (p.getID() == id) {
                    System.out.println("Polygon ID \"" + idText + "\" already exists.");
                    return;
                }
            }

            // Determines polygon colour
            String selected = (String) theApp.colourBox.getSelectedItem();
            Color chosenColour = Color.BLACK;
            if ("RED".equals(selected)) chosenColour = Color.RED;
            if ("BLUE".equals(selected)) chosenColour = Color.BLUE;
            if ("GREEN".equals(selected)) chosenColour = Color.GREEN;

            // Create and add polygon to the list
            RegPolygon newPoly = new RegPolygon(id, chosenColour, sides, angle, radius);
            theApp.polygonList.add(newPoly);
            System.out.println("Polygon ID \"" + idText + "\" added to the list.");
        }

        theApp.repaint();
    }
}
