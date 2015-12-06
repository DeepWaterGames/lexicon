package net.deepwater.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;

public class Engine
{
	private static Engine engine = new Engine();
	
	protected EntityManager entityManager;
	protected AssetManager assetManager;
	protected Background background;
	protected OverlayManager overlayManager;
	protected EventManager eventManager;
	protected InputProcessor inputProcessor;
	
	public Engine()
	{
	}
	
	public void setDisplayMode(int width, int height, boolean fullscreen)
	{
		Gdx.graphics.setDisplayMode(width, height, fullscreen);
	}
	
	public void setBackground(String textureName)
	{
		background.setTextureName(textureName);
	}
	
	public EntityManager getEntityManager()
	{
		return entityManager;
	}
	
	public AssetManager getAssetManager()
	{
		return assetManager;
	}
	
	public OverlayManager getOverlayManager()
	{
		return overlayManager;
	}
	
	public EventManager getEventManager()
	{
		return eventManager;
	}

    public InputProcessor getInputProcessor() { return inputProcessor; }
	
	public boolean start()
	{
		eventManager = new EventManager();

		entityManager = new EntityManager();
		
		assetManager = new AssetManager();
		
		background = new Background();
		
		overlayManager = new OverlayManager();

        inputProcessor = new InputProcessor();

		Gdx.input.setInputProcessor(inputProcessor);
		
		return true;
	}
	
	public boolean run()
    {
		eventManager.update();
		entityManager.updateEntities();
		overlayManager.updateOverlays();
	
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		background.render();
		
		entityManager.renderEntities();
		
		overlayManager.renderOverlays();
		
		return true;
	}
	
	public static Engine getInstance()
	{
		return engine;
	}
}
