import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

public class Brick extends Sprite
{
	static Image[] brick_images =null;
	

	Brick(int _x, int _y, int _w, int _h, Model m)
	{
		super(m);
		x = _x;
		y = _y;
		w = _w;
		h = _h;
		brick_images = new Image[1];
		jsonobjtype = "Brick";
		try
		{

			brick_images[0] = ImageIO.read(new File("brick.jpg"));
		} 
		catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
	//Copy Constructor
	Brick(Brick that, Model mod)
	{
		super(that,mod);
		this.x = that.x;
		this.y = that.y;
		this.w = that.w;
		this.h = that.h;
		this.jsonobjtype = that.jsonobjtype;
	}


	Brick(Json ob, Model m)
	{
		super(m);
		brick_images = new Image[1];
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = (int)ob.getLong("w");
        h = (int)ob.getLong("h");

		try
		{

			brick_images[0] = ImageIO.read(new File("brick.jpg"));
		} 
		catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
        	

	}
	Brick cloneme(Model m){
		return new Brick(this, m);
	}
	void update(Model m){
	}
	void draw(Graphics g){
		g.drawImage(brick_images[0],x - model.scrollPos, y, w, h, null);
	}
 	
 	Json marshall(){
 		  Json ob = Json.newObject();
 		  ob.add("type", jsonobjtype);
          ob.add("x", x);
          ob.add("y", y);
          ob.add("w", w);
          ob.add("h", h);

          return ob;
 	}

	boolean isBrick(){
		//System.out.println("isBrick works in brick");
		return true;
	}
	
}