package net.deepwater.lexicon;

import com.badlogic.gdx.math.Vector2;

import net.deepwater.engine.Entity;
import net.deepwater.engine.EntityObserver;

public class EntityPlayerMovementObserver extends EntityObserver {

	private Vector2 acceleration;
	private Vector2 velocity;
	private boolean move;
	
	public EntityPlayerMovementObserver()
	{
		velocity = new Vector2();
		acceleration = new Vector2();
		move = false;
	}
	
	@Override
	public void onInit(Entity entity)
	{
		entity.setPosition(0F, 500F);
	}
	
	@Override
	public void onUpdate(Entity entity)
	{	
		if(move)
		{
			
		}
		
		entity.move(4F, 0F);
		entity.rotate(2F);
		
		if(entity.getPositionX() > 1900)
		{
			entity.setPosition(0F, 500F);
		}
		
		//check for collisions here
	}
	
	@Override
	public boolean keyDown(Entity entity, int keycode) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean keyUp(Entity entity, int keycode) {
		// TODO Auto-generated method stub
		return super.keyUp(entity, keycode);
	}

	@Override
	public boolean keyTyped(Entity entity, char character) {
		// TODO Auto-generated method stub
		return super.keyTyped(entity, character);
	}

	@Override
	public boolean touchDown(Entity entity, int screenX, int screenY,
			int pointer, int button) {
		// TODO Auto-generated method stub
		return super.touchDown(entity, screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchUp(Entity entity, int screenX, int screenY,
			int pointer, int button) {
		// TODO Auto-generated method stub
		return super.touchUp(entity, screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(Entity entity, int screenX, int screenY,
			int pointer) {
		// TODO Auto-generated method stub
		return super.touchDragged(entity, screenX, screenY, pointer);
	}

	@Override
	public boolean mouseMoved(Entity entity, int screenX, int screenY) {
		// TODO Auto-generated method stub
		float magnitude = (float) Math.sqrt((float)((screenX * screenX) + (screenY * screenY)));
		float x = screenX / magnitude;
		float y = screenY / magnitude;
		entity.move(x * velocity.x, y * velocity.y);
		return true;
	}

	@Override
	public boolean scrolled(Entity entity, int amount) {
		// TODO Auto-generated method stub
		return super.scrolled(entity, amount);
	}
}
