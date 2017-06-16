/* Craig Gardner
 * CSC 365
 * 9/21/2014
 * BSTSWINGAPP
 */


import javax.swing.*;
import javax.swing.SwingUtilities.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.io.*;



public class BSTSwingApp extends JFrame implements ActionListener {

	static final boolean DEBUG=false;
	public static final int XDIM = 800;
	public static final int YDIM = 300;

	// define non-GUI objects;
	BST<Integer> bst;
	boolean clearFlag;
	Random r;
	
	// define GUI objects (widgets) that will comprise this GUI app
	JButton loadButton;
	JButton clearButton;
	JButton addButton;
	JButton reflectButton;
	JButton saveButton;
	JButton removeButton;
	JFormattedTextField inputField;
	JTextField debugField;

	JPanel buttonPanel;
	TreePanel treePanel;
	TreePanel reflectPanel;

	// GUI constructor
	public BSTSwingApp() {
		// set up non-GUI objects
		clearFlag = true;
		r = new Random();
		bst = new BST(new NaturalComparator());

		// set up our frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // center the frame on the monitor
		
		setLayout(new BorderLayout() ); // set Layout Manager for this frame

		//create widgets
		addButton =     new JButton("  Add  ");
		reflectButton = new JButton("Reflect");
		clearButton =   new JButton(" Clear ");
		loadButton = 	new JButton(" Load ");
		saveButton = 	new JButton(" Save " );
		removeButton =  new JButton("Remove" );
		inputField =    new JFormattedTextField(0);
		//debugField =    new JTextField(10);
		//debugField.setEditable(false);
		inputField.setColumns(2);

		//create the panels
        	buttonPanel = new JPanel();
		treePanel = new TreePanel();
		reflectPanel = new TreePanel();
	
		// set up the layout for the panels if we're putting widgets in them
		buttonPanel.setLayout(new FlowLayout());

		// add widget(s) to panel(s)
	
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(inputField);
		buttonPanel.add(clearButton);
		buttonPanel.add(reflectButton);
		buttonPanel.add(loadButton);
		buttonPanel.add(saveButton);
		//buttonPanel.add(debugField);
	
		// add panels to our frame 
		add(treePanel, BorderLayout.WEST);
		add(reflectPanel, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.NORTH);


		// add action Listeners for each widget we want to listen for 
	        addButton.addActionListener(this);
		reflectButton.addActionListener(this);
		clearButton.addActionListener(this);
		saveButton.addActionListener(this);
		loadButton.addActionListener(this);
		removeButton.addActionListener(this);
			

		// pack the widgets and make frame visible
		setSize(XDIM,YDIM);
		pack();
		setResizable(false);
		setVisible(true);   // make frame visible
		treePanel.repaint();
	    } // end constructor



	public void actionPerformed(ActionEvent e) {
		if (e.getSource() != reflectButton) {
			clearFlag = true;
			reflectPanel.setVisible(false);
		}
		
		if (e.getSource() == loadButton) {
			clearFlag = false;
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + ".bstrc"));
				bst = (BST) ois.readObject();
				ois.close();
				//	System.out.println("Root = " + bst.root.data);
				//	System.out.println("Lchild = " + bst.root.left.data);
				JOptionPane.showMessageDialog(treePanel, "File loaded");		
			} catch (IOException io) {
				io.printStackTrace();
	    		}
	       		catch (ClassNotFoundException cnf) {
				cnf.printStackTrace();
			}
			treePanel.repaint();
		}

		if (e.getSource() == clearButton) {
			clearFlag = true;
		//	debugField.setText("clear button");
			bst.root.clear();
			treePanel.repaint();
			JOptionPane.showMessageDialog(clearButton,"Tree Cleared.");
		}

		if (e.getSource() == reflectButton) {
			reflectPanel.setVisible(true);
		//	debugField.setText("reflect button");
			bst.root.reflect();
			reflectPanel.repaint();
			JOptionPane.showMessageDialog(addButton,"Tree Reflected.");
			bst.root.reflect();
		}

		if (e.getSource() == addButton) {
			if (bst.root.size() <= 8) {
				clearFlag = false;
			//	debugField.setText("add button");
				int i = r.nextInt(100);
				bst.root.put(i);
		    		treePanel.repaint();
			} else {
				JOptionPane.showMessageDialog(addButton,"Cannot add any more nodes.");
			}	
		}

		if (e.getSource() == saveButton) {
			FileOutputStream fos = null;
			ObjectOutputStream oos = null;
			try {
				File bstrc = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + ".bstrc");
				fos = new FileOutputStream(bstrc);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(bst);
				oos.flush();
				fos.flush();
				oos.close();	
				fos.close();
			} catch (IOException io) { io.getMessage(); }
			JOptionPane.showMessageDialog(saveButton,"Tree Saved.");
		}

		if (e.getSource() == removeButton) {
			clearFlag = false;
			int i = (Integer) inputField.getValue();
			if (bst.root.contains(i)) {
				if (bst.root.remove(i))
					JOptionPane.showMessageDialog(removeButton,"Node Removed");
		//		debugField.setText("remove");
			}	
			treePanel.repaint();
		}	
	}
    
	public static void createAndShowGUI() {
		// Launch the GUI
		BSTSwingApp bts = new BSTSwingApp();
	}
    
	public static void main(String [] args) {
		// schedule a job for the event dispatch thread,
		// creating and showing this application's GUI
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
		    		createAndShowGUI();
			}
		});
	} //end main
    
    
	class TreePanel extends JPanel {
	    
		public TreePanel() {
			setBorder(BorderFactory.createLineBorder(Color.blue));
		}
		public Dimension getPreferredSize() {
			return new Dimension(XDIM/2,YDIM);
		}
	
		// calling a panel's repaint() method will call paintComponent
		protected void paintComponent(Graphics g) {
			// call JPanel's paintComponent
			super.paintComponent(g);
			if (bst.root.isEmpty() || clearFlag)
				return;
			// now we add out stuff to TreePanel
			int top = (Integer)bst.root.data;
			int xCenter = XDIM/4;
			int yCenter = 30;
			BST.Node.InOrder in = bst.root.inOrder();
			while (in.hasNext()) {
				Integer j = (Integer)in.next();
				int x = xCenter
				       	+ (bst.root.xCoord 
					- bst.root.find(j).xCoord) * 25;
				int y = yCenter 
					+ (bst.root.find(j).level() * 20);
				g.drawString(j.toString(), x, y);
	    			//draw lines
				if (!bst.root.find(j).left.isEmpty())
	    				g.drawLine(x, y, x 
						- (bst.root.find(j).left.xCoord
						- bst.root.find(j).xCoord) 
						* 25, y + 25);
				if (!bst.root.find(j).right.isEmpty())
					g.drawLine(x, y, x 
						+ (bst.root.find(j).xCoord 
						- bst.root.find(j).right.xCoord)
						* 25, y + 25);
				//int size = bst.root.size();
				//debugField.setText(size.toString());
	    		}
		}
	}
}
    

