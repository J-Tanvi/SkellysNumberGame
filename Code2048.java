//Name: Tanvi Jain
//Date: June 16th 2023
//Purpose: to express my love to hades (the game) and hope they give me cheat codes to beat the final boss :)

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.sound.sampled.*;
import java.io.File;
import java.io.*;

public class Code2048 extends Applet implements ActionListener
{
    Panel p_card;  //to hold all of the screens
    Panel card1, card2, card3, card4, card5, card6;
    CardLayout cdLayout = new CardLayout ();


    //grid
    int row = 4;
    int col = 4;
    JButton a[] = new JButton [row * col];
    //levels
    int lvlBase[] [] = {{512, 0, 1024, 0},
	    {0, 0, 0, 0},
	    {512, 0, 0, 0},
	    {0, 0, 0, 0}};
    int lvl0[] [] = {{512, 0, 1024, 0},
	    {0, 0, 0, 0},
	    {512, 0, 0, 0},
	    {0, 0, 0, 0}};
    int lvl1[] [] = {{0, 2, 0, 0},
	    {8, 4, 8, 4},
	    {0, 0, 0, 0},
	    {0, 0, 0, 2}};
    int lvl2[] [] = {{2, 2, 2, 2},
	    {4, 4, 0, 0},
	    {0, 0, 0, 0},
	    {0, 0, 0, 0}};
    int lvl3[] [] = {{0, 0, 4, 2},
	    {0, 8, 2, 4},
	    {0, 0, 0, 2},
	    {0, 8, 0, 0}};
    int lvl4[] [] = {{4, 4, 0, 0},
	    {0, 0, 2, 2},
	    {0, 8, 0, 0},
	    {8, 8, 8, 0}};
    int lvl = 1;
    int points = 0;
    JLabel pointsTitle = new JLabel (" " + points);

    public void date ()
    {
	Date now = new Date ();
	DateFormat df = DateFormat.getDateInstance ();
	df.setTimeZone (TimeZone.getTimeZone ("Canada/Toronto"));
	String s = df.format (now);
	JOptionPane.showMessageDialog (null, "Today is " + s,
		"Date", JOptionPane.INFORMATION_MESSAGE);
    }


    public void init ()
    {
	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	date ();
	screen1 ();
	screen2 ();
	screen3 ();
	screen4 ();
	screen5 ();
	resize (450, 630);
	setLayout (new BorderLayout ());
	menu ();
	add ("Center", p_card);
    }


    //adds a menu for users
    public void menu ()
    {
	Color darkRed = new Color (161, 2, 0);
	Color yellow = new Color (255, 177, 74);
	JMenuBar menuBar = new JMenuBar ();
	menuBar.setBackground (darkRed);
	JMenu menu;
	JMenuItem menuItem;

	menu = new JMenu ("File");
	menu.setBackground (darkRed);
	menu.setForeground (yellow);
	menuBar.add (menu);
	menuItem = new JMenuItem ("Date");
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("date");
	menu.add (menuItem);
	menuItem = new JMenuItem ("Quit Game");
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("s5");
	menu.add (menuItem);
	menuItem = new JMenuItem ("Exit");
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("exit");
	menu.add (menuItem);

	menu = new JMenu ("Game Settings");
	menu.setBackground (darkRed);
	menu.setForeground (yellow);
	menuBar.add (menu);
	menuItem = new JMenuItem ("Reset Game");
	menuItem.setActionCommand ("reset");
	menuItem.addActionListener (this);
	menu.add (menuItem);
	menuItem = new JMenuItem ("Save");
	menuItem.setActionCommand ("save");
	menuItem.addActionListener (this);
	menu.add (menuItem);
	menuItem = new JMenuItem ("Load");
	menuItem.setActionCommand ("load");
	menuItem.addActionListener (this);
	menu.add (menuItem);
	menuItem = new JMenuItem ("Skip Level");
	menuItem.setActionCommand ("skip");
	menuItem.addActionListener (this);
	menu.add (menuItem);

	menu = new JMenu ("Navigate");
	menu.setBackground (darkRed);
	menu.setForeground (yellow);
	menuBar.add (menu);
	menuItem = new JMenuItem ("Opening");
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("s1");
	menu.add (menuItem);
	menuItem = new JMenuItem ("Intructions");
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("s2");
	menu.add (menuItem);
	menuItem = new JMenuItem ("Play");
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("s3");
	menu.add (menuItem);

	add ("North", menuBar);
    }


