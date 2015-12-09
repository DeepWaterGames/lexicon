package net.deepwater.lexicon;

import com.badlogic.gdx.Gdx;

import net.deepwater.engine.BaseEventData;
import net.deepwater.engine.Entity;

import java.util.HashMap;

/**
 * Created by nickc on 12/8/2015.
 */

//handles progression thru game level
public class BaseGameManager {
    private double elapsedTime;

    private Entity player;

    private HashMap<Float, BaseEventData> distanceBasedEvents;

    private HashMap<Double, BaseEventData> timeBasedEvents;

    BaseGameManager(Entity player) {
        elapsedTime = 0;
        this.player = player;
        distanceBasedEvents = new HashMap<Float, BaseEventData>();
        timeBasedEvents = new HashMap<Double, BaseEventData>();
    }

    //an action is an event that is sent out if a player has reached a certain distance
    //for example: increase asteroids, increase point multiplier
    public void setAction(float playerDistance, BaseEventData event)
    {

    }

    //if interval is negative, this event is sent out every frame from the specified distance on
    public void setAction(float playerDistance, float interval, BaseEventData event)
    {

    }

    //a timed event sent out at a specific time in the game
    public void setTimedAction(double time, BaseEventData event)
    {

    }

    public HashMap<Float, BaseEventData> checkForDistanceBasedEvents()
    {
        return null;
    }

    public HashMap<Double, BaseEventData> checkForTimeBasedEvents()
    {
        return null;
    }

    public void update()
    {
        elapsedTime += Gdx.graphics.getDeltaTime();
    }
}
