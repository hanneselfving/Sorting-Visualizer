package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.SwingWorker;


public class DrawPanel extends JPanel {
	
	int scale = 5;
	
	public static int height, width;
	
	public static int size = 1;
	
	public static int delay = 10;
	
	public static final int SMALL = 20;
	public static final int MED = 100;
	
	public static List<Node> list =  Collections.synchronizedList(new LinkedList<Node>());
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		width = getWidth();
		height = getHeight();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		if(!list.isEmpty()) {
			int c = 0;
			int s = SMALL;
			if(size == 0) {
			s = SMALL;
			}
			else if(size == 1) {
			s = MED;	
			}
			
			for(Node n : list) {
				if(n != null) {
				n.render(g, c, (width/s));
				}
				c+=(width/s);
				
				
			}
			
	
			
		}
		
		
		
		
	}
	
	//Sizes: 0 = small, 1 = med, 2 = large
	public void generateNodes() {
		
		if(!list.isEmpty()) {
			list.clear();
		}
		
				if(OptionsPanel.size0.isSelected())
					size = 0;
				else if(OptionsPanel.size1.isSelected())
					size = 1;

				
				switch(size) {
				case 0:
					for(int i = 0; i < SMALL; i++) {
						list.add(new Node(getRandInt()));
					
					}
					repaint();
				break;
				case 1:
					for(int i = 0; i < MED; i++) {
						list.add(new Node(getRandInt()));
					}
					repaint();
				break;
				}
				System.out.println("List Generated. Size: " + list.size());	
			}
	

	
	public void runSort(String sortStr) {
		
		
		
		if(sortStr.contains("Bubble")) {
			System.out.println("Running Bubble Sort");
			bubbleSort();
			
		}
		
		else if(sortStr.contains("Insertion")) {
			System.out.println("Running Insertion Sort");
			insertionSort();
		}
		
		else if(sortStr.contains("Quick")) {
			System.out.println("Running Quick Sort");
			quickSort();
		}
		
		else if(sortStr.contains("Selection")) {
			System.out.println("Running Selection Sort");
			selectionSort();
			
		}
		
		else if(sortStr.contains("Merge")) {			
			System.out.println("Running Merge Sort");
			mergeSort();
		}
		
			
		
	}

	static int getRandInt() {
		return (int) (Math.random() * height);
		
	}

	private void insertionSort() {
		if(list.isEmpty()) {
			return;
		}
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	
			@Override
			protected Void doInBackground() throws Exception {
				
				for(int j = 1; j < list.size(); j++) {
					
					
					int key = list.get(j).value;
					int comp = j-1;
					
					if(OptionsPanel.toggleCompDisplay.isSelected() == true) {
					list.get(j).compared = true;
					list.get(comp).compared = true;
					repaint();
					Thread.sleep(delay);
					}
					
					if(key < list.get(comp).value) {
						for(int i2 = 0; i2 < list.size(); i2++) {
							if(OptionsPanel.toggleCompDisplay.isSelected() == true) {
							list.get(i2).selected = true;
							repaint();
							Thread.sleep(delay);
							list.get(i2).selected = false;
							}
							if(list.get(i2).value >= key) {
								list.add(i2, new Node(key));
								list.remove(j + 1);
								break;
							}
							
						}
						
					}
					
					
					
					if(OptionsPanel.toggleCompDisplay.isSelected() == true) {
					list.get(j).compared = false;
					list.get(comp).compared = false;
					repaint();
					Thread.sleep(delay);
					}
					
					
				}
				
				repaint();
				
				return null;
			}
			
		};
		worker.execute();
	}

	private void bubbleSort() {
		if(list.isEmpty()) {
			return;
		}
		else {
			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	
				@Override
				protected Void doInBackground() throws Exception {
					
					for(int i = 0; i < list.size()-1; i++) {
						for(int i2 = 0; i2 < list.size()-1-i;i2++) {
							list.get(i2).compared = true;
							list.get(i2 + 1).compared = true;
							if(OptionsPanel.toggleCompDisplay.isSelected()) {
							repaint();
							Thread.sleep(delay);
							}
							if(list.get(i2).value > list.get(i2 + 1).value) {
								list.get(i2).selected = true;
								list.get(i2 + 1).selected = true;
								if(OptionsPanel.toggleCompDisplay.isSelected()) {
								repaint();
								Thread.sleep(delay);
								}
								Collections.swap(list, i2, i2+1);
								list.get(i2).selected = false;
								list.get(i2 + 1).selected = false;
					
							}
							list.get(i2).compared = false;
							list.get(i2 + 1).compared = false;
							repaint();
							Thread.sleep(delay);
						}
						
						
						
					}
					
					
					
					
					return null;
				}
				
			};
			worker.execute();
		}
		
		
		
	}

	private void selectionSort() {
		if(list.isEmpty()) {
			return;
		}
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				int minElementIndex = 0;
				
				for(int j = 0; j<list.size(); j++) {
					minElementIndex = j;
					for(int i = j+1; i < list.size(); i++) {
						
	
						list.get(i).compared = true;
						if(OptionsPanel.toggleCompDisplay.isSelected() == true) {
						repaint();
						Thread.sleep(delay);
						}
						
						if(list.get(i).value < list.get(minElementIndex).value) {
							list.get(minElementIndex).selected = false;
							minElementIndex = i;	
							list.get(i).selected = true;	
						}
						
						list.get(i).compared = false;
						Thread.sleep(delay);
						repaint();
					}
					
					Collections.swap(list, j, minElementIndex);	
					list.get(j).selected = true;
					
				}
				
				repaint();
				return null;
			}
		};
		worker.execute();
	
			
		
	}

	private void quickSort() {
		if(list.isEmpty()) {
			return;
		}
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				
				
				recQuick(list);
				
				repaint();
					
				
				
				return null;
			}

			private void recQuick(List<Node> subList) {

				if(subList.size() <= 1) {
					return;
				}
				//Select pivot at the very left
				Node pivot = subList.get(0);
				int pivotPos = 0;
				
				if(OptionsPanel.toggleCompDisplay.isSelected()) {
				pivot.selected=true;
				repaint();
				
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				
				for(int i = 1; i < subList.size(); i++) {
						int compVal = subList.get(i).value;
						
						if(OptionsPanel.toggleCompDisplay.isSelected()) {
						subList.get(i).compared = true;
						repaint();
						try {
							Thread.sleep(delay);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
						
					if(compVal <= pivot.value) {
						
						subList.remove(i);
						subList.add(0,new Node(compVal));
						pivotPos++;
					}
						if(OptionsPanel.toggleCompDisplay.isSelected()) {
						subList.get(i).compared = false;
						repaint();
						}
				}
				
				recQuick(subList.subList(0, pivotPos));
				recQuick(subList.subList(pivotPos + 1, subList.size()));
			
			}
			
		};
		worker.execute();
		
	}
	
	public void mergeSort() { // Not finished
		if(list.isEmpty()) {
			return;
		}
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				
				
			//	recSort(list);
				
				return null;
			}
			
			private void recSort(List<Node> subList) {
				
				//Return if size is 1
				if(subList.size() <= 1) {
					return;
					}
				
				//Split list in two sublists
				int middle = subList.size()/2;
				int midVal = subList.get(middle).value;
				
				List<Node> listA = new LinkedList<Node>(list.subList(0, middle));
				List<Node> listB = new LinkedList<Node>(list.subList(middle, subList.size()));
				
				listB.get(0).selected=true;
				repaint();
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				recSort(listA);
				recSort(listB);
				merge(listA,listB,subList);
				repaint();
				
				
			}
			
			private void merge(List<Node> left, List<Node> right, List<Node> list) {
				
				    int leftIndex = 0;
				    int rightIndex = 0;
				    int listIndex = 0;

				    while (leftIndex < left.size() && rightIndex < right.size()) {
				      if (left.get(leftIndex).value < right.get(rightIndex).value) {
				        list.set(listIndex++, left.get(leftIndex++));
				      } else {
				        list.set(listIndex++, right.get(rightIndex++));
				      }
				      repaint();
				      try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    }
				    while (leftIndex < left.size()) {
				      list.set(listIndex++, left.get(leftIndex++));
				      repaint();
				      try {
							Thread.sleep(delay);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				    while (rightIndex < right.size()) {
				      list.set(listIndex++, right.get(rightIndex++));
				      repaint();
				      try {
							Thread.sleep(delay);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				  }
				
			
			
		};
		worker.execute();
	}
	
	
}

