package net.deepwater.engine;

public class BasePanelEvent extends BaseEventData
{
	public enum EventType
	{
		KEY_DOWN,
		KEY_UP,
		KEY_TYPED,
		TOUCH_DOWN,
		TOUCH_UP,
		TOUCH_DRAGGED,
		MOUSE_MOVED
	};
	
	Entity panel;
	EventType type;
	
	BasePanelEvent(Entity panel, EventType eventType)
	{
		this.panel = panel;
		this.type = eventType;
	}
	
	public Entity getEntityPanel()
	{
		return this.panel;
	}
	
	public EventType getEventType()
	{
		return this.type;
	}
	
	@Override
	public String getName()
	{
		return "net.deepwater.engine.BasePanelEvent";
	}
}