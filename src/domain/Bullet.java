package domain;

/**
 * 子弹类，实现线程接口，以自我移动
 * @author ZengYu
 *
 */
public class Bullet implements Runnable {
	private int x;
	private int y;
	private int direction;//子弹方向
	private int speed=1;//子弹速度
	private int isValid=0;  //子弹是否死亡，默认活着 1为死亡
	//构造函数
	public Bullet(int x, int y, int direction) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
	}



	@Override
	public void run() {
		System.out.println("调用了Bullet的run方法");

		while (true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//沿方向移动
			switch(this.getDirection()){
			case 0://上
				this.y-=speed;
				break;			
			case 1://左
				this.x-=speed;
				break;
			case 2://下
				this.y+=speed;
				break;
			case 3://右
				this.x+=speed;
				break;
			default:
				break;	
			
			}
			System.out.println("x"+x+",y"+y);
			System.out.println(speed);
			//子弹何时死亡
			if(this.x<=0||this.x>=400||this.y<=0||this.y>=400){
				this.isValid=1;
				break;
			}
		}
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



	public int getIsValid() {
		return isValid;
	}



	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}



}
