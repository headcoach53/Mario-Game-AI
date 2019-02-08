import java.util.Iterator;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
//import java.util.Random;
class Coin extends Sprite
{

static Random r = new Random(0);
int random = r.nextInt(30) - 15;

 static Image[] coin_images = null;

	Coin(int _x, int _y, int hor_vel, int vert_vel ,Model m)
	{
		super(m);
		x = _x;
		y= _y;
 
		jsonobjtype = "Coin";

		if(coin_images == null){
		coin_images = new Image[1];

		try
		{		
			coin_images[0] = ImageIO.read(new File("coin.png"));
		} 
		catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}



	}
	Coin(Coin that, Model mod)
	{
		super(that, mod);
		//System.out.println("Coin Copy Works");
		this.random = that.random;
		
	}

	Coin(Json ob,Model m)
	{
		super(m);
		jsonobjtype = (String)ob.getString("type");
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");

		if(coin_images == null){
		coin_images = new Image[1];

		try
		{
			coin_images[0] = ImageIO.read(new File("coin.png"));
		} 
		catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

		
	}
	Coin cloneme(Model m){
		return new Coin(this,m);
	}
	

	void draw(Graphics g){

 	
		g.drawImage(coin_images[0], x- model.scrollPos, y, null);
		
	}
	Json marshall()
	{
		Json ob = Json.newObject();
		ob.add("type", jsonobjtype);
		ob.add("x", x);
		ob.add("y", y);

		return ob;
	}

	
	void update(Model m)
	{
		vert_vel += 3.14;
		x -=random;
		y += vert_vel;
	}
	
		
		
	
	
			
}