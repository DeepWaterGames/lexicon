package net.deepwater.engine;

import java.util.ArrayList;
import java.util.Iterator;

public class OverlayManager
{
	protected ArrayList<OverlayScreen> screens;

    protected int activeScreen;
	
	public OverlayManager()
	{
		screens = new ArrayList<OverlayScreen>();
        activeScreen = -1;
	}

    public void setActiveScreen(int activeScreen)
    {
        this.activeScreen = activeScreen;
    }

    public int getActiveScreen()
    {
        return this.activeScreen;
    }
	
	public OverlayScreen createOverlayScreen(boolean setActive)
	{
		OverlayScreen screen = new OverlayScreen();
		screens.add(screen);
        if(setActive)
        {
            activeScreen = screens.size() - 1;
        }
		return screen;
	}
	
	public OverlayScreen getOverlayScreen(int index)
	{
		int count = 0;
		Iterator<OverlayScreen> iter = screens.iterator();
		while(iter.hasNext())
		{
			if(count == index)
			{
				OverlayScreen screen = iter.next();
				return screen;
			}
			
			count++;
		}

		return null;
	}
	
	public void updateOverlays()
    {
        OverlayScreen activeScreen = getOverlayScreen(this.activeScreen);
        if(activeScreen != null)
        {
            activeScreen.update();
        }
	}
	
	public void renderOverlays()
	{
        OverlayScreen activeScreen = getOverlayScreen(this.activeScreen);
        if(activeScreen != null)
        {
            activeScreen.render();
        }
	}
	
	public boolean notifyKeyDown(int keycode)
	{
        OverlayScreen activeScreen = getOverlayScreen(this.activeScreen);
        if(activeScreen != null)
        {
            activeScreen.notifyKeyDown(keycode);
        }
		
		return true;
	}
	
	public boolean notifyKeyUp(int keycode)
	{
        OverlayScreen activeScreen = getOverlayScreen(this.activeScreen);
        if(activeScreen != null)
        {
            activeScreen.notifyKeyUp(keycode);
        }

        return true;
	}
	
	public boolean notifyKeyTyped(char character) 
	{
        OverlayScreen activeScreen = getOverlayScreen(this.activeScreen);
        if(activeScreen != null)
        {
            activeScreen.notifyKeyTyped(character);
        }
		
		return true;
	}

	public boolean notifyTouchDown(int screenX, int screenY, int pointer, int button) 
	{
        OverlayScreen activeScreen = getOverlayScreen(this.activeScreen);
        if(activeScreen != null)
        {
            activeScreen.notifyTouchDown(screenX, screenY, pointer, button);
        }
		
		return true;
	}

	public boolean notifyTouchUp(int screenX, int screenY, int pointer, int button) 
	{
		Iterator<OverlayScreen> iter = screens.iterator();
		while(iter.hasNext())
		{
			OverlayScreen screen = iter.next();
			screen.notifyTouchUp(screenX, screenY, pointer, button);
		}
		
		return true;
	}

	public boolean notifyTouchDragged(int screenX, int screenY, int pointer) 
	{
        OverlayScreen activeScreen = getOverlayScreen(this.activeScreen);
        if(activeScreen != null)
        {
            activeScreen.notifyTouchDragged(screenX, screenY, pointer);
        }
		
		return true;
	}

	public boolean notifyMouseMoved(int screenX, int screenY) 
	{
        OverlayScreen activeScreen = getOverlayScreen(this.activeScreen);
        if(activeScreen != null)
        {
            activeScreen.notifyMouseMoved(screenX, screenY);
        }
		
		return true;
	}

	public boolean notifyScrolled(int amount) 
	{
        OverlayScreen activeScreen = getOverlayScreen(this.activeScreen);
        if(activeScreen != null)
        {
            activeScreen.notifyScrolled(amount);
        }
		
		return true;
	}
};