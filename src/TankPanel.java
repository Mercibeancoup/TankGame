import javax.imageio.ImageIO;
import javax.swing.*;

import domain.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Vector;

/*
 * 1.我方坦克和敌方可以自由移动
 * 2.我方坦克和敌方坦克 可以发射子弹，
 * 3.限定我方坦克和敌方坦克的运动范围
 * */
public class TankPanel extends JPanel implements KeyListener, Runnable {
	HeroTank herotank = null;// 我方坦克
	Vector<EmeryTank> emerytanks = new Vector<EmeryTank>();// 敌方坦克
	Vector<ExplosionEffect> effects = new Vector<ExplosionEffect>();// 爆炸效果

	// 初始化爆炸效果的三张图片，组合成一个爆炸效果
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;

	public TankPanel()  {
		//初始化我方坦克
		herotank = new HeroTank(100, 100);
		//初始化敌方坦克
		for (int i = 0; i < 3; i++) {
			EmeryTank et = new EmeryTank(i * 50, 0);
			et.setDirection(2);
			emerytanks.add(et);

			// 启动坦克线程
			Thread emeryTankThread = new Thread(et);
			emeryTankThread.start();
		}

		// 定义图片路径
		try {
			image1=ImageIO.read(Panel.class.getResource("/pic/bomb_1.gif"));
			image2=ImageIO.read(Panel.class.getResource("/pic/bomb_2.gif"));
			image3=ImageIO.read(Panel.class.getResource("/pic/bomb_3.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	// 重写paint函数
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fill3DRect(0, 0, 400, 400, false);
		// 画出我方坦克
		this.drawTank(this.herotank.getX(), this.herotank.getY(), g,
				this.herotank.getDirection(), this.herotank.getType());

		// 画出敌人坦克
		for (int i = 0; i < emerytanks.size(); i++) {
			EmeryTank emeryTank = emerytanks.get(i);
			// 敌方坦克有效则进行绘制
			if (emeryTank.getIsValid() == 0) {
				this.drawTank(emeryTank.getX(), emeryTank.getY(), g,
						emeryTank.getDirection(), emeryTank.getType());
			}
			//若是坦克失效，则移除该坦克
			if (emeryTank.getIsValid() == 1) {
				this.emerytanks.remove(i);
			}
		}

		// 画出我方坦克子弹 支持连发
		for (int i = 0; i < this.herotank.getBullets().size(); i++) {
			Bullet bullet = this.herotank.getBullets().get(i);
			if (bullet != null && bullet.getIsValid() == 0) {
				g.setColor(Color.white);
				g.draw3DRect(bullet.getX(), bullet.getY(), 2, 2, false);
			}
			// 子弹删除
			if (bullet.getIsValid() == 1) {
				this.herotank.getBullets().remove(bullet);
			}
		}
		

		// 画出炸弹效果
		for (int i = 0; i < effects.size(); i++) {

			System.out.println("炸彈總量" + effects.size());
			ExplosionEffect effect = effects.get(i);
			// System.out.println(effect.getX());
			// 根据爆炸效果的生命周期，替换爆炸效果图片
			try {
				if (effect.getLifeTime() > 7) {
					g.drawImage(image1, effect.getX(), effect.getY(), 30, 30,
							this);
				} else if (effect.getLifeTime() > 5) {
					g.drawImage(image2, effect.getX(), effect.getY(), 30, 30,
							this);
				} else {
					g.drawImage(image3, effect.getX(), effect.getY(), 30, 30,
							this);
				}
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
			// 调用减生命值函数
			effect.lifeDown();

			// 如果生命周期降为零，则移除该爆炸效果
			if (effect.getLifeTime() == 0) {
				effects.remove(effect);
			}

		}
	}

	
	/**
	 *  封装绘制坦克的函数 
	 * @param x
	 *            坦克坐标x
	 * @param y
	 *            坦克坐标y
	 * @param g
	 *            g 必须有
	 * @param direction
	 *            坦克方向
	 * @param type
	 *            坦克类型
	 */
	public void drawTank(int x, int y, Graphics g, int direction, int type) {
		// 根據坦克的種類繪製不同的顔色
		switch (type) {
		// 我方坦克
		case 0:
			g.setColor(Color.green);
			break;
		// 敌方坦克
		case 1:
			g.setColor(Color.red);
			break;
		default:
			g.setColor(Color.pink);
			break;
		}

		// 根據不同的方向 画出坦克
		switch (direction) {
		// 向上
		case 0:
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
		// 向左
		case 1:
			// 画出左边矩形
			g.fill3DRect(x, y, 30, 5, false);
			// 画出右边矩形
			g.fill3DRect(x, y + 15, 30, 5, false);
			// 画出中间矩形
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			// 画出圆形
			g.fillOval(x + 10, y + 5, 10, 10);
			// 画出线条
			g.drawLine(x + 15, y + 10, x, y + 10);
			break;
		// 向下
		case 2:
			// 画出左边矩形
			g.fill3DRect(x, y, 5, 30, false);
			// 画出右边矩形
			g.fill3DRect(x + 15, y, 5, 30, false);
			// 画出中间矩形
			g.fill3DRect(x + 5, y + 5, 10, 20, false);
			// 画出圆形
			g.fillOval(x + 5, y + 10, 10, 10);
			// 画出线条
			g.drawLine(x + 10, y + 15, x + 10, y + 30);
			break;
		// 向右
		case 3:
			// 画出左边矩形
			g.fill3DRect(x, y, 30, 5, false);
			// 画出右边矩形
			g.fill3DRect(x, y + 15, 30, 5, false);
			// 画出中间矩形
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			// 画出圆形
			g.fillOval(x + 10, y + 5, 10, 10);
			// 画出线条
			g.drawLine(x + 15, y + 10, x + 30, y + 10);
			break;
		default:
			g.draw3DRect(x, y, 20, 20, false);
			break;
		}
	}

	/**
	 * 更新坦克状态生存状态，判断子弹是否击中坦克
	 * 
	 * @param tank
	 *            坦克
	 * @param bullet
	 *            子弹
	 */
	public void updateTankStatus(Tank tank, Bullet bullet) {
		if (bullet.getIsValid() == 0 && tank.getIsValid() == 0) {
			int dir = tank.getDirection();
			int x = bullet.getX();
			int y = bullet.getY();

			switch (dir) {
			case 0:
			case 2:
				if (x >= tank.getX() && x <= (tank.getX() + 20)
						&& y >= tank.getY() && y <= tank.getY() + 30) {
					bullet.setIsValid(1);
					tank.setIsValid(1);

					// 添加爆炸效果类
					ExplosionEffect effect = new ExplosionEffect(tank.getX(),
							tank.getY());
					// 在爆炸效果类添加爆炸效果
					effects.add(effect);
				}
				break;
			case 1:
			case 3:
				if (x >= tank.getX() && x <= tank.getX() + 30
						&& y < tank.getY() && y >= tank.getY() + 20) {
					bullet.setIsValid(1);
					tank.setIsValid(1);

					// 添加爆炸效果类
					ExplosionEffect effect = new ExplosionEffect(tank.getX(),
							tank.getY());
					// 在爆炸效果类添加爆炸效果
					effects.add(effect);
				}
				break;
			}
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
		if (e.getKeyCode() == KeyEvent.VK_W) { // W：向上
			this.herotank.setDirection(0);
			this.herotank.MoveUp();
			// System.out.println("点击了w");
		} else if (e.getKeyCode() == KeyEvent.VK_A) {// A:向左
			this.herotank.setDirection(1);
			this.herotank.MoveLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {// S：向下
			this.herotank.setDirection(2);
			this.herotank.MoveDown();
			;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {// D：向右
			this.herotank.setDirection(3);
			this.herotank.MoveRight();
		}

		// 点击了J键之后，进行攻击
		if (e.getKeyCode() == KeyEvent.VK_J) {
			// 如果存在的子弹数少于五个
			if (this.herotank.getBullets().size() <= 4) {
				this.herotank.attackEmery();
			}

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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("调用了TankPanel的run方法");
		while (true) {
			// 每隔100ms重画
			// 想要每个一段时间就就进行重绘，就需要将TankPanel变成一个线程，每隔一段时间去执行
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 更新敌方坦克状态
			for (int i = 0; i < herotank.getBullets().size(); i++) {
				Bullet heroBullet = herotank.getBullets().get(i);
				for (int j = 0; j < emerytanks.size(); j++) {
					Tank emeryTank = emerytanks.get(j);
					updateTankStatus(emeryTank, heroBullet);
				}
			}

			// 更新我方坦克状态
			/*
			 * for (int i = 0; i < emerytanks.size(); i++) { EmeryTank
			 * emeryTank= emerytanks.get(i); for (int j = 0; j
			 * <emeryTank.getBullets().size() ; j++) { Bullet
			 * emeryBullet=emeryTank.getBullets().get(i); updateTankStatus(tank,
			 * bullet); } }
			 */
			this.repaint();
		}
	}

}
