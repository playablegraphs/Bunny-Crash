package com.playablegraphs.bunnycrash.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.playablegraphs.bunnycrash.Main;
import com.playablegraphs.bunnycrash.Variables;
import com.playablegraphs.bunnycrash.screens.AbstractScreen;
import com.playablegraphs.bunnycrash.screens.MainMenu;
import com.playablegraphs.bunnycrash.screens.PlayScreen;

public class ScreenManager extends Main{
	
	private AbstractScreen actScreen;
	
	public enum States { MAINMENU,INTRO,SELECT,PLAY,OPTIONS,HIGHSCORES,EXIT }
	
	public ScreenManager(Stage stage) {
		InputMultiplexer im = new InputMultiplexer(new InputManager(),stage);
		Gdx.input.setInputProcessor(im);
		
		if(actScreen != null) actScreen.dispose();	
		setState(States.MAINMENU);		
	}
	public void setState(States state){
		if(actScreen != null) actScreen.dispose();	
		switch(state){
		case MAINMENU:
			actScreen = new MainMenu(this);
			break;
		case SELECT:
			//actScreen = new SelectScreen(this);
			break;
		case PLAY:
			actScreen = new PlayScreen(this);
			break;
		case INTRO:
			//actScreen = new IntroScreen(this);
			break;
		case OPTIONS:
			//actScreen = new OptionsScreen(this);
			break;
		case HIGHSCORES:
			//actScreen = new HighScoresScreen(this);
			break;
		case EXIT:
			Gdx.app.exit();
			break;
		}
	}
	public void draw(float dt){
		/* BACKGROUND */
		Main.batch.begin();		
		Main.batch.draw(aManager.asset.get("gfx/background/play.png", Texture.class),0,0,Variables.WIDTH,Variables.HEIGHT);
		Main.batch.end();
		actScreen.draw(dt);
	}
	public void update(float dt){
		actScreen.update(dt);
	}
	public void dispose() {
		actScreen.dispose();
	}
}
