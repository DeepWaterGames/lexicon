package net.deepwater.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class EventManager
{
	protected HashMap<String, ArrayList<EventListener>> eventMap;
	
	protected ArrayList<BaseEventData> eventQueue;
	
	EventManager()
	{
		eventMap = new HashMap<String, ArrayList<EventListener>>();
		eventQueue = new ArrayList<BaseEventData>();
	}
	
	public void registerEventListener(String eventType, EventListener listener)
	{
		if(eventMap.get(eventType) == null)
		{
			eventMap.put(eventType, new ArrayList<EventListener>());
		}
		eventMap.get(eventType).add(listener);
	}
	
	public void unregisterEventListener(String eventType, EventListener listener)
	{
		eventMap.get(eventType).remove(listener);
	}
	
	public void triggerEvent(BaseEventData event)
	{
		String eventType = event.getClass().getName();
		
		if(eventMap.get(eventType) == null)
		{
			return;
		}
		
		ArrayList<EventListener> list = eventMap.get(eventType);
		//iterate through list
		Iterator<EventListener> iter = list.iterator();
		while(iter.hasNext())
		{
			EventListener listener = iter.next();
			listener.handleEvent(event);
		}
	}
	
	public void queueEvent(BaseEventData event)
	{
		String eventType = event.getClass().getName();
		
		if(eventMap.get(eventType) != null)
		{
			eventQueue.add(event);
		}
	}
	
	public void abortEvent(BaseEventData event)
	{
		String eventType = event.getClass().getName();
		
		if(eventMap.get(eventType) != null)
		{
			eventQueue.remove(event);
		}
	}
	
	public void update()
	{
		for (BaseEventData event : eventQueue)
		{
			String eventType = event.getClass().getName();
			for (EventListener listener : eventMap.get(eventType))
			{
				listener.handleEvent(event);
			}
		}
		
		eventQueue.clear();
	}
};