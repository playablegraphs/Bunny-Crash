package com.playablegraphs.bunnycrash.gameobjects;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.playablegraphs.bunnycrash.Main;
import com.playablegraphs.bunnycrash.Variables;
import com.playablegraphs.bunnycrash.managers.AssetManagement;


public class Items{

	public Array<Fruits> arrayfruit;
	public Rectangle frect;
	public Array<Rectangle> arrayrocks;
	public Rectangle rocks;
	public static Rectangle player;
	public Fruits fruit;


	private long time;
	public AssetManagement aManager;
	public Items(){
		aManager = new AssetManagement();

		arrayfruit = new Array <Fruits>();
		arrayrocks = new Array<Rectangle>();
		setPlayer();
		spawnFruit();
	}
	public void spawnRock(){
		rocks = new Rectangle();
		rocks.x = MathUtils.random(
				MathUtils.round(Wall.wall1.x+Wall.wall1.getWidth()),
				MathUtils.round(Wall.wall2.x-Wall.wall1.getWidth())
				);
		rocks.y = Variables.HEIGHT;
		rocks.height = 120;
		rocks.width = 128;
		arrayrocks.add(rocks);
	}
	public void setPlayer(){
		player = new Rectangle();
		player.width = 120;
		player.height = 168;
		player.x = Player.x;
		player.y = Player.y;

	}
	public void spawnFruit(){		
		fruit = new Fruits();
		fruit.x = MathUtils.random(
				MathUtils.round(Wall.wall1.x+Wall.wall1.getWidth()),
				MathUtils.round(Wall.wall2.x-Wall.wall1.getWidth())
				);
		fruit.y = Variables.HEIGHT;
		fruit.width = fruit.height = 128;
		fruit.alive = true;
		fruit.ID = MathUtils.random(46, 57);
		arrayfruit.add(fruit);
		time = TimeUtils.nanoTime();
	}
	public void update_draw(){
		player.x = Player.x;
		player.y = Player.y;

		if(TimeUtils.nanoTime() - time > 2000000000 && Player.alive) spawnFruit();
	

		Main.batch.begin();
		for(Fruits raindrop: arrayfruit) {
			if(raindrop.isAlive());
			Main.batch.draw(aManager.asset.get("gfx/items/fruits/"+raindrop.ID+".png", Texture.class), raindrop.x, raindrop.y);
		}
		for(Rectangle rect : arrayrocks){
			Main.batch.draw(aManager.asset.get("gfx/items/rocks/rock.png", Texture.class), rect.x, rect.y);
		}
		Main.batch.end();	      
		Iterator<Fruits> iter = arrayfruit.iterator();
		while(iter.hasNext()) {
			Fruits afruit = iter.next();
			afruit.y -= 200 * Gdx.graphics.getDeltaTime();
			if(afruit.y + 64 < 0){
				iter.remove();
				afruit.alive = false;
			}
			if(afruit.overlaps(player)) {
				if(Variables.enclosure < 0) Variables.enclosure += Variables.enclosure/2;
				else Variables.enclosure -= Variables.enclosure*2;
				Player.Score += afruit.ID;
				iter.remove();
				afruit.alive = false;
			}
		}
		Iterator<Rectangle> liter = arrayrocks.iterator();
		while(liter.hasNext()) {
			Rectangle rect = liter.next();
			rect.y -= 200 * Gdx.graphics.getDeltaTime();
			if(rect.y + 64 < 0){
				liter.remove();
			}
			if(rect.overlaps(player)) {
				if(Player.alive) Player.alive = false;
				liter.remove();
			}
		}
	
		if(Wall.wall1.overlaps(Items.player)){
			Player.alive = false;  
		}
		if(Wall.wall2.overlaps(Items.player)){
			Player.alive = false;  
		}
	}
	public static class Fruits{
		public int x;
		public int y;
		public int height;
		public int width;
		public boolean alive;
		public int ID;
		public boolean isAlive(){
			return alive;
		}
		public boolean overlaps (Rectangle r) {
			return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
		}
	}
}
