package net.deepwater.lexicon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import net.deepwater.engine.Engine;
import net.deepwater.engine.Entity;
import net.deepwater.engine.EntityObserver;

public class PlayerMovementObserver extends EntityObserver {
	private Vector2 velocity;
	private float angularVelocity;
	private float lastProportional;
	private float targetY;

	private float kP = .025F;
	private float kD = 2;

	//normally, component architecture would be used here
	float health  = 100; //100% health
	float armor = 0; //0% resistance
	
	public PlayerMovementObserver()
	{
		velocity = new Vector2();
		angularVelocity = 0F;
		lastProportional = 0F;
	}

	public void setHealth(float health)
	{
		this.health = health;
	}

	public float getHealth()
	{
		return this.health;
	}

	public void setArmor(float armor)
	{
		this.armor = armor;
	}

	public float getArmor(float armor)
	{
		return this.armor;
	}
	
	@Override
	public void onInit(Entity entity)
	{
		entity.setPosition(0F, 500F);
	}
	
	@Override
	public void onUpdate(Entity entity)
	{
		float dt = Gdx.graphics.getDeltaTime();
		dt = Math.max(dt, .01F);
		velocity.x = 50;
		float proportional = (targetY - entity.getPositionY());
		float derivative = (proportional - lastProportional) / dt;
		float u = kP * proportional + kP * derivative;
		velocity.y +=  (-10*velocity.y + 50F * u) * dt;

		entity.move(velocity.x * dt, velocity.y * dt);
		entity.rotate(angularVelocity * dt);

		PlayerPositionEvent evt = new PlayerPositionEvent(new Vector2(entity.getPositionX(), entity.getPositionY()));
		Engine.getInstance().getEventManager().queueEvent(evt);

		if(this.health == 0)
		{
			//send out player destroyed event
		}
	}
	
	@Override
	public boolean keyDown(Entity entity, int keycode) {
		// TODO Auto-generated method stub
		System.out.println("KeyDown");
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
		targetY = Gdx.graphics.getHeight()-screenY;
		return true;
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
		targetY = Gdx.graphics.getHeight()-screenY;
//		return super.touchDragged(entity, screenX, screenY, pointer);
		return true;
	}

	@Override
	public boolean mouseMoved(Entity entity, int screenX, int screenY) {
		// TODO Auto-generated method stub
		targetY = Gdx.graphics.getHeight()-screenY;
		return true;
	}

	@Override
	public boolean scrolled(Entity entity, int amount) {
		// TODO Auto-generated method stub
		return super.scrolled(entity, amount);
	}
}
