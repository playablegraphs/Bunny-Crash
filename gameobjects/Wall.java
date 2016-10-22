package com.playablegraphs.bunnycrash.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.playablegraphs.bunnycrash.Main;
import com.playablegraphs.bunnycrash.Variables;
import com.playablegraphs.bunnycrash.managers.AssetManagement;
import com.playablegraphs.bunnycrash.managers.AssetManagement.STR;


public class Wall {
	
	public Texture texture;
	public Sprite spr;

	public static Rectangle wall1;
	public static Rectangle wall2;
	STR str;
	public AssetManagement aManager;
	public Wall(){
		str = new STR();
		aManager = new AssetManagement();
		texture = aManager.asset.get(str.wall, Texture.class);
		spr = new Sprite(texture);
		wall1 = new Rectangle();
		wall2 = new Rectangle();
		wall1.height = wall2.height = texture.getHeight();
		wall1.width = wall2.width = texture.getWidth();
		wall1.x = 0;
		wall1.y = Player.y;
		wall2.x = Variables.WIDTH - texture.getWidth();
		wall2.y = Player.y;
	}
	public void draw(float dt){
		Main.batch.begin();
		//Main.batch.draw(texture,-width,Player.y,width*2,height);
		//Main.batch.draw(texture,Variables.WIDTH - width,Player.y,width*2,height);
		
		
		if(Player.alive && Variables.Time > 5){
			Variables.enclosure += (dt/100)*Variables.Time;
			wall1.x +=Variables.enclosure;
			wall2.x -=Variables.enclosure; 
			if(wall1.x < 0) wall1.x = 0;
			if(wall2.x > Variables.WIDTH - texture.getWidth()) wall2.x = Variables.WIDTH - texture.getWidth();
		}
		spr.setPosition(wall1.x, Player.y);
		spr.setRotation(0);
		spr.draw(Main.batch);
		spr.setRotation(180);
		spr.setPosition(wall2.x, Player.y);
		spr.draw(Main.batch);
		Main.batch.end();
	}
}
