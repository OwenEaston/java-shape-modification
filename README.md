# java-shape-modification
This project contains a graphical interface which allows the user to input a multitude of variables to either create, draw, or delete a polygon. As well as storing these polygons in a list and being able to sort said list through the "ID" variable.

"Create" uses the variables in the text fields to create an object named Polygon. This object is stored within PolygonList.
"Draw" uses the ID variable to search through the PolygonList. If a valid Polygon is detected with the ID, that Polygon is then drawn into the Java Graphical Interface and displayed to the user.
"Delete" uses the ID variable to delete the object Polygon from a list if its ID is detected.
"Sort List" prints all the Polygons from PolygonList in the terminal in ascending order based on the ID.

Each of these 4 actions have extensive error checking, using Try/Except, as well as general error checking principles when using if statements. Other concepts used are: ActionListeners, ArrayList, JavaSwing, paintComponent, compareTo.
