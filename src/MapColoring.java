import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MapColoring{
	
	int numberOfVariables = 10;
	int colors[];
	static String colorStrings[];
	static String[] colorMappings;
	static String[] states = {"Jammu and Kashmir", "Ladakh", "Punjab", "Himachal Pradesh", "Rajasthan", "Haryana", "Uttarakhand", "Nepal", "Bihar", "Bhutan"};

	// This method returns a color corresponding to a string.
	public static Color getColorFromString(String colorString) {
        Map<String, Color> colorMap = createColorMap();
        return colorMap.get(colorString.toLowerCase());
    }

	// This method maps a string to a COLOR object.
    public static Map<String, Color> createColorMap() {
        Map<String, Color> colorMap = new HashMap<>();
        colorMap.put("red", Color.RED);
        colorMap.put("blue", Color.BLUE);
        colorMap.put("green", Color.GREEN);
        colorMap.put("pink", Color.PINK);
        colorMap.put("black", Color.BLACK);
        colorMap.put("orange", Color.ORANGE);
        colorMap.put("yellow", Color.YELLOW);

        return colorMap;
    }
    
    // This method just creates a frame and panel and shows the colors chosen by the user. It shows the circles with the chosen colours.
	public static void colorGUI()
	{
		JFrame frame = new JFrame("Here are your colors: ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        JLabel label = new JLabel("These are the colors you have picked.");
   
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                
                int i = 50;
                for(int j = 0; j < colorStrings.length; j++) {
                	Color str1 = getColorFromString(colorStrings[j]);     	
                	 g.setColor(str1);
                     g.fillOval(i, 100, 50,50);
                     i += 100;
                }
            }
        };
 
        panel.add(label);
        // Add the panel to the frame
        frame.getContentPane().add(panel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }
	
	// This method takes two parameters: problem(which is the two dimensional integer array that represents the graph), n(Number of states we are trying to color).
	// It returns true if it is possible to colour this graph using the given number of colors and false if it is not possible.
	// It gives the solution by printing the colours assigned to each state.
	public boolean ColoringMap(int[][] problem, int n) {
		colors = new int[10]; // This array stores the numbers corresponding to each color.
		colorMappings = new String[10]; // This array stores the actual color strings.
		
		Arrays.fill(colors, 0);
		boolean result = colorAssignment(0, colors, problem, n);	
	
		
		if(result == false) {
			System.out.println("There is no solution.");
			return false;
		}
	
		
		else {

			
			for(int i=0; i < 10; i++) {
				colorMappings[i] = colorStrings[colors[i]-1];
			}
			
			
			System.out.println();
			System.out.println("Here is the list of states with their corresponding colors.\n");
			for(int i = 0; i < 10; i++) {
				System.out.println(states[i] + ":       " + colorMappings[i]);
			}
		}
		
		return true;
	}
	
	// This method assigns colors to variables such that their is no conflict.
	public boolean colorAssignment(int variable, int[] colors, int[][] problem, int n) {
		if(variable == 10) {
			return true;
		}
		else {
			
			for(int colorNumber = 1; colorNumber <= n; colorNumber++) {
				boolean flag = true;
				// Checks if there is a conflict with the neighbors.
				for(int i = 0; i < problem[0].length; i++) {
					if(problem[variable][i] ==  1 && colors[i] == colorNumber) {
						flag = false;
						break;
					}
					
				}
				if(flag == true) {
					colors[variable] = colorNumber;
					if(colorAssignment(variable + 1, colors, problem, n)) {
						return true;
					};
					
					colors[variable] = 0;
				}
				
			}
			return false;
			
		}
	}
	public static void main(String[] args)
		{
		JFrame frame = new JFrame("Map Coloring Problem is here :(");
        IndianMap indiaMap = new IndianMap();

        
        for (String state : states) {
          
            indiaMap.stateColors.put(state, Color.black);
           
        }
        frame.add(indiaMap);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
		
		System.out.println("This is a Map Coloring Problem for the Nothern states of the Indian Map.");
		System.out.println("*******************************************************");
		System.out.println("The 10 states we are considering for the Map Coloring Problem are as follows: ");
		System.out.println();
		System.out.println(" 1. Ladakh\n 2. Jammu and Kashmir \n 3. Punjab\n 4. Himachal Pradesh\n 5. Rajasthan\n 6. Haryana\n 7. Uttarakhand\n 8. Nepal\n 9. Bihar\n 10. Bhutan");
		 
		DropDownMenu menu = new DropDownMenu();
		
		int numberOfColors = menu.createAndShowGUI();
		System.out.println("Pick your " + numberOfColors + " colours");
		
		colorStrings = new String[numberOfColors];
		for(int i = 0; i < numberOfColors; i++) {
			System.out.println("Enter a color: ");
			Scanner scan = new Scanner(System.in);
			String color = scan.nextLine();
			colorStrings[i] = color;
			}
		colorGUI();
		
		
		// Now, let's represent the problem as a graph.
		
		int MapProblem[][] = {
	            {0,1,1,1,0,0,0,0,0,0},
	            {1,0,0,1,0,0,0,0,0,0},
	            {1,0,0,1,1,1,0,0,0,0},
	            {1,1,1,0,0,1,1,0,0,0},
	            {0,0,1,0,0,1,0,0,0,0},
	            {0,0,1,1,1,0,1,0,0,0},
	            {0,0,0,1,0,1,0,1,1,0},
	            {0,0,0,0,0,0,1,0,1,1},
	            {0,0,0,0,0,0,1,1,0,0},
	            {0,0,0,0,0,0,0,1,0,0}
	        };
		
		MapColoring mapColoring = new MapColoring();
		boolean result = mapColoring.ColoringMap(MapProblem, numberOfColors);
		
		if(result) {

			JFrame frame1 = new JFrame("Map Coloring Solution is here :)");
	        IndianMap indiaMap1 = new IndianMap();

	        int i = 0;
	        for (String state : states) {
	            String str = colorMappings[i];
	            Color userColor = getColorFromString(str);
	            indiaMap1.stateColors.put(state, userColor);
	            i++;
	        }
	        frame1.add(indiaMap1);
	        frame1.setSize(400, 400);
	        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame1.setVisible(true);
		}
		}
	
	
		
	
}