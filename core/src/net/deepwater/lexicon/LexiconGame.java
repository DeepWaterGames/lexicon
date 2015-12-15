package net.deepwater.lexicon;

import com.badlogic.gdx.ApplicationAdapter;

import net.deepwater.engine.CameraObserver;
import net.deepwater.engine.Engine;
import net.deepwater.engine.Entity;
import net.deepwater.engine.OverlayScreen;

public class LexiconGame extends ApplicationAdapter {
	Engine engine;
	
	@Override
	public void create ()  {
		//BaseGameManager.getInstance().getLoader().loadFromFile("config.txt");

		engine = Engine.getInstance();
		engine.start();
		engine.setDisplayMode(1920, 1080, false);

		engine.getEntityManager().getCamera().get().translate(50, 0);

        engine.getEntityManager().getCamera().setCameraObserver(new CameraObserver());

		Entity player = engine.getEntityManager().createEntity("Bob");
		player.setTextureName("Android/assets/badlogic.jpg");
		player.setEntityObserver(new PlayerMovementObserver());

		OverlayScreen screen = engine.getOverlayManager().createOverlayScreen(true);
		Entity panel = screen.createPanelEntity("Fred");
		panel.setTextureName("Android/assets/badlogic.jpg");
		panel.setPosition(0, 0);
	}

	@Override
	public void render () {
		AsteroidSpawner.getInstance().update();
		engine.run();
	}
}
