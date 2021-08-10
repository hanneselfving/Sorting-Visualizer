package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OptionsPanel extends JPanel {
	
	DrawPanel dPanel;
	
	public static JButton generateBtn = new JButton("Generate List");
	public static JButton sortBtn = new JButton("Sort List");
	
	public static JCheckBox toggleCompDisplay = new JCheckBox("Mark Nodes");
	
	public static JRadioButton size0 = new JRadioButton("Small");
	public static JRadioButton size1 = new JRadioButton("Large");
	public static ButtonGroup btnGroup = new ButtonGroup();
	
	JSlider delayControl = new JSlider(JSlider.HORIZONTAL, 0, 200, 10);
	
	JLabel delayText = new JLabel("Delay: ");
	
	String[] sortSelText = {"Bubble Sort", "Insertion Sort", "Selection Sort", "Quicksort"};
	public JComboBox sortSel = new JComboBox(sortSelText);

	
	OptionsPanel(DrawPanel dp) {	
		
		this.dPanel = dp;
		this.add(size0);
		this.add(size1);
		btnGroup.add(size0);
		btnGroup.add(size1);
		size0.setSelected(true);
		this.add(generateBtn);
		this.add(sortBtn);
		this.add(toggleCompDisplay);
		toggleCompDisplay.setSelected(true);
		this.add(delayText);
		this.add(delayControl);
		this.add(sortSel);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		initListeners();
		
		
		
		
		
	}

	private void initListeners() {
		
		generateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Generating Nodes...");
				dPanel.generateNodes();
				
			}
		});
		
		sortBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Sorting list...");
				dPanel.runSort(sortSel.getSelectedItem().toString());
				
				
			}
		});
		
		delayControl.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
			    if (!source.getValueIsAdjusting()) {
			        DrawPanel.delay = (int)source.getValue();
			       
			    }
				
			}
		});
		
		sortSel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Main.setInfo(sortSel.getSelectedItem().toString());
				
				
			}
		});
		
		
	}
	
	public static void toggleButtonsActive() {
		
		if(generateBtn.isEnabled() && sortBtn.isEnabled()) {
			generateBtn.setEnabled(false);
			sortBtn.setEnabled(false);
		}
		else {
			generateBtn.setEnabled(true);
			sortBtn.setEnabled(true);
		}
		
		
		
	}

}
