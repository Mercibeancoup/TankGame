package domain;

/**
 * 爆炸效果类
 * @author ZengYu
 *
 */
public class ExplosionEffect {
	private int x;//坐标x
	private int y;//坐标y
	private int lifeTime=9;//生命周期
	private int isValid=0;//是否有效
	
	public ExplosionEffect(int x, int y) {
		this.x = x;
		this.y = y;
	}
	//生命减小函数
	public void lifeDown(){
		if(lifeTime>=0){
			this.lifeTime--;
		}
		else {
			this.isValid=1;
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
	public int getLifeTime() {
		return lifeTime;
	}
	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
	}
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}
	

}
