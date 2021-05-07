///Yago Lacerda C19481114

package city_scape;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	
	private Image note = new ImageIcon(Main.class.getResource("../images/Note.png")).getImage();
	private int x,y = 580 - (1000 / Main.DELAY * Main.SPEED) * Main.REACH;
	private String noteType;
	private boolean proceeded = true;
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() {
		proceeded = false;
	}
	
	public Note (String noteType) {		
		if(noteType.equals("D")){
			x = 250;
		}
		else if(noteType.equals("F")) {
			x = 450;
		}
		else if(noteType.equals("J")) {
			x = 650;		
				}
		else if(noteType.equals("K")) {
			x = 850;
		}
		this.noteType= noteType;

	}
	
	public void screenDraw(Graphics2D g) {
		
		g.drawImage(note, x, y, null);
		}
			
	
	
	public void drop() {
		y+= Main.SPEED;
		if (y>620) {
			Main.indicator="Miss";
			Main.SCORE -= 20;
			close();
		}
		
	}
	
	@Override
	public void run() {
		try{
			while(true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.DELAY);
				}
				else {
					interrupt();
					break;
				}
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public void judge() {
		if(y >=613) {
			Main.indicator="Late";
			Main.SCORE -= 20;
			close();
		}
		else if (y<535) {
			Main.indicator="Miss";
			Main.SCORE -= 20;
			close();
		}
		else if(y >= 600){
			Main.indicator="Good";
			close();
		}
		else if(y >= 587){
			Main.indicator="Great";
			Main.SCORE += 25;
			close();
		}
		else if(y >= 573){
			Main.indicator="Perfect";
			Main.SCORE += 50;
			close();
		}
		else if(y >= 565){
			Main.indicator="Great";
			Main.SCORE += 25;
			close();
		}
		else if(y >= 550){
			Main.indicator="Good";
			Main.SCORE += 10;
			close();
		}
		else if(y >= 535){
			Main.indicator=("Early");
			Main.SCORE -= 20;
			close();
		}
	}
}

