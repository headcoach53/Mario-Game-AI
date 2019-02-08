import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;


abstract class Sprite
{

	int x;
	int y;
	int w ;
	int h ;
	Model model;
	int vert_vel ;
	int hor_vel ;

	String jsonobjtype;
	
	Sprite(Model m){
		model = m;
	}

	
	//Copy Constructor Deep Copy
	Sprite(Sprite that, Model mod)
	{
		model = mod;
	}
	abstract void draw(Graphics g);
	abstract void update(Model m);
	abstract Json marshall();
	abstract Sprite cloneme(Model m);
	

	boolean doesCollide(Sprite that)
	{
		if(this.x + this.w <= that.x)
			return false;
		if(this.x >= that.x + that.w)
			return false;
		if(this.y + this.h < that.y)
			return false;
		if(this.y > that.y + that.h)
			return false;
		return true;
	}	





	boolean isBrick(){
		//System.out.println("isBrick works sprite");
		return false;

	}
	boolean isMario(){
		//System.out.println("isMario works sprite");
		return false;

	}
	boolean isCoinBlock(){
		//System.out.println("isCoinblock boolen sprite works");
		return false;
	}

	
}
