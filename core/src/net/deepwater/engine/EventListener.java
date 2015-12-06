package net.deepwater.engine;

public class EventListener
{
	public EventListener()
	{
		Engine.getInstance().getEventManager().registerEventListener(BaseEventData.class.getName(), this);
	}
	
	public void handleEvent(BaseEventData event) {}
};