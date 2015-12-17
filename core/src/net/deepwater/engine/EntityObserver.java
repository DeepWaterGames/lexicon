package net.deepwater.engine;

//so it can recieve events and control the entity at the same time
public class EntityObserver extends EventListener
{
	public EntityObserver()
	{
	}
	
	public void onInit(Entity entity)
	{
	}
	
	public void onUpdate(Entity entity)
	{
	}

	//you could use an event manager for this shit but
	//fuck im too lazy to change it...
	public boolean keyDown(Entity entity, int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean keyUp(Entity entity, int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean keyTyped(Entity entity, char character) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchDown(Entity entity, int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchUp(Entity entity, int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchDragged(Entity entity, int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean mouseMoved(Entity entity, int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean scrolled(Entity entity, int amount) {
		// TODO Auto-generated method stub
		return false;
	}
};