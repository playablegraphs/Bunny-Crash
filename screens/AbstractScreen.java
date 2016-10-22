package com.playablegraphs.bunnycrash.screens;

import com.playablegraphs.bunnycrash.managers.ScreenManager;

public abstract class AbstractScreen{
	
	
	
	protected ScreenManager sManager;
	//protected Stage stage;
	
	
	public AbstractScreen(ScreenManager sManager){
		this.sManager = sManager;
		init();
	}
	public abstract void init();
	public abstract void update(float dt);
	public abstract void draw(float dt);
	public abstract void dispose();


}
