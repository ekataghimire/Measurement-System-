import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.event.*;
import javax.swing.*;

/**
 * The main graphical panel used to display conversion components.
 * 
 * This is the starting point for the assignment.
 * 
 * The variable names have been deliberately made vague and generic, and most comments have been removed.
 * 
 * You may want to start by improving the variable names and commenting what the existing code does.
 * 
 * @author mdixon
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private final static String[] list = { "inches/cm","Pounds/kilograms","Degrees/Radians","Miles/Kilometers","Acres/Hectares","Yards/Meters","Celcius/Fahrenheit" };
	private JTextField textField;
	private JLabel label;
	private JComboBox<String> combo;
	static int counter = 0, checked;
	static double result;
	JLabel count;


	JMenuBar setupMenu() {

		JMenuBar menuBar = new JMenuBar();  //Jmenubar class le menubar create gareko

		JMenu m1 = new JMenu("File"); //menubar bhitra menu creat gareko
		m1.setToolTipText("Click here to Go to files");
		JMenu m2 = new JMenu("Edit");//menubar 
		m2.setToolTipText("Click here to Edit files");
		JMenu m3 = new JMenu("Help");//menubar 
		m3.setToolTipText("Click here to get Help");
		

		menuBar.add(m1);  //adding to the menu bar
		menuBar.add(m2);
		menuBar.add(m3); 
		
		//for file menu bar menu items
		JMenuItem ne = new JMenuItem("New");  //menu items create matra bhako xa
		m1.add(ne);  //adding menuItem into menu
		//adding image 
	    ne.setIcon(new ImageIcon("C:/assignment/src/icons8-file-16.png"));
		//adding keystroke to get the keyEvent
		ne.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));	
		
		
		JMenuItem save = new JMenuItem("Save");
	    //adding icon to the menuitem
		save.setIcon(new ImageIcon("C:/assignment/src/icons8-save-16.png"));
		save.setToolTipText("Click here to save files");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		m1.add(save);

		JMenuItem exit = new JMenuItem("Exit");
		m1.add(exit);
		exit.setIcon(new ImageIcon("C:/assignment/src/icons8-exit-16.png"));
		exit.setToolTipText("Click here Exit the files");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,InputEvent.CTRL_MASK));
		//adding action listener 
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) { 
				System.exit(0);
			}
		});
	//for Help menu bar menu items
		JMenuItem ab = new JMenuItem("About");
		ab.setToolTipText("Click here get the information of the programs");
		ab.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) { 
				JOptionPane.showMessageDialog(null, "<html>"
						+ "The Purpose of this program is to convert into "
						+ "numeric values! <br>"
						+ "Author: Mark Dixon and Smita Neupane<br>"
						+"Unpublished Work © 2021 Smita Neupane"
						+"</html>");
				}
		});	
		m3.add(ab);
		
		final JCheckBox checkBox = new JCheckBox("Reverse Conversion");
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				checked = e.getStateChange();
		checkBox.setToolTipText("This reverse yours converter.");
			}		
		});
		add(checkBox);
		return menuBar;
	}


	MainPanel() {

		ActionListener listener = new ConvertListener();

		combo = new JComboBox<String>(list);
		combo.addActionListener(listener); //convert values when option changed
		combo.setToolTipText("Click to get the different types of conversions ");
		
		JLabel inputLabel = new JLabel("Enter value:");

		final JButton convertButton = new JButton("Convert");
		convertButton.setToolTipText("Click to Convert");
		convertButton.addActionListener(listener); // convert values when pressed

		label = new JLabel("---");
		textField = new JTextField(5);
		textField.setToolTipText("Enter Number to get the conversion");
		
		JButton clearbutton = new JButton("Clear");
		clearbutton.setToolTipText("Click here to clear the data");
		clearbutton.addActionListener(new clearButton());
		
		count = new JLabel(String.valueOf(counter));
		count.setToolTipText("Number of times the conversion took place");
		
		add(combo);
		add(inputLabel);
		add(textField);
		add(convertButton);
		add(label);
		add(clearbutton);
		add(count);
		textField.addKeyListener(new KeyAdapter()//anonymous inner class  
				{
					public void keyPressed(KeyEvent e)
					{
						if(e.getKeyCode() == KeyEvent.VK_ENTER)
						{
							convertButton.doClick();
						}
					}
				});
		
		
		setPreferredSize(new Dimension(800, 80));
		setBackground(Color.LIGHT_GRAY);
	}
	
private class clearButton implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			textField.setText(null);
			count.setText("Count: 0 ");
			label.setText(null);
		}
	}

	private class ConvertListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			String text = textField.getText().trim();

			if (text.isEmpty()==false) {
				try {
				
				double value = Double.parseDouble(text);

				// the factor applied during the conversion
				double factor = 0;

				// the offset applied during the conversion.
				double offset = 0;

				//switch (combo.getSelectedIndex()) {

				
					if(checked == 0) {
					// Setup the correct factor/offset values depending on required conversion
					switch (combo.getSelectedIndex()) {
						
					case 0:		 						// convert inches to cm
						factor = 2.54;
						result = factor * value + offset;
						break;
						
					case 1: 							//convert pounds to kilogram
						factor = 0.45;
						result = factor * value;
						break;
						
					case 2: 							//convert degree to radians
						factor =0.0174533;
						result = factor * value;
						break;
						
					case 3: 							//convert acres to hectares
						factor = 0.40;
						result = factor * value;
						break;
						
					case 4:								//convert miles to kilometer
	
						factor = 1.60;
						result = factor * value;
						break;
						
					case 5: 							//convert yards to meter
						factor = 0.91;
						result = factor * value;
						break;
						
					case 6: 							//convert celsius to fahrenheit
						factor =1.8;
						offset = 32.00;
						result = factor * value + offset;
						break;	
					}
					
					}
					
					
					else {
						switch (combo.getSelectedIndex()) {
						
						case 0:
							factor =  0.393701;
							result = factor * value;
							break;
							
						case 1:
							factor = 2.20462;
							result = factor * value;
							break;
							
						case 2:
							factor =  57.2958;
							result = factor * value;
							break;
							
						case 3:
							factor =2.47105;
							result = factor * value;
							break;
							
						case 4:
							factor =0.6213719;
							result = factor * value;
							break;
							
						case 5:
							factor = 1.09361;
							result =(value - factor) * (5/9);
							break;
						
						case 6:
							factor = 0.55555556;
							offset = -17.78;
							result = factor * value + offset;
							break;
						}
					}


				label.setText(String.format("%.2f",result));
				counter++;
				count.setText(String.valueOf(counter));
				}
				
				catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Input must be an Integer");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Input cannot be Empty");
			}
			
		}
	}
}