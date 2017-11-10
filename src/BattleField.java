import javax.swing.*;
import java.awt.*;

/**
 * 战场
 * @author ZengYu
 *
 */
public class BattleField extends JFrame{

	TankPanel htp= null;
	public BattleField() {
		//设置组件
		htp= new TankPanel();
		
		//设置布局
		//添加组件
		this.add(htp);
		
		//注册监听
		this.addKeyListener(htp);
		//设置窗体
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setLocation(400, 200);
	}
	public static void main(String[] args) {
		BattleField bf= new BattleField();

	}
	
}
