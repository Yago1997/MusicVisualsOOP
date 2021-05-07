///Yago Lacerda C19481114
package city_scape;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.ImageIcon;

public class Running extends Thread{
	
	private Image Gameinfo = new ImageIcon(Main.class.getResource("../images/Gameinfo.png")).getImage();
	private Image Judgementline = new ImageIcon(Main.class.getResource("../images/Judgementline.png")).getImage();
	private Image NotePathD = new ImageIcon(Main.class.getResource("../images/Path.png")).getImage();
	private Image NotePathF = new ImageIcon(Main.class.getResource("../images/Path.png")).getImage();
	private Image NotePathJ = new ImageIcon(Main.class.getResource("../images/Path.png")).getImage();
	private Image NotePathK = new ImageIcon(Main.class.getResource("../images/Path.png")).getImage();
	
	private boolean isend = false;
	
	ArrayList<Note> noteList = new ArrayList<Note>();	

	public void screenDraw(Graphics2D g) {		
    	g.drawImage(Gameinfo, 0, 600, null);
    	g.drawImage(Judgementline, 0, 600, null);
    	g.drawImage(Gameinfo, 750, 600, null);
    	g.drawImage(Judgementline, 750, 600, null);
    	g.drawImage(NotePathD, 260, -70, null);
    	g.drawImage(NotePathF, 460, -70, null);
    	g.drawImage(NotePathJ, 660, -70, null);
    	g.drawImage(NotePathK, 860, -70, null);
      	for(int i=0; i<noteList.size(); i++)
    	{
    		Note Note = noteList.get(i);
    		if(!Note.isProceeded()) {
    			noteList.remove(i);
    			i--;
    		}
    		else {
    			Note.screenDraw(g);
    		}
    	}
    	g.setColor(Color.yellow);
    	g.setFont(new Font("Elephant",Font.PLAIN, 40));
    	g.drawString(String.valueOf(Main.SCORE), 10, 700);
    	g.drawString(String.valueOf(Main.indicator), 10, 590);
    	g.drawString("D", 315, 700);
    	g.drawString("F", 515, 700);
    	g.drawString("J", 715, 700);
    	g.drawString("K", 915, 700);
    	g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	}
	
	public void pressD() {
		judge("D");
		NotePathD = new ImageIcon(Main.class.getResource("../images/PathPress.png")).getImage(); 
		
	}
	public void releaseD() {
		NotePathD = new ImageIcon(Main.class.getResource("../images/Path.png")).getImage(); 
	}
	public void pressF() {
		judge("F");
		NotePathF = new ImageIcon(Main.class.getResource("../images/PathPress.png")).getImage(); 
	}
	public void releaseF() {
		NotePathF = new ImageIcon(Main.class.getResource("../images/Path.png")).getImage(); 
		
	}
	public void pressJ() {
		judge("J");
		NotePathJ = new ImageIcon(Main.class.getResource("../images/PathPress.png")).getImage(); 
		//new Music("SoundPress.mp3", false).start();
		
	}
	public void releaseJ() {
		NotePathJ = new ImageIcon(Main.class.getResource("../images/Path.png")).getImage(); 
		
	}
	public void pressK() {
		judge("K");
		NotePathK = new ImageIcon(Main.class.getResource("../images/PathPress.png")).getImage(); 
		//new Music("SoundPress.mp3", false).start();
		
	}
	public void releaseK() {
		NotePathK = new ImageIcon(Main.class.getResource("../images/Path.png")).getImage(); 
		
	}
	
	@Override
	public void run() {
		dropNotes();	
	}
	
	public void close() {
		this.interrupt();
	}
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	public void dropNotes() {
		Beat[] beats = new Beat[650];
		int startTime = 2000 - Main.REACH * 1000;
		int gap = 25;
		int j;
		int x = 0;
		String n = "D";
		for(j=0;j<601;j++) {
			x +=20;
			beats[j]=new Beat(startTime+ gap*x,n);
			int number = getRandomNumber(1,650);
			if(number<200) {
				n = "F";		
			}
			else if(number<400) {
				n = "J";
			}
			else if(number<600) {
				n = "K";
			}
			else if(number<800) {
				n = "D";
			}
		}
		int i = 0;
		Music stageMusic = new Music("nightwalker.mp3", false);
		stageMusic.start();
		while(i < beats.length && !isInterrupted()&&!isend) {
			boolean dropped = false;
			if(beats[i].getTime() <= Music.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if(!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		if(i==601) {
				isend = true;
				System.out.println("s");
				}
		}

	}
	
	public void judge(String input) {
		for (int i = 0; i< noteList.size(); i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				note.judge();
				break;
			}
		}
	}
	public boolean isEnd(){
		return isend;
	}
}
