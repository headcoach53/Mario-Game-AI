import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keySpace;
	int mouseDownX;
	int mouseDownY;
	Mario mario;

	Controller(Model m)
	{
		model = m;
	}
	public void mousePressed(MouseEvent e)
	{
		mouseDownX = e.getX();
		mouseDownY = e.getY();


	}

	public void mouseReleased(MouseEvent e) 
	{

		
		int x1 = mouseDownX;
		int x2 = e.getX();
		int y1 = mouseDownY;
		int y2 = e.getY();
		int left = Math.min(x1, x2);
		int right = Math.max(x1,x2);
		int top = Math.min(y1, y2);
		int bottom = Math.max(y1, y2);
		model.addBrick(left + model.scrollPos , top, right - left, bottom - top);
	

		if(e.getButton() == 3)
		{
			x1 = mouseDownX;
			y1 = mouseDownY;
			model.addCoinBlocks(left + model.scrollPos, top, 75, 75);
		}
	}
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {  
		if(e.getY() < 100)
		{
			System.out.println("break here");
		}
	}

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_S: model.save("map.json"); break;
			case KeyEvent.VK_L: model.load("map.json"); break;
			case KeyEvent.VK_SPACE: keySpace = true; break;


		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_SPACE: keySpace = false; break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
	model.remember_where_you_are();

	// Evaluate each possible action
	double score_run = model.evaluateAction(Model.Action.run, 0);
	double score_jump = model.evaluateAction(Model.Action.jump, 0);
	double score_jump_and_run = model.evaluateAction(Model.Action.jump_and_run, 0);

	// Do the best one
	if(score_jump_and_run > score_jump && score_jump_and_run > score_run)
		model.do_action(Model.Action.jump_and_run);
	else if(score_jump > score_run)
		model.do_action(Model.Action.jump);
	else
		model.do_action(Model.Action.run);

		if(keyRight) 
		{
			model.mario.x += 10;
		}
		if(keyLeft)
		{
			model.mario.x -= 10;
		} 
		if(keyUp)
		{
			if(model.mario.jumpframes < 5)
				model.mario.vert_vel += 10.1;
		 //System.out.println("model.mario.vert_vel");
		  //System.out.println(model.mario.vert_vel);

		}
	}

	public void actionPerformed(ActionEvent e)
	{
		
	}
	public void setView(View v)
	{
		view = v;
	}
}