    public void screen1 ()
    { //title
	card1 = new Panel ();
	JButton next = new JButton (createImageIcon ("intro.png"));
	next.setActionCommand ("s2");
	next.addActionListener (this);
	card1.add (next);
	p_card.add ("1", card1);
    }


    public void screen2 ()
    { //instructions
	card2 = new Panel ();
	JButton next = new JButton (createImageIcon ("intruc.png"));
	next.setActionCommand ("s3");
	next.addActionListener (this);
	card2.add (next);
	p_card.add ("2", card2);
    }


    public void screen3 ()
    { //game
	card3 = new Panel ();
	Color red = new Color (162, 1, 0);
	Color yellow = new Color (255, 177, 74);
	card3.setBackground (red);
	JLabel title = new JLabel ("~~Skelly's Game~~");
	title.setFont (new Font ("Constantia", Font.BOLD, 50));
	title.setForeground (yellow);
	pointsTitle = new JLabel ("Points: " + points + "            ");
	pointsTitle.setFont (new Font ("Constantia", Font.BOLD, 20));
	pointsTitle.setForeground (yellow);

	//Set up grid
	Panel p = new Panel (new GridLayout (row, col));
	int move = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a [move] = new JButton (createImageIcon (lvlBase [i] [j] + ".png"));
		a [move].setPreferredSize (new Dimension (100, 100));
		a [move].addActionListener (this);
		a [move].setActionCommand ("" + move);
		p.add (a [move]);
		move++;
	    }
	}
	card3.add (title);

	card3.add (p);

	p_card.add ("3", card3);
	card3.add (pointsTitle);
    }


    public void screen4 ()
    { //win
	card4 = new Panel ();
	JButton next = new JButton (createImageIcon ("youWin.png"));
	next.setActionCommand ("s1");
	next.addActionListener (this);
	card4.add (next);
	p_card.add ("4", card4);
    }


    public void screen5 ()
    { //end
	card5 = new Panel ();
	JButton next = new JButton (createImageIcon ("youLost.png"));
	next.setActionCommand ("s1");
	next.addActionListener (this);
	card5.add (next);
	p_card.add ("5", card5);
    }



    protected static ImageIcon createImageIcon (String path)
    {
	java.net.URL imgURL = Code2048.class.getResource (path);
	if (imgURL != null)
	{
	    return new ImageIcon (imgURL);
	}
	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }


    public void redraw ()
    {
	int move = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a [move].setIcon (createImageIcon (lvlBase [i] [j] + ".png"));
		move++;
	    }
	}

	//adds more pieces after the player makes a move
	int addPiece1 = (int) (Math.random () * 3) + 1;
	int x = (int) (Math.random () * 4);
	int y = (int) (Math.random () * 4);
	if (addPiece1 == 1 && lvlBase [x] [y] == 0)
	{
	    lvlBase [x] [y] = 2;
	}
	else if (addPiece1 == 2 && lvlBase [x] [y] == 0)
	{
	    lvlBase [x] [y] = 4;
	}
	else if (addPiece1 == 3 && lvlBase [x] [y] == 0)
	{
	    lvlBase [x] [y] = 8;
	}
	else
	{
	    x = (int) (Math.random () * 5);
	    y = (int) (Math.random () * 5);
	}
    }



    //arrow key notifiers
    public void addNotify ()
    {
	super.addNotify ();
	setFocusable (true);
	requestFocusInWindow ();
	addKeyListener (new ArrowKeyListener ());
    }


    //arrow key listener
    private class ArrowKeyListener extends KeyAdapter
    {
	public void keyReleased (KeyEvent e)
	{
	    if (e.getKeyCode () == KeyEvent.VK_UP)
	    {
		moveUp (); //Call the move up method

	    }
	    else if (e.getKeyCode () == KeyEvent.VK_DOWN)
	    {
		moveDown (); //Call the move down method

	    }
	    else if (e.getKeyCode () == KeyEvent.VK_LEFT)
	    {
		moveLeft (); //Call the move left method

	    }
	    else if (e.getKeyCode () == KeyEvent.VK_RIGHT)
	    {
		moveRight (); //Call the move right method

	    }
	}
    }


    public void moveUp ()
    { //moves all pieces up
	for (int k = 4 ; k > -1 ; k--)
	{
	    for (int i = 3 ; i > 0 ; i--)
	    {
		for (int j = 3 ; j >= 0 ; j--)
		{
		    if (lvlBase [i] [j] > lvlBase [i - 1] [j] && lvlBase [i - 1] [j] == 0)
		    { //makes sure the piece can go past
			lvlBase [i - 1] [j] = lvlBase [i] [j];
			lvlBase [i] [j] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 2)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i - 1] [j] = 4;               }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 4)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i - 1] [j] = 8;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 8)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i - 1] [j] = 16;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 16)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i - 1] [j] = 32;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 32)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i - 1] [j] = 64;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 64)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i - 1] [j] = 128;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 128)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i - 1] [j] = 256;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 256)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i - 1] [j] = 512;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 512)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i - 1] [j] = 1024;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 1024)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i - 1] [j] = 2048;
			redraw ();
			JOptionPane.showMessageDialog (null, "Not too bad boyo. Click to move on the next one.",
				"Level Completed!", JOptionPane.INFORMATION_MESSAGE);
			win ();
		    }
		    else
		    {
		    }
		}
	    }
	}
	points = points + 150;
	pointsTitle.setText ("Points: " + points);   
	redraw ();
    }


    public void moveDown ()
    { //moves all pieces down
	for (int k = 4 ; k > -1 ; k--)
	{
	    for (int i = 3 ; i > 0 ; i--)
	    {
		for (int j = 3 ; j >= 0 ; j--)
		{
		    if (lvlBase [i] [j] < lvlBase [i - 1] [j] && lvlBase [i] [j] == 0)
		    {
			lvlBase [i] [j] = lvlBase [i - 1] [j];
			lvlBase [i - 1] [j] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 2)
		    {
			lvlBase [i] [j] = 4;
			lvlBase [i - 1] [j] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 4)
		    {
			lvlBase [i] [j] = 8;
			lvlBase [i - 1] [j] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 8)
		    {
			lvlBase [i] [j] = 16;
			lvlBase [i - 1] [j] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 16)
		    {
			lvlBase [i] [j] = 32;
			lvlBase [i - 1] [j] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 32)
		    {
			lvlBase [i] [j] = 64;
			lvlBase [i - 1] [j] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 64)
		    {
			lvlBase [i] [j] = 128;
			lvlBase [i - 1] [j] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 128)
		    {
			lvlBase [i] [j] = 256;
			lvlBase [i - 1] [j] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 256)
		    {
			lvlBase [i] [j] = 512;
			lvlBase [i - 1] [j] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 512)
		    {
			lvlBase [i] [j] = 1024;
			lvlBase [i - 1] [j] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i - 1] [j] && lvlBase [i] [j] == 1024)
		    {
			lvlBase [i] [j] = 2048;
			lvlBase [i - 1] [j] = 0;
			redraw ();
			JOptionPane.showMessageDialog (null, "Not too bad boyo. Click to move on the next one.",
				"Level Completed!", JOptionPane.INFORMATION_MESSAGE);
			win ();
		    }
		    else
		    {
		    }
		}
	    }
	}
	points = points + 87;
	pointsTitle.setText ("Points: " + points);
	redraw ();
    }


    public void moveLeft ()
    { //moves all pieces left
	for (int k = 4 ; k > -1 ; k--)
	{
	    for (int i = 3 ; i >= 0 ; i--)
	    {
		for (int j = 3 ; j > 0 ; j--)
		{
		    if (lvlBase [i] [j] > lvlBase [i] [j - 1] && lvlBase [i] [j - 1] == 0)
		    {
			lvlBase [i] [j - 1] = lvlBase [i] [j];
			lvlBase [i] [j] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 2)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i] [j - 1] = 4;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 4)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i] [j - 1] = 8;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 8)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i] [j - 1] = 16;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 16)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i] [j - 1] = 32;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 32)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i] [j - 1] = 64;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 64)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i] [j - 1] = 128;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 128)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i] [j - 1] = 256;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 256)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i] [j - 1] = 512;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 512)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i] [j - 1] = 1024;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 1024)
		    {
			lvlBase [i] [j] = 0;
			lvlBase [i] [j - 1] = 2048;
			redraw ();
			JOptionPane.showMessageDialog (null, "Not too bad boyo. Click to move on the next one.",
				"Level Completed!", JOptionPane.INFORMATION_MESSAGE);
			win ();
		    }
		    else
		    {
		    }
		}
	    }
	}
	points = points + 87;
	pointsTitle.setText ("Points: " + points);
	redraw ();
    }


    public void moveRight ()
    { //moves all pieces right
	for (int k = 4 ; k > -1 ; k--)
	{
	    for (int i = 3 ; i >= 0 ; i--)
	    {
		for (int j = 3 ; j > 0 ; j--)
		{
		    if (lvlBase [i] [j] < lvlBase [i] [j - 1] && lvlBase [i] [j] == 0)
		    {
			lvlBase [i] [j] = lvlBase [i] [j - 1];
			lvlBase [i] [j - 1] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 2)
		    {
			lvlBase [i] [j] = 4;
			lvlBase [i] [j - 1] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 4)
		    {
			lvlBase [i] [j] = 8;
			lvlBase [i] [j - 1] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 8)
		    {
			lvlBase [i] [j] = 16;
			lvlBase [i] [j - 1] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 16)
		    {
			lvlBase [i] [j] = 32;
			lvlBase [i] [j - 1] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 32)
		    {
			lvlBase [i] [j] = 64;
			lvlBase [i] [j - 1] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 64)
		    {
			lvlBase [i] [j] = 128;
			lvlBase [i] [j - 1] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 128)
		    {
			lvlBase [i] [j] = 256;
			lvlBase [i] [j - 1] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 256)
		    {
			lvlBase [i] [j] = 512;
			lvlBase [i] [j - 1] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 512)
		    {
			lvlBase [i] [j] = 1024;
			lvlBase [i] [j - 1] = 0;
		    }
		    else if (lvlBase [i] [j] == lvlBase [i] [j - 1] && lvlBase [i] [j] == 1024)
		    {
			lvlBase [i] [j] = 2048;
			lvlBase [i] [j - 1] = 0;
			redraw ();
			JOptionPane.showMessageDialog (null, "Not too bad boyo. Click to move on the next one.",
				"Level Completed!", JOptionPane.INFORMATION_MESSAGE);
			win ();
		    }
		    else
		    {
		    }
		}
	    }
	}
	points = points + 87;
	pointsTitle.setText ("Points: " + points);
	redraw ();
    }


    public void replace (int a[] [], int b[] [])
    { //changes levels
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a [i] [j] = b [i] [j];
	    }
	}
    }



    public void reset ()
    { //resets the game
	points = points - 3000;
	lvl = 1;
	replace (lvlBase, lvl0);
	redraw ();
    }


    public void win ()
    { //checks for player win
	if (lvl == 1)
	{
	    replace (lvlBase, lvl1);
	    lvl++;
	}
	else if (lvl == 2)
	{
	    replace (lvlBase, lvl2);
	    lvl++;
	}
	else if (lvl == 3)
	{
	    replace (lvlBase, lvl3);
	    lvl++;
	}
	else if (lvl == 4)
	{
	    replace (lvlBase, lvl4);
	    lvl++;
	}
	else if (lvl == 5)
	{
	    JOptionPane.showMessageDialog (null, "You win!! Now you can either continue or replay the full game!",
		    "Congrats!", JOptionPane.INFORMATION_MESSAGE);
	    cdLayout.show (p_card, "4");
	}
	redraw ();
    }


    public void save (String fileName)
    { //creates a txt doc that saves the current lvl
	PrintWriter out;
	try
	{
	    out = new PrintWriter (new FileWriter (fileName));
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    out.println (lvlBase [i] [j]);
		}
	    }
	    out.close ();
	}
	catch (IOException e)
	{
	    System.out.println ("Error opening file " + e);
	}
    }


    public void load (String fileName)
    {
	BufferedReader in;
	try
	{
	    in = new BufferedReader (new FileReader (fileName));
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    lvlBase [i] [j] = Integer.parseInt (in.readLine ());
		}
	    }
	    in.close ();
	}
	catch (IOException e)
	{
	    System.out.println ("Error opening file " + e);
	}
	redraw ();
    }



    public void actionPerformed (ActionEvent e)
    { //moves between the screens
	if (e.getActionCommand ().equals ("s1"))
	    cdLayout.show (p_card, "1");
	else if (e.getActionCommand ().equals ("s2"))
	    cdLayout.show (p_card, "2");
	else if (e.getActionCommand ().equals ("s3"))
	    cdLayout.show (p_card, "3");
	else if (e.getActionCommand ().equals ("s4"))
	    cdLayout.show (p_card, "4");
	else if (e.getActionCommand ().equals ("s5"))
	    cdLayout.show (p_card, "5");
	else if (e.getActionCommand ().equals ("exit"))
	    System.exit (0);
	else if (e.getActionCommand ().equals ("date"))
	    date ();
	else if (e.getActionCommand ().equals ("reset"))
	    reset ();
	else if (e.getActionCommand ().equals ("save"))
	    save ("game.txt");
	else if (e.getActionCommand ().equals ("load"))
	    load ("game.txt");
	else if (e.getActionCommand ().equals ("skip"))
	    win ();
	requestFocusInWindow ();
	redraw ();
    }
}
