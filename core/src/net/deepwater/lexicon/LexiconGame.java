package net.deepwater.lexicon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import net.deepwater.engine.Engine;
import net.deepwater.engine.Entity;
import net.deepwater.engine.EntityPlayerMovementObserver;
import net.deepwater.engine.OverlayScreen;

public class LexiconGame extends ApplicationAdapter {
	Engine engine;
	
	@Override
	public void create () {
		engine = Engine.getInstance();
		engine.start();
		engine.setDisplayMode(1920, 1080, false);

		engine.getEntityManager().getCamera().get().translate(50, 0);

		Entity player = engine.getEntityManager().createEntity("Bob");
		player.setTextureName("Android/assets/badlogic.jpg");
		new EntityPlayerMovementObserver();
		player.setEntityObserver(new EntityPlayerMovementObserver());

		OverlayScreen screen = engine.getOverlayManager().createOverlayScreen(true);
		Entity panel = screen.createPanelEntity("Fred");
		panel.setTextureName("Android/assets/badlogic.jpg");
		panel.setPosition(0, 500);
	}

	@Override
	public void render () {
		engine.run();
	}
}
