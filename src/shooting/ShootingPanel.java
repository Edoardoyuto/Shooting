//1116230005 伊達　エドアルド佑都
package shooting;

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;

public class ShootingPanel extends Panel {
	public BufferedImage image;
	
	ShootingPanel(){
		super();
		this.image=new BufferedImage(500,500,BufferedImage.TYPE_3BYTE_BGR);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(image != null) {
			g.drawImage(image,0,0,this);
		}
	}
	
	public void draw() {
		repaint();
	}

}
