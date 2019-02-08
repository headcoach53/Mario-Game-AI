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
import java.util.Random;
class CoinBlock extends Sprite
{


int coins;
int coincounter= 0;
 static Random r = new Random();
 static Image[] coinblock_images = null;

	CoinBlock(int _x, int _y, int _w, int _h, Model m)
	{

		super(m);
		x = _x;
		y = _y;
		w = 75;
		h = 75;
		coins = 5;
		jsonobjtype = "CoinBlock";
		if(coinblock_images == null){
		coinblock_images = new Image[5];

		try
		{
			coinblock_images[0] = ImageIO.read(new File("block1.png"));
			coinblock_images[1] = ImageIO.read(new File("block2.png"));
		} 
		catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}


	}
	CoinBlock(CoinBlock that, Model mod)
	{
		super(that,mod);
		//System.out.println("Coin Copy Works");
		this.x = that.x;
		this.y = that.y;
		this.w = that.w;
		this.h = that.h;
		this.jsonobjtype = that.jsonobjtype;
		this.coins = that.coins;
	}
	CoinBlock cloneme(Model m){
		return new CoinBlock(this, m);
	}


	void popoutacoin(){
		model.coincounter++;
		if(coins != 0)
		{
			int	horiz_velocity = r.nextInt() * 16 - 8;
			int vert_velocity = -22 ;
		
		Coin c = new Coin(x, y,-horiz_velocity,vert_velocity, model);
			model.sprites.add(c);
			coins--;

		}
	}
	

	CoinBlock(Json ob,Model m)
	{
		super(m);
		jsonobjtype = (String)ob.getString("type");
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
		coins = (int)ob.getLong("coins");

		if(coinblock_images == null){
		coinblock_images = new Image[2];

		try
		{
			coinblock_images[0] = ImageIO.read(new File("block1.png"));
			coinblock_images[1] = ImageIO.read(new File("block2.png"));
		} 
		catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}


		
	}
	void draw(Graphics g){
 	if(coins == 0)
		g.drawImage(coinblock_images[1], x- model.scrollPos, y, null);
	else{
		g.drawImage(coinblock_images[0], x- model.scrollPos, y, null);
	}	
	}
	Json marshall()
	{
		Json ob = Json.newObject();
		ob.add("type", jsonobjtype);
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		ob.add("coins", coins);

		return ob;
	}
	boolean isCoinBlock(){
		//System.out.println("isCoinBlock boolean works CoinBlock");
		return true;
	}
	

	
	void update(Model m)
	{
		model = m;


	}
}