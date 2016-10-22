package com.playablegraphs.bunnycrash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.playablegraphs.bunnycrash.managers.AssetManagement;
import com.playablegraphs.bunnycrash.managers.ScreenManager;

public class Main extends Game {
	
	/* VIEW */	
	public static SpriteBatch batch;
	public static Viewport viewport;
	public static OrthographicCamera orthocam;
	public static Stage stage;
	/* MANAGEMENT */
	public ScreenManager sManager;
	public AssetManagement aManager;
	/* TIMING */
	public boolean isDone;
	public float dt;
	
	@Override
	public void create() {
		/* VIEW */
		batch = new SpriteBatch();
		orthocam = new OrthographicCamera(Variables.WIDTH,Variables.HEIGHT);
		viewport = new FitViewport(Variables.WIDTH, Variables.HEIGHT,orthocam);
		//orthocam.setToOrtho(false,viewport.getWorldWidth(),viewport.getWorldHeight());		
		stage = new Stage(viewport,batch);
		/* MANAGEMENT */
		aManager = new AssetManagement();
		aManager.load();
		aManager.asset.finishLoadingAsset("gfx/gui/splash.jpg");	
		/* TIMING */
		dt = 0;
		isDone = false;
		
		
	}
	@Override
	public void render() {	
		Gdx.gl.glClearColor(255,255,255,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		dt = Gdx.graphics.getDeltaTime();
		batch.setProjectionMatrix(orthocam.combined);
		
		if(!isDone){
			batch.begin();
			batch.draw(aManager.asset.get("gfx/gui/splash.jpg", Texture.class),0,0);
			batch.end();
		}
		if(aManager.getAsset().update() && !isDone){
			sManager = new ScreenManager(stage);
			isDone = true;
		}	
		if(isDone)	{
			sManager.draw(dt); 
			sManager.update(dt);		
		}
	}
	@Override 
	public void dispose(){
		sManager.dispose();
	}
}