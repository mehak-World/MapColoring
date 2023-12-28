import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class IndianMap extends JPanel {

    static final Map<String, Color> stateColors = new HashMap<>();

    // Define the state centers and radius
     final Map<String, Point> stateCenters = new HashMap<>();
     final Map<String, Integer> stateRadius = new HashMap<>();
     final Map<String, String> stateAbbreviations = new HashMap<>();
    final Map<String, List<String>> stateConnections = new HashMap<>();

    public IndianMap() {
   
    	stateCenters.put("Jammu and Kashmir", new Point(50, 100));
        stateCenters.put("Ladakh", new Point(100, 50));       
        stateCenters.put("Himachal Pradesh", new Point(150, 100));
        stateCenters.put("Punjab", new Point(100, 150));
        stateCenters.put("Haryana", new Point(150, 150));
        stateCenters.put("Rajasthan", new Point(120, 175));
        stateCenters.put("Uttarakhand", new Point(180, 120));
        stateCenters.put("Nepal", new Point(200, 100));
        stateCenters.put("Bihar", new Point(220, 150));
        stateCenters.put("Bhutan", new Point(250, 100));

        stateAbbreviations.put("Jammu and Kashmir", "JM");
        stateAbbreviations.put("Ladakh", "Ladakh");
        stateAbbreviations.put("Himachal Pradesh", "HP");
        stateAbbreviations.put("Punjab", "PB");
        stateAbbreviations.put("Haryana", "HR");
        stateAbbreviations.put("Rajasthan", "RJ");
        stateAbbreviations.put("Uttarakhand", "UT");
        stateAbbreviations.put("Nepal", "Nepal");
        stateAbbreviations.put("Bihar", "Bihar");
        stateAbbreviations.put("Bhutan", "Bhutan");
        
        stateRadius.put("Ladakh", 10);
        stateRadius.put("Jammu and Kashmir", 10);
        stateRadius.put("Himachal Pradesh", 10);
        stateRadius.put("Punjab", 10);
        stateRadius.put("Haryana", 10);
        stateRadius.put("Rajasthan", 10);
        stateRadius.put("Uttarakhand", 10);
        stateRadius.put("Nepal", 10);
        stateRadius.put("Bihar", 10);
        stateRadius.put("Bhutan", 10);

        // Defining state connections
        stateConnections.put("Ladakh", Arrays.asList("Jammu and Kashmir", "Himachal Pradesh"));
        stateConnections.put("Jammu and Kashmir", Arrays.asList("Himachal Pradesh", "Punjab"));
        stateConnections.put("Himachal Pradesh", Arrays.asList("Ladakh", "Punjab", "Haryana", "Uttarakhand"));
        stateConnections.put("Punjab", Arrays.asList("Jammu and Kashmir", "Himachal Pradesh", "Haryana"));
        stateConnections.put("Haryana", Arrays.asList("Punjab", "Himachal Pradesh", "Uttarakhand"));
        stateConnections.put("Rajasthan", Arrays.asList("Haryana", "Uttarakhand", "Punjab"));
        stateConnections.put("Uttarakhand", Arrays.asList("Himachal Pradesh", "Haryana", "Nepal", "Bihar"));
        stateConnections.put("Nepal", Arrays.asList("Uttarakhand", "Bihar", "Bhutan"));
        stateConnections.put("Bihar", Arrays.asList("Uttarakhand", "Nepal"));
        stateConnections.put("Bhutan", Arrays.asList("Nepal"));
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw edges between connected states
        for (Map.Entry<String, List<String>> entry : stateConnections.entrySet()) {
            String state1 = entry.getKey();
            List<String> connectedStates = entry.getValue();
            Point center1 = stateCenters.get(state1);

            for (String state2 : connectedStates) {
                Point center2 = stateCenters.get(state2);
                g.setColor(Color.BLACK);
                g.drawLine(center1.x, center1.y, center2.x, center2.y);
            }
        }

        // Draw states as filled circles
        for (Map.Entry<String, Color> entry : stateColors.entrySet()) {
            String state = entry.getKey();
            Color color = entry.getValue();
            Point center = stateCenters.get(state);
            int radius = stateRadius.get(state);

            g.setColor(color);
            g.fillOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);
            g.setColor(Color.BLACK);
            g.drawOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);
            
            g.setColor(Color.BLACK);
            String abbreviation = stateAbbreviations.get(state);
            g.drawString(abbreviation, center.x - 15, center.y - 10);
        }
    }

    
}
