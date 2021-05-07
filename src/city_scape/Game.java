///Yago Lacerda C19481114
package city_scape;

import java.awt.image.ImageObserver;

import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import city_scape.Main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame
{
    private Image screenImage;
    private Graphics screenGraphic;
    private Image Background= new ImageIcon(Main.class.getResource("../images/background.jpg")).getImage();
    private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menubar.png")));
    
    
    private ImageIcon Start1 = new ImageIcon(Main.class.getResource("../images/Start1.png"));
    private ImageIcon Start2 = new ImageIcon(Main.class.getResource("../images/Start2.png"));
    private ImageIcon Quit1 = new ImageIcon(Main.class.getResource("../images/Quit1.png"));
    private ImageIcon Quit2 = new ImageIcon(Main.class.getResource("../images/Quit2.png"));
    private ImageIcon Back1 = new ImageIcon(Main.class.getResource("../images/Back1.png"));
    private ImageIcon Back2 = new ImageIcon(Main.class.getResource("../images/Back2.png"));
    
    private JButton startButton = new JButton(Start2);
    private JButton quitButton = new JButton(Quit2);
    private JButton backButton = new JButton(Back2);
    
    private int mouseX, mouseY;
    
    private boolean isMainScreen = false;
    private boolean isGameScreen = false;
    
    public static Running running = new Running();
    
	public Game() {
    	setUndecorated(true);
        setTitle("Game");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(new Color(0,0,0,0));
        setLayout(null);
        
        addKeyListener(new Listener());
             
        menuBar.setBounds(0,0,1290,30);
        
        startButton.setBounds(465,250,310,130);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        
        quitButton.setBounds(465,400,310,130);
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusPainted(false);

        backButton.setBounds(5,30,50,50);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
                         
        add(menuBar);
        add(startButton);
        add(quitButton);
        add(backButton);
        

        intro();
    }
        
        public void intro() {
        	isMainScreen = true;
	    	isGameScreen = false;
	    	backButton.setVisible(false);
	 		startButton.setVisible(true);
			quitButton.setVisible(true);
			Background = new ImageIcon(Main.class.getResource("../images/background.jpg")).getImage();
			Music introMusic = new Music("city.mp3", true);
			introMusic.start();
			setFocusable(true);
	             
             
        	 menuBar.addMouseListener(new MouseAdapter(){
             	@Override
             	public void mousePressed (MouseEvent e) {
             		mouseX = e.getX();
             		mouseY = e.getY();
             		
             	}
             });
             menuBar.addMouseMotionListener(new MouseMotionAdapter() {
             	@Override
             	public void mouseDragged(MouseEvent e) {
             		int x = e.getXOnScreen();
             		int y = e.getYOnScreen();
             		setLocation(x - mouseX, y - mouseY);
             	}
             });
          
        
             startButton.addMouseListener(new MouseAdapter(){
             	@Override
             	public void mouseEntered (MouseEvent e) {
             		startButton.setIcon(Start1);
             	    startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
             		
             	}
             	@Override
             	public void mouseExited (MouseEvent e) {
             		startButton.setIcon(Start2);
             		startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
             		
             	}
             	@Override
             	public void mousePressed (MouseEvent e) {  
             		introMusic.interrupt();
             		introMusic.close();
             		gamestart();   
       
             	}
             	
             });
            
         
             quitButton.addMouseListener(new MouseAdapter(){
             	@Override
             	public void mouseEntered (MouseEvent e) {
             		quitButton.setIcon(Quit1);
             	    quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
             		
             	}
             	@Override
             	public void mouseExited (MouseEvent e) {
             		quitButton.setIcon(Quit2);
             		quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
             		
             	}
             	@Override
             	public void mousePressed (MouseEvent e) {
             		System.exit(0);
             	}
             	
             });
                          	
    }
    
   
	    @Override
	    public void paint(Graphics g) {
	        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
	        screenGraphic = screenImage.getGraphics();
	        screenDraw((Graphics2D) screenGraphic);
	        g.drawImage(this.screenImage, 0, 0, null);
	    }
	    
	    public void screenDraw(Graphics2D g) {
	        g.drawImage(this.Background, 0, 0, null);
	        
	        if(isGameScreen && !running.isEnd()) {
	        	running.screenDraw(g);       	        	
	        }
	        else if(running.isEnd()){
	        	endscreen(g);
	        }
	        
	        paintComponents(g);
	        try{
	        	Thread.sleep(5);
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
	        this.repaint();
	    }
	    public void gamestart() {
	    	isMainScreen = false;
	    	isGameScreen = true;
	    	backButton.setVisible(true);
	 		startButton.setVisible(false);
			quitButton.setVisible(false);
			Background = new ImageIcon(Main.class.getResource("../images/stage.jpg")).getImage();
			running.start();
			setFocusable(true);
			
					
		       backButton.addMouseListener(new MouseAdapter(){
	             	@Override
	             	public void mouseEntered (MouseEvent e) {
	             		backButton.setIcon(Back1);
	             		backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	             		
	             	}
	             	@Override
	             	public void mouseExited (MouseEvent e) {
	             		backButton.setIcon(Back2);
	             		backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	             		
	             	}
	             	@Override
	             	public void mousePressed (MouseEvent e) {
	             		System.exit(0);         		
	             	}
	             	
	             });
		             
				
		    }
	    public void endscreen(Graphics2D g) {
	    	isMainScreen = false;
	    	isGameScreen = false;
	    	backButton.setVisible(true);
	 		startButton.setVisible(false);
			quitButton.setVisible(false);
			g.setColor(Color.yellow);
			g.setFont(new Font("Elephant",Font.PLAIN, 40));
			g.drawString("Thanks for playing", 450, 300);
	    	g.drawString("Final score: " + String.valueOf(Main.SCORE), 450, 360);
	    	
			
	    	
	    }
	    }
