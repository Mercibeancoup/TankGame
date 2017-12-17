package domain;

public class EmeryTank extends Tank implements Runnable {

	public EmeryTank(int x, int y) {
		super(x, y);
		this.setType(1);
	}

	@Override
	public void run() {
		while (true) {

			// 根据不同方向进行移动
			switch (this.getDirection()) {
			case 0:
				// 利用变量来进行多次的移动
				for (int i = 0; i < 30; i++) {
					// 判断坦克是否越界
					if (this.getY() > 0) {
						this.setY(this.getY() - this.getSpeed());
					}

					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				break;
			case 1:
				for (int i = 0; i < 30; i++) {
					if (this.getX() > 0) {
						this.setX(this.getX() - this.getSpeed());
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) { 
						e.printStackTrace();
					}

				}
				break;
			case 2:
				for (int i = 0; i < 20; i++) {
					if (this.getY() < 400) {
						this.setY(this.getY() + this.getSpeed());
					}
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 3:
				for (int i = 0; i < 30; i++) {
					if (this.getX() < 400) {
						this.setY(this.getX() + this.getSpeed());
					}
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			}

			// 随机生成方向
			int direct = (int) (Math.random() * 4);
			this.setDirection(direct);

			// 当坦克死亡时，跳出循环
			if (this.getIsValid() == 1) {
				break;
			}
		}
	}

}
