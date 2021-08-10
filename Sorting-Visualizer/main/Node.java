package main;

import java.awt.Color;
import java.awt.Graphics;

public class Node {

	public int value;
	public boolean selected = false;
	public boolean compared = false;
	
	public Node(int val) {
		this.value = val;
	}
	
	public void render(Graphics g, int x, int w) {
		
		if(selected)
			g.setColor(Color.RED);
		else if(compared) {
			g.setColor(Color.BLUE);
		}
		else
			g.setColor(Color.LIGHT_GRAY);
		
		g.fillRect(x, DrawPanel.height-value, w, value);
		g.setColor(Color.BLACK);
		g.drawRect(x, DrawPanel.height-value, w, value);
		if(DrawPanel.size == 0) {
		g.drawString("" + value, x + 2, DrawPanel.height - 10);
		}
		
	}
	
	
	
}
