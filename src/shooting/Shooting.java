//1116230005 伊達　エドアルド佑都

package shooting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Shooting implements ClickListener{
	public static ShootingFrame shootingFrame;
	public static boolean loop = true;
	public static int FPS = 0; 
	public static int bulletInterval=100;
	public static int clickX;
	public static int clickY;

	
	public static void main(String[] args) {
		
		shootingFrame = new ShootingFrame(new Shooting());
 		
		Graphics gra = shootingFrame.panel.image.getGraphics();
		
		
		//FPS
		int enemyPoint = 10;
		long startTime; 
		long fpsTime = 0;
		int fps = 30; 
		int fpsCount = 0;
		long bulletTime = 0;
		int score = 0;
		
		
		EnumShootingScreen screen = EnumShootingScreen.START;
		
		Point player = new Point(250,400);
		ArrayList<Point> playerBullet= new ArrayList<>();
		ArrayList<Point> enemyBullet= new ArrayList<>();
		ArrayList<Point> enemy = new ArrayList<>();
		
		while(loop) {
			if(System.currentTimeMillis()-fpsTime>1000) {
				fpsTime=System.currentTimeMillis();
				FPS=fpsCount;
				fpsCount=0;
				shootingFrame.label.setText("FPS:"+Shooting.FPS+"");
			}
			fpsCount++;
			startTime = System.currentTimeMillis();
			
			
			gra.setColor(Color.white);
			gra.fillRect(0, 0,500,500);
			
			switch(screen) {
			case START: 
				gra.setColor(Color.BLACK);
				gra.fillRect(0, 0, 500, 500);
				gra.setColor(Color.WHITE);
				gra.setFont(new Font("Serif", Font.BOLD, 50));
			    gra.setColor(Color.orange);
			    gra.drawString("SPACE INVEDARS", 30, 250);
			    gra.setFont(new Font("Serif", Font.PLAIN, 25));
				gra.drawString("press SPACE to Start ",250,300);
				if(KeyBoard.isKeyPressed(KeyEvent.VK_SPACE)) {
					screen = EnumShootingScreen.GAME;
				}
				break;
			case GAME:
				
				 gra.setColor(Color.BLACK);
				 gra.fillRect(0, 0, 500, 500);
				
				gra.setColor(Color.CYAN);
				gra.fillRect((int)player.getX(),(int)player.getY(),30,10);
				gra.fillRect((int)player.getX()+10,(int)player.getY()-10,10,10);
				if(KeyBoard.isKeyPressed(KeyEvent.VK_LEFT)&&player.getX()>0)player.translate(-7,0);
				if(KeyBoard.isKeyPressed(KeyEvent.VK_RIGHT)&&player.getX()<460)player.translate(7,0);
				if(KeyBoard.isKeyPressed(KeyEvent.VK_UP)&&player.getY()>0)player.translate(0,-5);
				if(KeyBoard.isKeyPressed(KeyEvent.VK_DOWN)&&player.getY()<440)player.translate(0,7);
				
				if(KeyBoard.isKeyPressed(KeyEvent.VK_SPACE)&&System.currentTimeMillis()-bulletTime>bulletInterval) {
					playerBullet.add(new Point((int)player.getX()+12,(int)player.getY()));
					bulletTime = System.currentTimeMillis();
				}
				
				Random rand = new Random();
				if(rand.nextInt(15)==0) {
					enemy.add(new Point(rand.nextInt(460),0));
				}
				
				for(int i=0;i<playerBullet.size();i++) {
					Point point = playerBullet.get(i);
					gra.drawOval((int)point.getX(),(int)point.getY(),3,3);
					playerBullet.set(i, new Point((int)point.getX(),(int)point.getY()-7));
					if(playerBullet.get(i).getY()<0)playerBullet.remove(i);
					for(int j=0;j<enemy.size();j++) {
						Point Epoint = enemy.get(j);
						if(Epoint.getX()<point.getX()&&point.getX()<Epoint.getX()+30
								&&Epoint.getY()<point.getY()&&point.getY()<Epoint.getY()+20) {
							enemy.remove(j);
							score+=enemyPoint;
						}
						
					}
				
				}
				
				
				for(int i=0;i<enemy.size();i++) {
					gra.setColor(Color.red);
					gra.fillRect((int)enemy.get(i).getX(),(int)enemy.get(i).getY(),30,10);
					gra.fillRect((int)enemy.get(i).getX()+10,(int)enemy.get(i).getY()+10,10,10);
					Point point = enemy.get(i);
					enemy.set(i, new Point((int)point.getX(),(int)point.getY()+5));
					if(rand.nextInt(14)==0)enemyBullet.add(new Point((int)point.getX()+12,(int)point.getY()));
					if(enemy.get(i).getY()>500)enemy.remove(i);
					
				}
				System.out.println(enemy.size());
				
				for(int i=0;i<enemyBullet.size();i++) {
					Point point = enemyBullet.get(i);
					gra.drawOval((int)point.getX(),(int)point.getY(),3,3);
					enemyBullet.set(i, new Point((int)point.getX(),(int)point.getY()+9));
					
					if(player.getY()<=enemyBullet.get(i).getY()&&enemyBullet.get(i).getY()<=player.getY()+10) {
						if(player.getX()<=enemyBullet.get(i).getX()&&enemyBullet.get(i).getX()<=player.getX()+30) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO 自動生成された catch ブロック
								e.printStackTrace();
							}
							screen = EnumShootingScreen.GAMEOVER;  
							
							
						}
						
					}
					if(enemyBullet.get(i).getY()>500)enemyBullet.remove(i);
				}
				gra.setColor(Color.CYAN);
				gra.drawString("スコア："+score,340,430);
				
				
				break;
			case GAMEOVER:
		
			    gra.setColor(Color.BLACK);
			    gra.fillRect(0, 0, 500, 500);

			    gra.setFont(new Font("Serif", Font.BOLD, 50));
			    gra.setColor(Color.RED);
			    gra.drawString("Game Over!!!", 100, 250);
			    gra.setFont(new Font("Serif", Font.PLAIN, 20));
			    gra.setColor(Color.RED);
			    gra.drawString("score: "+score+" !!", 200, 300);

			
			    if ((System.currentTimeMillis() / 500) % 2 == 0) {
			        gra.setFont(new Font("Serif", Font.PLAIN, 20));
			        gra.setColor(Color.YELLOW);
			        gra.drawString("click HERE to Restart", 150, 340);
			        gra.drawString("click HERE to Quit", 150, 365);
			      
			    }

			   
			    Random rand1 = new Random();
			    for (int i = 0; i < 50; i++) {
			        gra.setColor(new Color(rand1.nextInt(256), rand1.nextInt(256), rand1.nextInt(256))); 
			        int x = rand1.nextInt(500);
			        int y = rand1.nextInt(500);
			        gra.fillOval(x, y, 5, 5); 
			    }
			    
			    if(193<clickX&&clickX<325) {
			    	if(245<clickY&&clickY<340) {
			    		player = new Point(250,400);
			    		playerBullet= new ArrayList<>();
			    		enemyBullet= new ArrayList<>();
			    		enemy = new ArrayList<>();
			    		screen = EnumShootingScreen.GAME;
			    		shootingFrame.requestFocus();
			    		clickX = 0;
			    		clickY = 0;
			    	}
			    if(193<clickX&&clickX<325) {
				    if(350<clickY&&clickY<365) {
				    	System.exit(0);
				    }
				    
				 }
			    }

			    break;
			}
			
			shootingFrame.panel.draw();
			  
			try {
				long runTime = System.currentTimeMillis()-startTime;
				if(runTime<1000/fps) {
					Thread.sleep((1000/fps)-(System.currentTimeMillis()-startTime));
				}
				
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} 
		}
	}


	@Override
	public void onClick(int x, int y) {
		// TODO 自動生成されたメソッド・スタブ
		Shooting.clickX = x;
		Shooting.clickY = y;
		System.out.println("clicked at (" + clickX + ", " + clickY + ")");
	}
	
}
