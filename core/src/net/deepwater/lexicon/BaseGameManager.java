package net.deepwater.lexicon;

import com.badlogic.gdx.Gdx;

import net.deepwater.engine.BaseEventData;
import net.deepwater.engine.Entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by nickc on 12/8/2015.
 */

//handles progression thru game level/dumping ground lol
public class BaseGameManager {

    private static BaseGameManager gameManager = new BaseGameManager();

    public class DistanceAction
    {
        BaseEventData event;
        float playerDistance;
        float interval;
        float stop;
        boolean repeat;
    }

    public class TimedAction
    {
        BaseEventData event;
        double time;
        double interval;
        double stop;
        boolean repeat;
    }

    private double elapsedTime;
    private double elapsedDistance;

    private Entity player;

    private Vector<DistanceAction> distanceBasedEvents;

    private Vector<TimedAction> timeBasedEvents;

    private Vector<Spawner> spawners;

    BaseGameManager() {
        elapsedTime = 0;
        elapsedDistance = 0;
        distanceBasedEvents = new Vector<DistanceAction>();
        timeBasedEvents = new Vector<TimedAction>();
        spawners = new Vector<Spawner>();
        //add spawners here
    }

    public void setPlayer(Entity player)
    {
        this.player = player;
    }

    //an action is an event that is sent out if a player has reached a certain distance
    //for example: increase asteroids, increase point multiplier
    public void setAction(float playerDistance, BaseEventData event)
    {
        DistanceAction action = new DistanceAction();
        action.event = event;
        action.playerDistance = playerDistance;
        action.interval = -1;
        action.repeat = false;
        action.stop = -1;

        distanceBasedEvents.add(action);
    }

    public void setAction(float playerDistance, float interval, BaseEventData event)
    {
        DistanceAction action = new DistanceAction();
        action.event = event;
        action.playerDistance = playerDistance;
        action.interval = interval;
        action.repeat = true;
        action.stop = -1;

        distanceBasedEvents.add(action);
    }

    public void setAction(float playerDistance, float interval, float stop, BaseEventData event)
    {
        DistanceAction action = new DistanceAction();
        action.event = event;
        action.playerDistance = playerDistance;
        action.interval = interval;
        action.repeat = true;
        action.stop = stop;

        distanceBasedEvents.add(action);
    }

    //a timed event sent out at a specific time in the game
    public void setTimedAction(double time, BaseEventData event)
    {
        TimedAction action = new TimedAction();
        action.event = event;
        action.time = time;
        action.interval = -1;
        action.repeat = false;
        action.stop = -1;

        timeBasedEvents.add(action);
    }

    public void setTimedAction(double time, double interval, BaseEventData event)
    {
        TimedAction action = new TimedAction();
        action.event = event;
        action.time = time;
        action.interval = interval;
        action.repeat = true;
        action.stop = -1;

        timeBasedEvents.add(action);
    }

    public void setTimedAction(double time, double interval, double stop, BaseEventData event)
    {
        TimedAction action = new TimedAction();
        action.event = event;
        action.time = time;
        action.interval = interval;
        action.repeat = true;
        action.stop = stop;

        timeBasedEvents.add(action);
    }

    public Vector<BaseEventData> checkForDistanceBasedEvents()
    {
        return null;
    }

    public Vector<BaseEventData> checkForTimeBasedEvents()
    {
        return null;
    }

    public void update()
    {
        Iterator<Spawner> spawnIter = spawners.iterator();
        while(spawnIter.hasNext())
        {
            Spawner spawner = spawnIter.next();
            spawner.update();
        }

        elapsedTime += Gdx.graphics.getDeltaTime();
        elapsedDistance += player.getPositionX();
    }

    public static BaseGameManager getInstance()
    {
        return gameManager;
    }
}
