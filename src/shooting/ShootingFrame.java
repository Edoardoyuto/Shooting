//1116230005 伊達　エドアルド佑都

package shooting;
 
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class ShootingFrame extends Frame implements WindowListener,MouseListener {
	
	public ShootingPanel panel;
	public Label label;
	private ClickListener clickListener;
	
	public ShootingFrame(ClickListener listener){
		this.clickListener = listener;
		this.addKeyListener(new KeyBoard());
		
		panel = new ShootingPanel();
		panel.addMouseListener(this);
		this.add(panel);
		
		this.setTitle("ShootingGame 1116230005");
		this.setSize (500,500);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		addWindowListener(this);
	
		
		label = new Label();
		label.setFont(new Font("serif",Font.ITALIC,15));
		add(label,BorderLayout.SOUTH);
		setFocusable(true);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		int clickX = e.getX();
        int clickY = e.getY();
        System.out.println("qqqqqqqqqqqqqqqq"); 
        if (clickListener != null) {
            clickListener.onClick(clickX, clickY);            
        }
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("Window closing");
		Shooting.loop=false;
		System.exit(0);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}



}
