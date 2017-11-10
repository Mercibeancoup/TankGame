import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class TankPanel extends JPanel implements KeyListener {
	HeroTank herotank = null;
	Vector<EmeryTank> emerytanks=new Vector<EmeryTank>();
	public TankPanel() {
		herotank = new HeroTank(100, 100);
		for(int i=0;i<3;i++){
			EmeryTank et= new EmeryTank(i*50, 0); 
			et.setDirection(2);
		
			emerytanks.add(et);
		}
		
	}

	// 重写paint函数
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//画出我方坦克
		this.drawTank(this.herotank.getX(), this.herotank.getY(), g,
				this.herotank.getDirection(), 0);
		//画出敌人坦克
		for(int i=0;i<emerytanks.size();i++){
			this.drawTank(emerytanks.get(i).getX(), emerytanks.get(i).getY(), g, emerytanks.get(i).getDirection(), 1);
		}
		

	}

	// 封装画坦克的函数
	public void drawTank(int x, int y, Graphics g, int direction, int type) {
		// 画出坦克

		switch (type) {
		// 我方坦克
		case 0:
			g.setColor(Color.green);
			break;
		//
		case 1:
			g.setColor(Color.red);
			break;
		}
		switch (direction) {
		//向上
		case 0:
			// 设置颜色
			g.setColor(Color.green);
			// 画出左边矩形
			g.fill3DRect(x, y, 5, 30, false);
			// 画出右边矩形
			g.fill3DRect(x + 15, y, 5, 30, false);
			// 画出中间矩形
			g.fill3DRect(x + 5, y + 5, 10, 20, false);
			// 画出圆形
			g.fillOval(x + 5, y + 10, 10, 10);
			// 画出线条
			g.drawLine(x + 10, y + 15, x + 10, y);
			break;
		//向左
		case 1:
			// 设置颜色
			g.setColor(Color.green);
			// 画出左边矩形
			g.fill3DRect(x, y, 30, 5, false);
			// 画出右边矩形
			g.fill3DRect(x , y+ 15, 30, 5, false);
			// 画出中间矩形
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			// 画出圆形
			g.fillOval(x + 10, y + 5, 10, 10);
			// 画出线条
			g.drawLine(x + 15, y + 10, x , y+10);
			break;
		//向下	
		case 2:
			// 设置颜色
			g.setColor(Color.green);
			// 画出左边矩形
			g.fill3DRect(x, y, 5, 30, false);
			// 画出右边矩形
			g.fill3DRect(x + 15, y, 5, 30, false);
			// 画出中间矩形
			g.fill3DRect(x + 5, y + 5, 10, 20, false);
			// 画出圆形
			g.fillOval(x + 5, y + 10, 10, 10);
			// 画出线条
			g.drawLine(x + 10, y + 15, x + 10, y+30);
			break;
		//向右
		case 3:
			// 设置颜色
			g.setColor(Color.green);
			// 画出左边矩形
			g.fill3DRect(x, y, 30, 5, false);
			// 画出右边矩形
			g.fill3DRect(x , y+ 15, 30, 5, false);
			// 画出中间矩形
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			// 画出圆形
			g.fillOval(x + 10, y + 5, 10, 10);
			// 画出线条
			g.drawLine(x + 15, y + 10, x + 30, y+10);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	// 键按下得处理 a:向左 s:向下 d：向右，w向右
	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println("X:"+this.herotank.getX()+"Y:"+this.herotank.y);

		// 对不同按钮点击事件设置坦克方向
		// W:向上
		if (e.getKeyCode() == KeyEvent.VK_W) {
			this.herotank.setDirection(0);
			this.herotank.MoveUp();
			System.out.println("点击了w");
		} else if (e.getKeyCode() == KeyEvent.VK_A) {// A:向左
			this.herotank.setDirection(1);
			this.herotank.MoveLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {// D：向右
			this.herotank.setDirection(2);
			this.herotank.MoveDown();
			;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {//
			this.herotank.setDirection(3);
			this.herotank.MoveRight();
		}

		// 必须重新绘制
		this.repaint();

		// System.out.println("X:"+this.herotank.x+"Y:"+this.herotank.y);
		// 重新绘制结束
		System.out.println("重新绘制结束");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
