package com.playablegraphs.bunnycrash.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.playablegraphs.bunnycrash.Main;
import com.playablegraphs.bunnycrash.Variables;
import com.playablegraphs.bunnycrash.gameobjects.Hud;
import com.playablegraphs.bunnycrash.gameobjects.Items;
import com.playablegraphs.bunnycrash.gameobjects.Player;
import com.playablegraphs.bunnycrash.gameobjects.Wall;
import com.playablegraphs.bunnycrash.managers.AssetManagement;
import com.playablegraphs.bunnycrash.managers.AssetManagement.STR;
import com.playablegraphs.bunnycrash.managers.ScreenManager;
import com.playablegraphs.bunnycrash.managers.drawString;
import com.playablegraphs.bunnycrash.managers.ScreenManager.States;

public class PlayScreen extends AbstractScreen{

	STR str;
	Sprite hill;
	Sprite hill2;
	Texture hilltex;
	
	Player player;
	Hud hud;
	Wall wall;
	Items items;
	AssetManagement aManager;
	public long Timer;
	public Button medal;
	public Button leader;
	public static Button playagain;
	public static Button share;
	Button menu;
	public Skin skin;
	public Table table;
	public Table itable;
	public int tableheight;
	public int tablewidth;
	Texture trans;
	Texture button;
	drawString dString;
	public PlayScreen(ScreenManager sManager) {
		super(sManager);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init() {
		trans = new Texture(Gdx.files.internal("gfx/transnew.png"));
		button = new Texture(Gdx.files.internal("gfx/gui/button.png"));
		str = new AssetManagement.STR();
		aManager = new AssetManagement();
		player = new Player();
		hud = new Hud();
		wall = new Wall();
		items = new Items();
		dString = new drawString();
		Variables.Time = 0;
		Variables.Timer = TimeUtils.millis();
		Variables.dtSec = 0;
		Variables.enclosure = 0;
		skin = new Skin();
		
		skin.add("button2", new Texture(Gdx.files.internal("gfx/gui/button2.png")));
		skin.add("button", new Texture(Gdx.files.internal("gfx/gui/button.png")));
		skin.add("medal", new Texture(Gdx.files.internal("gfx/gui/medal.png")));
		skin.add("leader", new Texture(Gdx.files.internal("gfx/gui/leader.png")));
		
		medal = new Button(style("medal"));
		medal.setName("medal");
		medal.addListener(Clicked(medal.getName(), medal));
		medal.setSize(200,200);
		leader = new Button(style("leader"));
		leader.setName("leader");
		leader.addListener(Clicked(leader.getName(), leader));
		leader.setSize(200, 200);
		
		
		menu = new Button(style("button"));
		menu.setName("menu");
		menu.addListener(Clicked(menu.getName(), menu));
		share = new Button(style("button"));
		share.setName("share");
		share.addListener(Clicked(share.getName(),share));
		playagain = new Button(style("button"));
		playagain.setName("playagain");
		playagain.addListener(Clicked(playagain.getName(),playagain));
		tableheight = skin.get("button", Texture.class).getHeight()*2;
		tablewidth = skin.get("button", Texture.class).getWidth()*2;
		table = new Table(skin);
		itable = new Table(skin);
		itable.setBounds(Variables.WIDTH/2 - tablewidth/2, Variables.HEIGHT/2, tablewidth, tableheight);
		itable.setBackground("button");
		itable.add(medal).padRight(100);
		itable.add(leader).padLeft(100);
		tableheight = 600;
		tablewidth = 600;
		table.row();
		table.add(playagain);
		table.row();
		table.add(share);
		table.row();
		table.add(menu);
		table.setBounds(Variables.WIDTH/2 - tablewidth/2, Variables.HEIGHT/2 - tableheight, tablewidth, tableheight);
		Main.stage.addActor(table);		
		Main.stage.addActor(itable);
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub		
		Variables.dtSec += dt;
		if(Variables.dtSec > 1){
			Variables.Time++;
			Variables.dtSec = 0;
		}
		if(TimeUtils.millis() - Variables.Timer > 5000 && player.isAlive()){			
			Variables.Timer = TimeUtils.millis();
			items.spawnRock();
		}	
		hud.draw_update(dt);
		player.update(dt);
		items.update_draw();
		
		if(!player.isAlive()){
			

			//table.setVisible(true);
		
			Main.batch.begin();
			Main.batch.draw(trans,Variables.WIDTH/2 - trans.getWidth()/2*7.74f,Variables.HEIGHT/2 - trans.getHeight()/2*4.25f
					, Variables.WIDTH
					,Variables.HEIGHT
					);
			Main.batch.end();
			table.setVisible(true);
			itable.setVisible(true);
			Main.stage.act(dt);
			Main.stage.draw();
			//dString.draw(dt, "REPLAY",Variables.WIDTH/2 - button.getWidth()/3,Variables.HEIGHT/2 - button.getHeight()*1.75f);
			//dString.draw(dt, "SHARE",Variables.WIDTH/2 - button.getWidth()/3,Variables.HEIGHT/2 - button.getHeight()*2.725f);
			
			dString.draw(dt, "REPLAY",getStageLocation(playagain).x + playagain.getWidth()/2 - dString.str.length()*45/2,
					getStageLocation(playagain).y + playagain.getHeight()/2 - 60/2);
			dString.draw(dt, "SHARE",Variables.WIDTH/2 - button.getWidth()/3,Variables.HEIGHT/2 - button.getHeight()*2.725f);
			
		}
		else {
			table.setVisible(false);
			itable.setVisible(false);
		}
	}
	@Override
	public void draw(float dt) {
		if(!player.isAlive()){
			
		}

		player.draw(dt);
		wall.draw(dt);
		
	}
	public Button.ButtonStyle style(String direction){
		ButtonStyle style = new ButtonStyle();
		style.up = skin.getDrawable(direction);
		if(direction == "button")style.down = skin.getDrawable(direction+""+2);
		return style;
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	public ClickListener Clicked(final String s,final Button b){
			ClickListener click = new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if(s == "playagain"){
						player = new Player();
						hud = new Hud();
						wall = new Wall();
						items = new Items();						
					}
					if(s == "menu"){
						sManager.setState(States.MAINMENU);
						Player.alive = false;
						table.remove();
						itable.remove();
					}
				}
			};
		return click;
	}
	public Button getPlayagain() {
		return playagain;
	}
	public Button getMenu() {
		return share;
	}
	public static Vector2 getStageLocation(Actor actor) {
	    return actor.localToStageCoordinates(new Vector2(0, 0));
	}
}
