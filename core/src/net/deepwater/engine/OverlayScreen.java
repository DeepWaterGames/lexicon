package net.deepwater.engine;

import java.util.ArrayList;
import java.util.Iterator;

//groups panels together in screen and can be switched in and out
//as a group 
public class OverlayScreen
{
	protected ArrayList<Entity> panels;
	
	OverlayScreen()
	{
		panels = new ArrayList<Entity>();
	}
	
	public Entity createPanelEntity()
	{
		Entity panel = new Entity();
		panel.setOverlay(true);
		panels.add(panel);
		return panel;
	}
	
	public Entity createPanelEntity(String name)
	{
		Entity panel = new Entity();
		panel.setOverlay(true);
		panel.setName(name);
		panels.add(panel);
		return panel;
	}
	
	public void removeEntityPanel(Entity panel)
	{
		panels.remove(panel);
	}
	
	public void removeEntityPanel(String name)
	{
		Iterator<Entity> iter = panels.iterator();
		while(iter.hasNext())
		{
			Entity panel = iter.next();
			if(panel.getName().equals(name))
			{
				panels.remove(panel);
				return;
			}
		}
	}
	
	public void removeEntityPanel(int index)
	{
		int count = 0;
		Iterator<Entity> iter = panels.iterator();
		while(iter.hasNext())
		{
			if(count == index)
			{
				Entity panel = iter.next();
				panels.remove(panel);
				return;
			}
			
			count++;
		}
	}
	
	public Entity getEntityPanel(String name)
	{
		Iterator<Entity> iter = panels.iterator();
		while(iter.hasNext())
		{
			Entity panel = iter.next();
			if(panel.getName().equals(name))
			{
				return panel;
			}
		}
		
		return null;
	}
	
	public Entity getEntityPanel(int index)
	{
		int count = 0;
		Iterator<Entity> iter = panels.iterator();
		while(iter.hasNext())
		{
			if(count == index)
			{
				Entity panel = iter.next();
				return panel;
			}
			
			count++;
		}
		
		return null;
	}
	
	public int getEntityPanelCount()
	{
		return panels.size();
	}
	
	public void update()
	{
		Iterator<Entity> iter = panels.iterator();
		while(iter.hasNext())
		{
			Entity panel = iter.next();
			panel.update();
		}
	}
	
	public void render()
	{
		Iterator<Entity> iter = panels.iterator();
		while(iter.hasNext())
		{
			Entity panel = iter.next();
			panel.render();
		}
	}
	
	public boolean notifyKeyDown(int keycode)
	{
		Iterator<Entity> iter = panels.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyKeyDown(keycode);
		}
		
		return true;
	}
	
	public boolean notifyKeyUp(int keycode)
	{
		Iterator<Entity> iter = panels.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyKeyUp(keycode);
		}
		
		return true;
	}
	
	public boolean notifyKeyTyped(char character) 
	{
		Iterator<Entity> iter = panels.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyKeyTyped(character);
		}
		
		return true;
	}

	public boolean notifyTouchDown(int screenX, int screenY, int pointer, int button) 
	{
		Iterator<Entity> iter = panels.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyTouchDown(screenX, screenY, pointer, button);
		}
		
		return true;
	}

	public boolean notifyTouchUp(int screenX, int screenY, int pointer, int button) 
	{
		Iterator<Entity> iter = panels.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyTouchUp(screenX, screenY, pointer, button);
		}
		
		return true;
	}

	public boolean notifyTouchDragged(int screenX, int screenY, int pointer) 
	{
		Iterator<Entity> iter = panels.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyTouchDragged(screenX, screenY, pointer);
		}
		
		return true;
	}

	public boolean notifyMouseMoved(int screenX, int screenY) 
	{
		Iterator<Entity> iter = panels.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyMouseMoved(screenX, screenY);
		}
		
		return true;
	}

	public boolean notifyScrolled(int amount) 
	{
		Iterator<Entity> iter = panels.iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.notifyScrolled(amount);
		}
		
		return true;
	}
};