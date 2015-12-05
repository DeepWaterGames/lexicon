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
	
	public void registerEvent(String eventType, EventListener listener)
	{
		if(eventMap.get(eventType) == null)
		{
			ArrayList<EventListener> list = new ArrayList<EventListener>();
			list.add(listener);
			eventMap.put(eventType, list);
		}
		else
		{
			ArrayList<EventListener> list = eventMap.get(eventType);
			list.add(listener);
		}
	}
	
	public void unregisterEvent(String eventType, EventListener listener)
	{
		if(eventMap.get(eventType) == null)
		{
			return;
		}
		else
		{
			ArrayList<EventListener> list = eventMap.get(eventType);
			list.remove(listener);
		}
	}
	
	public void triggerEvent(BaseEventData event)
	{
		String eventType = event.getName();
		
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
		String eventType = event.getName();
		
		if(eventMap.get(eventType) == null)
		{
			return;
		}
		else
		{
			eventQueue.add(event);
		}
	}
	
	public void abortEvent(BaseEventData event)
	{
		String eventType = event.getName();
		
		if(eventMap.get(eventType) == null)
		{
			return;
		}
		else
		{
			eventQueue.remove(event);
		}
	}
	
	public void update()
	{
		Iterator<BaseEventData> iter = eventQueue.iterator();
		while(iter.hasNext())
		{
			BaseEventData event = iter.next();
			
			String eventType = event.getName();
			
			ArrayList<EventListener> list = eventMap.get(eventType);
			//iterate through list
			Iterator<EventListener> listIter = list.iterator();
			while(listIter.hasNext())
			{
				EventListener listener = listIter.next();
				listener.handleEvent(event);
			}
		}
		
		eventQueue.clear();
	}
};