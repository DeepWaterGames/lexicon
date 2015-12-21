package net.deepwater.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.*;

public class EntityManager {
    private ArrayList<Entity> entities;
    private Camera camera;
	private com.badlogic.gdx.graphics.OrthographicCamera overlayCamera;

    public EntityManager() {
        // TODO Auto-generated constructor stub
        entities = new ArrayList<Entity>();
        camera = new Camera();
		overlayCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		overlayCamera.translate(overlayCamera.viewportWidth / 2, overlayCamera.viewportHeight / 2);
    }

	public Camera getCamera() {
		//this has to be an net.deepwater.engine.Entity but...
		return this.camera;
	}

	public OrthographicCamera getOverlayCamera() {
		//this has to be an net.deepwater.engine.Entity but...
		return this.overlayCamera;
	}
	
	public Entity createEntity()
	{
		Entity entity = new Entity();
		entity.init();
		entities.add(entity);
		return entity;

	}
	
	public Entity createEntity(String name)
	{
		Entity entity = new Entity();
		entity.setName(name);
		entity.init();
		entities.add(entity);
		return entity;
	}
	
	//remove and get entities
	public void removeEntity(Entity entity)
	{
		entities.remove(entity);
	}
	
	public void removeEntity(String name)
	{
		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext())
		{
			Entity entity = iter.next();
			if(entity.getName().equals(name))
			{
				entities.remove(entity);
				return;
			}
		}
	}
	
	public void removeEntity(int index)
	{
		int count = 0;
		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext())
		{
			if(count == index)
			{
				Entity entity = iter.next();
				entities.remove(entity);
				return;
			}
			
			count++;
		}
	}

	public void clearEntities()
	{
		entities.clear();
	}
	
	public Entity getEntity(String name)
	{
		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext())
		{
			Entity entity = iter.next();
			if(entity.getName().equals(name))
			{
				return entity;
			}
		}
		
		return null;
	}
	
	public Entity getEntity(int index)
	{
		int count = 0;
		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext())
		{
			if(count == index)
			{
				Entity entity = iter.next();
				return entity;
			}
			
			count++;
		}
		
		return null;
	}
	
	public int getEntityCount()
	{
		return entities.size();
	}
	
	public void updateEntities()
	{
        camera.update();

		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.update();
		}
	}
	
	public void renderEntities()
	{
		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.render();
		}
	}
	
	public boolean notifyKeyDown(int keycode)
	{
        camera.notifyKeyDown(keycode);

		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyKeyDown(keycode);
		}
		
		return true;
	}
	
	public boolean notifyKeyUp(int keycode)
	{
        camera.notifyKeyUp(keycode);

		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyKeyUp(keycode);
		}
		
		return true;
	}
	
	public boolean notifyKeyTyped(char character) 
	{
        camera.notifyKeyTyped(character);

		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyKeyTyped(character);
		}
		
		return true;
	}

	public boolean notifyTouchDown(int screenX, int screenY, int pointer, int button) 
	{
        camera.notifyTouchDown(screenX, screenY, pointer, button);

		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyTouchDown(screenX, screenY, pointer, button);
		}
		
		return true;
	}

	public boolean notifyTouchUp(int screenX, int screenY, int pointer, int button) 
	{
        camera.notifyTouchUp(screenX, screenY, pointer, button);

		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyTouchUp(screenX, screenY, pointer, button);
		}
		
		return true;
	}

	public boolean notifyTouchDragged(int screenX, int screenY, int pointer) 
	{
        camera.notifyTouchDragged(screenX, screenY, pointer);

		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyTouchDragged(screenX, screenY, pointer);
		}
		
		return true;
	}

	public boolean notifyMouseMoved(int screenX, int screenY) 
	{
        camera.notifyMouseMoved(screenX, screenY);

		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyMouseMoved(screenX, screenY);
		}
		
		return true;
	}

	public boolean notifyScrolled(int amount) 
	{
        camera.notifyScrolled(amount);

		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyScrolled(amount);
		}
		
		return true;
	}
	
	//returns list of entities that intersect
	public ArrayList<Entity> executeQuery(Entity e)
	{
		/*if(e.getBounds() == null)
		{
			return new ArrayList();
		}
		
		ArrayList<net.deepwater.engine.Entity> result = new ArrayList();
		
		Iterator<net.deepwater.engine.Entity> iter = entities.iterator();
		while(iter.hasNext())
		{
			net.deepwater.engine.Entity e2 = iter.next();
			if(e2.getBounds() != null)
			{
				if(e.getBounds().intersects(e2.getBounds()))
				{
					result.add(e2);
				}
			}
		}
		
		return result; */
		return null;
	}
}
