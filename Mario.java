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

class Mario extends Sprite
{
	int prev_y;
	int prev_x;
	int jumpframes;
	

 static Image[] mario_images = null;

	Mario(Model m)
	{
		super(m);
		w = 60;
		h = 95;
		jsonobjtype = "Mario";
		if(mario_images == null){
		mario_images = new Image[5];

		try
		{
			mario_images[0] = ImageIO.read(new File("mario1.png"));
			mario_images[1] = ImageIO.read(new File("mario2.png"));
			mario_images[2] = ImageIO.read(new File("mario3.png"));
			mario_images[3] = ImageIO.read(new File("mario4.png"));
			mario_images[4] = ImageIO.read(new File("mario5.png"));
		} 
		catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}



	}

	Mario(Mario that, Model mod)
	{
		super(that, mod);
		//System.out.println("Mario Copy Works");
		this.x = that.x;
		this.y = that.y;
		this.w = that.w;
		this.h = that.h;
		this.prev_x = that.prev_x;
		this.prev_y = that.prev_y;
		this.jumpframes = that.jumpframes;
		this.jsonobjtype = that.jsonobjtype;
	}


	Mario(Json ob,Model m)
	{
		super(m);
		jsonobjtype = (String)ob.getString("type");
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
		vert_vel = (int)ob.getDouble("vert_vel");
		jumpframes = (int)ob.getLong("jumpframes");
		if(mario_images == null)
		{
			mario_images = new Image[5];

			try
			{
				mario_images[0] = ImageIO.read(new File("mario1.png"));
				mario_images[1] = ImageIO.read(new File("mario2.png"));
				mario_images[2] = ImageIO.read(new File("mario3.png"));
				mario_images[3] = ImageIO.read(new File("mario4.png"));
				mario_images[4] = ImageIO.read(new File("mario5.png"));
			} 
			catch(Exception e) 
			{
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
		
	}
	Sprite cloneme(Model m){
		return new Mario(this, m);
	}
	
	void draw(Graphics g){
		//Draws Mario
		int marioFrame = (Math.abs(model.mario.x)/ 20) % 5;	
		//20 is the how fast he runs and 5 is the number of frames. 	
		g.drawImage(mario_images[marioFrame], x- model.scrollPos, y, null);
		
	}
	Json marshall()
	{
		Json ob = Json.newObject();
		ob.add("type", jsonobjtype);
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		ob.add("vert_vel", vert_vel);
		ob.add("jumpframes", jumpframes);

		return ob;
	}
	boolean isMario(){
		//System.out.println("isBrick works sprite");
		return true;

	}


	void remember_where_you_are()
	{
		prev_x = x;
		prev_y = y;
	}
	
	
	void get_out(Sprite s)
	{
		//If youre coming from the left
		if(x + w >= s.x && prev_x + w <= s.x)
		{
			x = s.x - w - 1;
		}
		//Coming From the Right 
		if(x <= s.x + s.w && prev_x >= s.x + s.w)
		{
			x = s.x + s.w + 1;
		}
		//Coming from the Top
		if(y + h >= s.y && prev_y + h <= s.y)
		{
			y = s.y - h - 1;
			vert_vel = 0;
			jumpframes = 0;
		}
		//Coming From the Bottom 
		if(y <= s.y + s.h && prev_y >= s.y + s.h)
		{
			y = s.y + s.h + 1;
			vert_vel = 0; 
		}	
	}

	void update(Model m)
	{
		vert_vel += 3.14;
		y += vert_vel;
		model.scrollPos = x -300;
		//jumpframes++;
		//System.out.println("jumpframes");
		//System.out.println(jumpframes);

		if(y >= 750)
		{
			y = 750;
			vert_vel = 0;
			jumpframes = 0;
		}

		for (int i=0;i<model.sprites.size();i++) 
		{
			Sprite s = model.sprites.get(i);
			if(s.isBrick()){
				if(doesCollide(s))
					{
						get_out(s);

					}
	}
			if(s.isCoinBlock()){
				if(doesCollide(s))
					{
					if(y <= s.y + s.h && prev_y >= s.y + s.h){
						//System.out.println(" is in does collideworks Mario cb");
						CoinBlock cb = (CoinBlock)s;
						cb.popoutacoin();
						get_out(s);
						
					}
					if(x + w >= s.x && prev_x + w <= s.x)
					{
						x = s.x - w - 1;
					}
					//Coming From the Right 
					if(x <= s.x + s.w && prev_x >= s.x + s.w)
					{
						x = s.x + s.w + 1;

					}
					//Coming from the Top
					if(y + h >= s.y && prev_y + h <= s.y)
					{
						y = s.y - h - 1;
						vert_vel = 0;
						jumpframes = 0;
					}
					}
			}
		}
	}
}	
	//IF COINBLOCK ONCE AGAIN (((CoinBlock)s).counter++