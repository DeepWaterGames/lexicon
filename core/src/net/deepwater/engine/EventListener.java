package net.deepwater.engine;

public class EventListener
{
	public EventListener()
	{
		Engine.getInstance().getEventManager().registerEvent("net.deepwater.engine.BaseEventData", this);
	}
	
	public void handleEvent(BaseEventData event) {}
};