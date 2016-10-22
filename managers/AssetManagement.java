package com.playablegraphs.bunnycrash.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetManagement {
	
	public static AssetManager asset;
	STR str;
	
	public void load(){
		str = new STR();
		asset = new AssetManager();
		asset.load(str.pg, Texture.class);
		asset.load(str.playerl, Texture.class);
		asset.load(str.playerr, Texture.class);		
		asset.load(str.f46,Texture.class);
		asset.load(str.f47,Texture.class);
		asset.load(str.f48,Texture.class);
		asset.load(str.f49,Texture.class);
		asset.load(str.f50,Texture.class);
		asset.load(str.f51,Texture.class);
		asset.load(str.f52,Texture.class);
		asset.load(str.f53,Texture.class);
		asset.load(str.f54,Texture.class);
		asset.load(str.f55,Texture.class);
		asset.load(str.f56,Texture.class);
		asset.load(str.f57,Texture.class);
		asset.load(str.wall, Texture.class);
		asset.load(str.go, Texture.class);
		asset.load(str.bunny1d,Texture.class);
		asset.load(str.bunny2d,Texture.class);
		asset.load(str.koaladl,Texture.class);
		asset.load(str.koaladr,Texture.class);
		asset.load(str.monkeyd, Texture.class);
		asset.load("gfx/gui/splash.jpg", Texture.class);
		asset.load(str.rock, Texture.class);
		
	}
	public void dispose(){
		
	}
	public static class STR{
		public final String pg = "gfx/background/play.png";
		public final String hill = "gfx/background/hills1.png";
		public final String hill2 = "gfx/background/hills2.png";
		public final String piramid = "gfx/background/piramid.png";
		public final String playerl  = "gfx/player/playerl.png";
		public final String playerr  = "gfx/player/playerr.png";
		public final String  f46 = "gfx/items/fruits/46.png";
		public final String  f47 = "gfx/items/fruits/47.png";
		public final String  f48 = "gfx/items/fruits/48.png";
		public final String  f49 = "gfx/items/fruits/49.png";
		public final String  f50 = "gfx/items/fruits/50.png";
		public final String  f51 = "gfx/items/fruits/51.png";
		public final String  f52 = "gfx/items/fruits/52.png";
		public final String  f53 = "gfx/items/fruits/53.png";
		public final String  f54 = "gfx/items/fruits/54.png";
		public final String  f55 = "gfx/items/fruits/55.png";
		public final String  f56 = "gfx/items/fruits/56.png";
		public final String  f57 = "gfx/items/fruits/57.png";
		public final String wall = "gfx/items/environment/wood-spike-block.png";
		public final String go = "gfx/hud/gameover.png";
		public final String monkeyd = "gfx/player/monkeyd.png";
		public final String bunny1d = "gfx/player/bunny1d.png";
		public final String bunny2d = "gfx/player/bunny2d.png";
		public final String koaladr = "gfx/player/koaladr.png";
		public final String koaladl = "gfx/player/koaladl.png";
		public final String rock = "gfx/items/rocks/rock.png";
	}
	public AssetManager getAsset(){
		return asset;
	}
}
