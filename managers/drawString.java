package com.playablegraphs.bunnycrash.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.playablegraphs.bunnycrash.Main;
import com.playablegraphs.bunnycrash.Variables;

public class drawString {
	
	public Array<tex> letters;
	public String str = "";
	public int height;
	public int width;
	public drawString(){
		letters = new Array<tex>();
		for (int i = 65; i < 90; i++) {
			letters.add(new tex(new Texture(Gdx.files.internal("gfx/gui/letters/letter"+(char)i+".png")),i));
		}
	}
	public void draw(float dt,String s,float x , float y){
			//s.toUpperCase();
		this.str = s;
		for (int j = 0; j < s.length(); j++) {
			//s.toUpperCase();
			char c = s.charAt(j);
			if(c == ' ') c = s.charAt(++j);
			if(c > 90)	c -= 32;
				for (tex tex : letters) {
					if(tex.ID == c){
						
					Main.batch.begin();
					Main.batch.draw(tex.tex,x  + j * 35,y,tex.tex.getWidth()*0.75f,tex.tex.getHeight()*0.75f);
					Main.batch.end();
					}		
				}
				
		}
		
	}
	public class tex{
		Texture tex;
		int ID;
		public tex(Texture tex,int ID){
			this.tex = tex;
			this.ID = ID;
		}
		public Texture getTexture(int id){
			
			return tex;
		}
		public int getWidth(){
			return tex.getWidth();
		}
	}
	
}
