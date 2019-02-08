import java.util.ArrayList;


class Model
{
	int scrollPos;
	ArrayList<Sprite> sprites;
	Mario mario;
	Sprite s;
	int coincounter;
	int jumpcounter;

	//Old Model
	Model()
	{
		mario = new Mario(this);
		sprites = new ArrayList<Sprite>();
		sprites.add(mario);
		this.load("map.json");
	}
	//New Model
	Model(Model other)
	{
		//this.mario = new Mario(that);
		this.scrollPos = other.scrollPos;
		this.sprites = new ArrayList<Sprite>();
		//sprites.add(this.mario);
		for(int i=0; i < other.sprites.size(); i++){
			Sprite other1 = other.sprites.get(i);
			Sprite clone = other1.cloneme(this);
			sprites.add(clone);
			if(clone.isMario())
				mario = (Mario)clone;
		}
	}

	enum Action{
	run,
	jump,
	jump_and_run
}
void do_action(Action i){
		remember_where_you_are();

		if(i == Action.run) 
		{
			mario.x += 10;
		}
		
		if(i == Action.jump)
		{
			if(mario.jumpframes < 5)
				mario.vert_vel -= 15;
				jumpcounter++;
				mario.x += 1;
				
				//mario.jumpframes++;

		
		}
		if(i == Action.jump_and_run){
			mario.x += 2;
			if(mario.jumpframes < 5)
				mario.vert_vel -= 20;
				jumpcounter++;
				//mario.jumpframes++;
		}

}

double evaluateAction(Action action, int depth)
{
	int k = 3;	//3
	int d = 15;	//12
	// Evaluate the state
	if(depth >= d)
		return scrollPos + (500000 * coincounter )- (1000 * jumpcounter);

	// Simulate the action
	Model copy = new Model(this); // uses the copy constructor
	copy.do_action(action); // like what Controller.update did before
	copy.update(); // advance simulated time

	// Recurse
	if(depth % k != 0)
	   return copy.evaluateAction(action, depth + 1);
	else
	{
	   double best = copy.evaluateAction(Action.run, depth + 1);
	   best = Math.max(best,
		   copy.evaluateAction(Action.jump, depth + 1));
	   best = Math.max(best,
		   copy.evaluateAction(Action.jump_and_run, depth + 1));
	   return best;
	}
}


	void remember_where_you_are()
	{
		mario.remember_where_you_are();
	}


	public void update()
	{


		for (int i = 0;i < sprites.size() ; i++) 
		{
			Sprite s = sprites.get(i);
			s.update(this);
			if(s.y > 1100){
				sprites.remove(s);
				//coincounter++;
				//System.out.println("coincounter");
				//System.out.println(coincounter);
			}
		}
		
	}
	

	void addBrick(int x, int y, int w, int h)
	{
		Sprite b = new Brick(x, y, w, h,this);
		sprites.add(b);
	}

	void addCoinBlocks(int x, int y, int w, int h)
	{
		Sprite cb = new CoinBlock(x, y,w,h,this);
		sprites.add(cb);

	}



	void unmarshall(Json ob)
	{
       sprites.clear();
       Json json_sprites = ob.get("sprites");
       for(int i = 0; i < json_sprites.size(); i++)
        {
            Json j = json_sprites.get(i);
      		String type = j.getString("type");  
      		if(type.equals("Mario")){
      			Sprite m = new Mario(j,this);
				sprites.add(mario);
			}
      		
      		if(type.equals("Brick")){
      			Brick b = new Brick(j, this);
				sprites.add(b);

      		}
      		if(type.equals("CoinBlock")){
      			CoinBlock cb = new CoinBlock(j, this);
			 	sprites.add(cb);

      		}
      		if(type.equals("Coin")){
      			Coin c = new Coin(j, this);
				sprites.add(c);

      		}

      		 
      	}	
	}
 	
 	Json marshall(){
 		  Json ob = Json.newObject();
 		  Json json_sprites = Json.newList();
 		  ob.add("sprites" , json_sprites);

 		  for (int i=0;i<sprites.size(); i++) 
 		  {
 		  	Sprite s = sprites.get(i);
 		  	Json j = s.marshall();
 		  	json_sprites.add(j);
 		  }
          return ob;
 	}

 	void save(String filename)
 	{
 		Json ob = marshall();
 		ob.save(filename);
 	}
   void load(String filename)
   {
   		Json ob = Json.newObject();
   		ob = ob.load(filename);
   		unmarshall(ob);
   }




	
}
