package domain;

import java.util.Vector;

/**
 * 定义坦克类
 * 
 * @author ZengYu
 * 
 */
public class Tank {
	private int x, y;// 定义坐标
	private int direction = 0;// 定义默认0，向上，1向左，2向下，3向右
	private int speed = 10;// 坦克速度
	private int type = 0;// 坦克类型
	//private boolean isLive=0;//坦克是否活着 ，默认为
	private int isValid=0; //坦克是否死亡，默认0：活着 1为死亡
	
	
	private Bullet bullet = null;
	private Vector<Bullet> bullets = new Vector<Bullet>();// 子彈的集合

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 攻击敌人方法,根据坦克方向生成子弹 2.可以连续攻击出多个子弹
	 */
	public void attackEmery() {
		switch (this.direction) {
		case 0:
			// 上
			bullet = new Bullet(x + 10, y, direction);
			bullets.add(bullet);
			break;
		case 1:
			// 左
			bullet = new Bullet(x, y + 10, direction);
			bullets.add(bullet);
			break;
		case 2:
			// 下
			bullet = new Bullet(x + 10, y + 30, direction);
			bullets.add(bullet);
			break;
		case 3:
			// 右
			bullet = new Bullet(x + 30, y + 10, direction);
			bullets.add(bullet);
			break;
		}

		// 创建子弹线程
		Thread bulletThread = new Thread(bullet);
		bulletThread.start();
	}

	// 坦克向下移动
	public void MoveDown() {
		System.out.print("调用了向下移动方法");
		if (this.getY() >= 330) {// 不能超过游戏 范围
			// this.setX(this.getX());
			this.setY(330);
		} else {
			this.setY(this.getY() + this.getSpeed());
		}
		System.out.println(this.getY());

		// y += speed;
		// System.out.println("运行了Movedown方法");
	}

	// 坦克向上移动
	public void MoveUp() {
		if (this.getY() <= 0) {
			this.setY(0);
		} else {
			this.setY(this.getY() - this.getSpeed());
		}
		// y -= speed;
		// System.out.println("运行了Moveup方法");
	}

	// 坦克向左移动
	public void MoveLeft() {
		if (this.getX() <= 0) {
			this.setX(0);
		} else {
			this.setX(this.getX() - this.getSpeed());
		}
		// x -= speed;
	}

	// 坦克向右移动
	public void MoveRight() {
		if (this.getX() >= 350) {
			this.setX(350);
		} else {
			this.setX(this.getX() + this.getSpeed());
		}
		// x += speed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Vector<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(Vector<Bullet> bullets) {
		this.bullets = bullets;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}



	
	

}
