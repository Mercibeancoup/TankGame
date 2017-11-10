/**
 * 定义坦克类
 * 
 * @author ZengYu
 * 
 */
public class Tank {
	private int x, y;// 定义坐标
	private int direction = 0;// 定义默认0，向上，1向左，2向下，3向右
	private int speed = 20;// 坦克速度

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
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

	// 坦克向下移动
	public void MoveDown() {
		this.setY(this.getY()+this.getSpeed());
		//y += speed;
		System.out.println("运行了Movedown方法");
	}

	// 坦克向上移动
	public void MoveUp() {
		this.setY(this.getY() - this.getSpeed());
		//y -= speed;
		System.out.println("运行了Moveup方法");
	}

	// 坦克向左移动
	public void MoveLeft() {
		this.setX(this.getX() - this.getSpeed());
		//x -= speed;
	}

	// 坦克向右移动
	public void MoveRight() {
		this.setX(this.getX() + this.getSpeed());
		//x += speed;
	}


}
