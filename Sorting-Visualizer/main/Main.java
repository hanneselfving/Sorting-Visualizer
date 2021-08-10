package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

	public static final int SCREENW = 1280;
	public static final int SCREENH = 720;
	public static final String TITLE = "Sorting Visualizer";
	public static JTextArea infoArea;
	
	Main() {
		
		this.setPreferredSize(new Dimension(SCREENW,SCREENH));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	public static void main(String[] args) {
		
		Main frame = new Main();
		DrawPanel drawPanel = new DrawPanel();
		OptionsPanel optionsPanel = new OptionsPanel(drawPanel);
		infoArea = new JTextArea();		
		infoArea.setEditable(false);
		
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				frame.setVisible(true);
			}
		});
		
		frame.add(drawPanel, BorderLayout.CENTER);
		frame.add(optionsPanel, BorderLayout.NORTH);
		frame.add(infoArea, BorderLayout.EAST);
		
		setInfo("Bubble");
		

		frame.pack();
		
		DrawPanel.height = drawPanel.getHeight();
		DrawPanel.width = drawPanel.getWidth();
		
		drawPanel.repaint();

	}
	
	public static void setInfo(String sortStr) {
		
		if(sortStr.contains("Bubble")) {
			infoArea.setText("Sort Name: Bubble Sort\nAverage Time Complexity: O(n^2)");
			
		}
		
		else if(sortStr.contains("Insertion")) {
			infoArea.setText("Sort Name: Insertion Sort\nAverage Time Complexity: O(n^2)");
		}
		
		else if(sortStr.contains("Quick")) {
			infoArea.setText("Sort Name: Quicksort\nAverage Time Complexity: O(nlogn)");
		}
		
		else if(sortStr.contains("Selection")) {
			infoArea.setText("Sort Name: Seleciton Sort\nAverage Time Complexity: O(n^2)");
			
		}
		
		
	}

	
	
}
