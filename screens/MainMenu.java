package com.playablegraphs.bunnycrash.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.playablegraphs.bunnycrash.Main;
import com.playablegraphs.bunnycrash.Variables;
import com.playablegraphs.bunnycrash.gameobjects.Market;
import com.playablegraphs.bunnycrash.managers.ScreenManager;
import com.playablegraphs.bunnycrash.managers.ScreenManager.States;
import com.playablegraphs.bunnycrash.managers.drawString;

public class MainMenu extends AbstractScreen{


	Skin skin;
	Table lefttable;
	Table righttable;
	Table midtable;
	Button scores;
	Button sound;
	Button vibrate;
	Button exit;
	Button achievements;
	Button friends;
	Button rate;
	Button bmarket;
	Button information;
	Button play;
	int midtablewidth;
	int midtableheight;
	drawString dString;
	Texture trans;
	Texture trans2;
	Market market;

	public MainMenu(ScreenManager sManager) {
		super(sManager);
	}
	@Override
	public void init() {
		trans = new Texture(Gdx.files.internal("gfx/trans.png"));
		trans2 = new Texture(Gdx.files.internal("gfx/trans2.png"));
		skin = new Skin();
		skin.add("scores", new Texture(Gdx.files.internal("gfx/gui/scores.png")));
		skin.add("vibrate", new Texture(Gdx.files.internal("gfx/gui/vibrate.png")));
		skin.add("sound", new Texture(Gdx.files.internal("gfx/gui/sound.png")));
		skin.add("achievements", new Texture(Gdx.files.internal("gfx/gui/achievements.png")));
		skin.add("friends", new Texture(Gdx.files.internal("gfx/gui/friends.png")));
		skin.add("information", new Texture(Gdx.files.internal("gfx/gui/information.png")));
		skin.add("market", new Texture(Gdx.files.internal("gfx/gui/market.png")));
		skin.add("rate", new Texture(Gdx.files.internal("gfx/gui/rate.png")));
		skin.add("play", new Texture(Gdx.files.internal("gfx/gui/play.png")));
		skin.add("trans", new Texture(Gdx.files.internal("gfx/trans.png")));
		dString = new drawString();
		midtablewidth = 1500;
		midtableheight = 1000;
		market = new Market(skin);
		scores = new Button(style("scores"));
		scores.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				sManager.setState(States.MAINMENU);
			}
		});
		vibrate = new Button(style("vibrate"));
		sound = new Button(style("sound"));
		achievements = new Button(style("achievements"));
		friends = new Button(style("friends"));
		rate = new Button(style("rate"));

		//marketwindow.add(rate);
		bmarket = new Button(style("market"));
		bmarket.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
					if(!market.table.isVisible()){
						market.table.setVisible(true);
						
					}
					if(market.table.isVisible()){
						market.table.setVisible(false);
					}
			}
		});
		information = new Button(style("information"));
		play = new Button(style("play"));
		/*play.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				sManager.setState(States.PLAY);
				midtable.remove();
				lefttable.remove();
				righttable.remove();
			}
		});*/
		lefttable = new Table().debug();
		righttable = new Table().debug();
		midtable = new Table().debug();
		

		midtable.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				sManager.setState(States.PLAY);
				midtable.remove();
				lefttable.remove();
				righttable.remove();
			
			};
		});
		lefttable.add(scores);
		lefttable.row();
		lefttable.add(vibrate);
		lefttable.row();
		lefttable.add(sound);

		righttable.add(achievements);
		righttable.row();
		righttable.add(friends);
		righttable.row();
		righttable.add(rate);
		righttable.row();
		righttable.add(bmarket);
		righttable.row();
		righttable.add(information);
	
		
		midtable.add(play).padTop(500);
		
		
		//righttable.add();
		righttable.setPosition(Variables.WIDTH - 170/2, Variables.HEIGHT/2);
		lefttable.setPosition(170/2, Variables.HEIGHT/2 - lefttable.getHeight());
		midtable.setPosition(Variables.WIDTH/2 - midtablewidth/2, Variables.HEIGHT/2-midtableheight/2);
		midtable.setSize(midtablewidth, midtableheight);
		Main.stage.addActor(lefttable);
		Main.stage.addActor(righttable);
		midtable.setTouchable(Touchable.enabled);
		Main.stage.addActor(midtable);
		Main.stage.addActor(market.table);
		scores.setPosition(0, Variables.HEIGHT*0.4f);
		vibrate.setPosition(0, Variables.HEIGHT*0.3f);
		sound.setPosition(0, Variables.HEIGHT*0.1f);
	}
	@Override
	public void update(float dt) {
		Main.stage.act(dt);
		Main.stage.draw();
	}
	@Override
	public void draw(float dt) {
		dString.draw(15f, "Touch To Play",Variables.WIDTH/2,Variables.HEIGHT/2);
	}
	@Override
	public void dispose() {
	}
	public Button.ButtonStyle style(String string){
		Button.ButtonStyle button = new Button.ButtonStyle();
		button.up = skin.getDrawable(string);

		return button;
	}
}
