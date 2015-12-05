package net.deepwater.engine;

import com.badlogic.gdx.ApplicationAdapter;

public class Lexicon extends ApplicationAdapter {
	Engine engine;
	
	@Override
	public void create () {
		engine = Engine.getInstance();
		engine.start();
        engine.setDisplayMode(1920, 1080, false);

        engine.getEntityManager().getCamera().get().translate(50, 0);

        Entity player = engine.getEntityManager().createEntity("Bob");
        player.setTextureName("Android/assets/badlogic.jpg");
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
