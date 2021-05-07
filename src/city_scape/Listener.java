///Yago Lacerda C19481114
package city_scape;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Listener extends KeyAdapter{
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(Game.running == null) {
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_D ){		
			Game.running.pressD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F ) {
			Game.running.pressF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J ) {
			Game.running.pressJ();		
				}
		else if(e.getKeyCode() == KeyEvent.VK_K ) {
			Game.running.pressK();
		}

	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(Game.running == null) {
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_D ){		
			Game.running.releaseD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F ) {
			Game.running.releaseF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J ) {
			Game.running.releaseJ();		
				}
		else if(e.getKeyCode() == KeyEvent.VK_K ) {
			Game.running.releaseK();
		}
	}
}


