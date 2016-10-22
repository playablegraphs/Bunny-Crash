package com.playablegraphs.bunnycrash.gameobjects;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.playablegraphs.bunnycrash.Variables;

public class Market{
	public Table table;
	public Skin skin;
	private int width,height;
	public Market(Skin skin){
		this.skin = skin;
		width = 1500;
		height = 1000;
		table = new Table(skin).debug();
		table.setBackground("trans");
		table.setVisible(false);
		table.setPosition(Variables.WIDTH/2 - width/2, Variables.HEIGHT/2 - height/2);
		table.setSize(width, height);
	}
	public void draw(){
		
	}
	public void update(float dt){
		
	}
}
