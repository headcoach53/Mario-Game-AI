import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
    Model model;
   //static Image[] mario_images = null;
   static Image[] background_images =null;
   //static Image[] brick_images =null;

    
	View(Controller c, Model m)
	{
		
		model = m;
		if(background_images == null)
		background_images = new Image[1];
		
		
		try
		{

			background_images[0] = ImageIO.read(new File("background.jpg"));
			//brick_images[0] = ImageIO.read(new File("brick.jpg"));
		} 
		catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
	
	public void paintComponent(Graphics g)
	{
		
		//Clear the Screen
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(background_images[0],-model.mario.x,0,null);
		g.setColor(new Color(50, 50, 0));

		for(int i = 0; i < model.sprites.size(); i++)
		{
			Sprite s = model.sprites.get(i);
			s.draw(g);
			
		}
		
		
				
		
	}
}