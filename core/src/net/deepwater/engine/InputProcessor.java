package net.deepwater.engine;

import com.badlogic.gdx.InputAdapter;

public class InputProcessor extends InputAdapter{

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		Engine.getInstance().getEntityManager().notifyKeyDown(keycode);
		Engine.getInstance().getOverlayManager().notifyKeyDown(keycode);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		Engine.getInstance().getEntityManager().notifyKeyUp(keycode);
		Engine.getInstance().getOverlayManager().notifyKeyUp(keycode);
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		Engine.getInstance().getEntityManager().notifyKeyTyped(character);
		Engine.getInstance().getOverlayManager().notifyKeyTyped(character);
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Engine.getInstance().getEntityManager().notifyTouchDown(screenX, screenY, pointer, button);
		Engine.getInstance().getOverlayManager().notifyTouchDown(screenX, screenY, pointer, button);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Engine.getInstance().getEntityManager().notifyTouchUp(screenX, screenY, pointer, button);
		Engine.getInstance().getOverlayManager().notifyTouchUp(screenX, screenY, pointer, button);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Engine.getInstance().getEntityManager().notifyTouchDragged(screenX, screenY, pointer);
		Engine.getInstance().getOverlayManager().notifyTouchDragged(screenX, screenY, pointer);
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Engine.getInstance().getEntityManager().notifyMouseMoved(screenX, screenY);
		Engine.getInstance().getOverlayManager().notifyMouseMoved(screenX, screenY);
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		Engine.getInstance().getEntityManager().notifyScrolled(amount);
		Engine.getInstance().getOverlayManager().notifyScrolled(amount);
		return true;
	}

}
