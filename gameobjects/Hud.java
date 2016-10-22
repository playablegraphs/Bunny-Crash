package com.playablegraphs.bunnycrash.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.playablegraphs.bunnycrash.Main;
import com.playablegraphs.bunnycrash.Variables;

public class Hud extends Main{

	DrawString dString;
	public TextureRegion[] heartfull;
	public TextureRegion[] heartempty;
	public Texture heartemp;
	public Texture heartful;
	public int time;
	public int timer;

	public int n = 0;
	public Hud(){
		dString = new DrawString();
		heartfull = new TextureRegion[10];
		heartempty = new TextureRegion[10];
		heartemp = new Texture(Gdx.files.internal("gfx/hud/heartempty.png"));
		heartful = new Texture(Gdx.files.internal("gfx/hud/heartfull.png"));
		time = 0;
		timer = 3;
	
		for (int i = 1; i < heartfull.length+1; i++) {
			heartfull[i-1] = new TextureRegion(heartful,10*i,10*i,
					heartful.getWidth()/heartfull.length,
					heartful.getHeight()/heartfull.length);
		}
		for (int i = 0; i < heartempty.length; i++) {
			heartempty[i] = new TextureRegion(heartemp,10,10,
					heartemp.getWidth()/heartempty.length,
					heartemp.getHeight()/heartempty.length);
		}

		
		
	}
	public void draw_update(float dt){
		//super.stage.act(dt);
		//super.stage.draw();
		time +=dt;
		if(time > 0.1f){
			n++;
			time = 0;
		}

		
		Main.batch.begin();
	
		for (int j = 0; j < heartfull.length; j++) {
			Main.batch.draw(heartfull[j],Variables.WIDTH/2,Variables.HEIGHT/2);
		}
		/*for (int j = 0; j < 0; j++) {
			Main.batch.draw(heartempty[j],Variables.WIDTH/2,Variables.HEIGHT/2);	
		}*/
		
		Main.batch.end();
		dString.drawString(Main.batch, ""+Player.Score,
				dString.cross.getWidth()/2,
				Variables.HEIGHT-dString.cross.getHeight()*3);
		//dString.drawString(Main.batch, "12"+Variables.Time, Variables.WIDTH/2, Variables.HEIGHT/2);
	}
	public class DrawString {		
		public TextureRegion[] numbersregion;
		public Texture cross;
		
		public char c;
		private int a = 0;
		
		public DrawString(){
			//numbers = new char[10];
			numbersregion = new TextureRegion[10];
			for (int i = 0; i < 10; i++) {
				numbersregion[i] = new TextureRegion(new Texture(Gdx.files.internal("gfx/hud/numbers/"+i+".png")));
			}
			cross = new Texture(Gdx.files.internal("gfx/hud/numbers/x.png"));
			
		}
		public void drawString(SpriteBatch sb,String numbers,float x,float y){
			for (a = 0; a < numbers.length(); a++) {
				 char c = numbers.charAt(a);
				 if(c == ',') c = ' ';
					if(c >= '0' && c <= '9') c -= '0';
					else continue;
					sb.begin();
				sb.draw(numbersregion[c], x+cross.getWidth()/2 + a*40, y,numbersregion[c].getRegionWidth()*1.50f,numbersregion[c].getRegionHeight()*1.50f);
				//if(!numbers.equals(Integer.toString(GameVars.currentLevel)))
				//sb.draw(cross,x - cross.getWidth()*1.25f,y,cross.getWidth()*1.50f,cross.getHeight()*1.50f);
				sb.end();
			}
		}
	}
}
