import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DropDownMenu {



    public Integer createAndShowGUI() {
    	JFrame frame = new JFrame("Pick the number of colours");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create a panel to hold components
        JPanel panel = new JPanel();

        // Create a label
        JLabel label = new JLabel("Select a color:");

        // Creating a dropdown menu 
        Integer[] colours = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        JComboBox<Integer> comboBox = new JComboBox<Integer>(colours);

  
        JButton button = new JButton("Now choose the colors for your map :)");

        Integer[] selectedItem = new Integer[1];

        // Add an action listener to the button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedItem[0] = (Integer) comboBox.getSelectedItem();
                frame.dispose(); // Close the frame
            }
        });

        // Add components to the panel
        panel.add(label);
        panel.add(comboBox);
        panel.add(button);

        // Add the panel to the frame
        frame.getContentPane().add(panel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);

        while (selectedItem[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return selectedItem[0];
    }
    
    
	}
        
  


