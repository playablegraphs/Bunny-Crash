package com.playablegraphs.bunnycrash.gameobjects;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.playablegraphs.bunnycrash.Main;
import com.playablegraphs.bunnycrash.Variables;
import com.playablegraphs.bunnycrash.managers.AssetManagement;
import com.playablegraphs.bunnycrash.managers.AssetManagement.STR;


public class Player extends InputAdapter{
	
	private Animation                       walkAnimation;       
	private Animation                       walkAnimationR;   
    private Texture                         walkSheet;            
    private TextureRegion[]                 walkFrames;    
    private Texture                         walkSheetR;            
    private TextureRegion[]                 walkFramesR;  
    private TextureRegion                   currentFrame;           
    private TextureRegion                   currentFrameR;
    float stateTime;
    float stateTimeR;
    
    STR str;
    
    private final int MONKEY = 0;
    private final int KOALA = 1;
    private final int BUNNY1 = 2;
    private final int BUNNY2 = 3;
    
    private int ID = 0;
    
    public static boolean alive;
    public static boolean moveLeft = false;
    public static boolean moveRight = true;
    public static int Score;
    public static float x;
    public static float y;

    
    public AssetManagement aManager;
    
    Items items;
	public Player(){
		
		aManager = new AssetManagement();
		
		ID = BUNNY1;
		
		str = new AssetManagement.STR();
		walkSheet = aManager.asset.get(str.playerl, Texture.class);
		walkSheetR = aManager.asset.get(str.playerr, Texture.class);
		walkFrames = new TextureRegion[4];
		walkFramesR = new TextureRegion[4];
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, 120, 168);		
		for (int i = 0; i < 4; i++) {
			walkFrames[i] = tmp[ID][i];
		}
		TextureRegion[][] tmpR = TextureRegion.split(walkSheetR, 120, 168);		
		for (int i = 0; i < 4; i++) {
			walkFramesR[i] = tmpR[ID][i];
		}
		walkAnimation = new Animation(1/4f, walkFrames);
		walkAnimationR = new Animation(1/4f, walkFramesR);
		
		stateTime = 0f;
		stateTimeR = 0f;
		
		x = Variables.WIDTH/2 - walkFramesR[0].getRegionWidth()/2;
		y = Variables.HEIGHT/10 + walkFramesR[0].getRegionHeight()*0.475f;
	
		alive = true;

	}
	public void draw(float delta){
		stateTime += delta;
		stateTimeR += delta;
		Main.batch.begin();
		if(isAlive()){
		if(moveLeft){
			currentFrame = walkAnimation.getKeyFrame(stateTime,true);
			
			Main.batch.draw(currentFrame,x,y);
		
			x -= delta * 250;
		
		}
		else if(moveRight){
			currentFrameR = walkAnimationR.getKeyFrame(stateTimeR,true);

			Main.batch.draw(currentFrameR,x,y);

			x += delta * 250;
		}
		}
		else{
			if(ID == MONKEY){
				Main.batch.draw(aManager.asset.get(str.monkeyd, Texture.class),x,y);
			}
			if(ID == BUNNY2){
				Main.batch.draw(aManager.asset.get(str.bunny2d, Texture.class),x,y);
			}
			if(ID == BUNNY1){
				Main.batch.draw(aManager.asset.get(str.bunny1d, Texture.class),x,y);
			}
			if(ID == KOALA){
				if(x > 0)Main.batch.draw(aManager.asset.get(str.koaladr, Texture.class),x,y);
				else if(x < 0)Main.batch.draw(aManager.asset.get(str.koaladl, Texture.class),x,y);
			}
		}
		//	Main.batch.draw();
		if(!isAlive()) Main.batch.draw(aManager.asset.get(str.go,Texture.class),
				Variables.WIDTH/2-aManager.asset.get(str.go,Texture.class).getWidth()/2,
				Variables.HEIGHT/2+aManager.asset.get(str.go,Texture.class).getHeight()/4
				);
		Main.batch.end();
	}
	public void update(float delta){
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(!isAlive()) return false;
		if(moveLeft){
			moveLeft = false;
			moveRight = true;
		}
		else if(moveRight) {
			moveRight = false; 
			moveLeft = true;
		}
		return false;
	}@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {		
		return false;
	}
	public boolean isAlive(){
		return alive;
	}
}
