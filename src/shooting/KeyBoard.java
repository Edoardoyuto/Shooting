//1116230005 伊達　エドアルド佑都

package shooting;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyBoard extends KeyAdapter{
	private static ArrayList<Integer> pressedButtons;
	KeyBoard(){
		pressedButtons = new ArrayList<>();
	}
	
	public static boolean isKeyPressed(int KeyCode) {
		return pressedButtons.contains(KeyCode);  
	}

	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		
		if(!pressedButtons.contains(e.getKeyCode())){
			pressedButtons.add(e.getKeyCode());
		}
	}
	
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		pressedButtons.remove((Integer)e.getKeyCode());
	}
	
}
