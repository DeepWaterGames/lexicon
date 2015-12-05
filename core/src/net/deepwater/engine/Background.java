package net.deepwater.engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background
{
	protected Sprite sprite;
	protected Batch batch;
	
	Background()
	{
		sprite = null;
		batch = new SpriteBatch();
	}
	
	public void setTextureName(String name)
	{
		if(Engine.getInstance().getAssetManager().isLoaded(name) == false)
		{
			Engine.getInstance().getAssetManager().load(name, Texture.class);
			Engine.getInstance().getAssetManager().finishLoading();
		}
		
		Texture texture = Engine.getInstance().getAssetManager().get(name, Texture.class);
		
		if(texture == null)
		{
			System.out.println("Could not load " + name);
			return;
		}
		
		sprite = new Sprite(texture);
	}
	
	public void render()
	{
		if(sprite != null)
		{
			batch.begin();
			sprite.draw(batch);
			batch.end();
		}
	}
};