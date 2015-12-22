package net.deepwater.lexicon;

import com.badlogic.gdx.Gdx;

import net.deepwater.engine.BaseEventData;
import net.deepwater.engine.CameraObserver;
import net.deepwater.engine.Engine;
import net.deepwater.engine.Entity;
import net.deepwater.engine.EventListener;
import net.deepwater.engine.GameState;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by nickc on 12/8/2015.
 */

//handles progression thru game level/dumping ground lol
//change this to game state play
public class GameStatePlay extends GameState {

    public class DistanceAction
    {
        BaseEventData event;
        float playerDistance;
        float interval;
        float stop;
        boolean repeat;
        int numTimesRepeated = 0;
    }

    public class TimedAction
    {
        BaseEventData event;
        double time;
        double interval;
        double stop;
        boolean repeat;
        int numTimesRepeated = 0;
    }

    private double elapsedTime;
    private double elapsedDistance;

    private Entity player;

    private Vector<DistanceAction> distanceBasedEvents;

    private Vector<TimedAction> timeBasedEvents;

    private Vector<Spawner> spawners;

    GameStatePlay() {
        elapsedTime = 0;
        elapsedDistance = 0;
        distanceBasedEvents = new Vector<DistanceAction>();
        timeBasedEvents = new Vector<TimedAction>();
        spawners = new Vector<Spawner>();
        //add spawners here
        spawners.add(new AsteroidSpawner());
        setAction(1000, 200, 2000, new EventChangePlayerHealth());
        Engine.getInstance().getEventManager().registerEventListener(EventChangePlayerHealth.class.getName(), this);
    }

    @Override
    public void handleEvent(BaseEventData event)
    {
        if(event.getClass().getName().equals(EventChangePlayerHealth.class.getName()))
        {
            //now send out event that GameStatePlay is over and switch to GameStateMenu
            System.out.println("RSTHGFDGDFDHFGDHFGDGHFGHF");
        }
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
        Vector<BaseEventData> events = new Vector<BaseEventData>();

        for (DistanceAction action : distanceBasedEvents) {
            if (this.elapsedDistance >= action.playerDistance)
            {
                if(action.repeat == false && action.numTimesRepeated == 0)
                {
                    events.add(action.event);
                    action.numTimesRepeated++;
                }
                else if(action.repeat == true)
                {
                    if (action.interval == -1 || action.numTimesRepeated % action.interval == 0)
                        events.add(action.event);
                    action.numTimesRepeated++;
                }
            }
        }
        return events;
    }

    public Vector<BaseEventData> checkForTimeBasedEvents()
    {
        Vector<BaseEventData> events = new Vector<BaseEventData>();

        for (TimedAction action : timeBasedEvents) {
            if (this.elapsedTime >= action.time)
            {
                if((action.repeat == false) && (action.numTimesRepeated == 0))
                {
                    events.add(action.event);
                    action.numTimesRepeated++;
                }
                else if(action.repeat == true)
                {
                    if (action.interval == -1 || action.numTimesRepeated % action.interval == 0)
                        events.add(action.event);
                    action.numTimesRepeated++;
                }
            }
        }

        return events;
    }

    @Override
    public void update(float dt)
    {
        //send out events
        for (BaseEventData evt : checkForDistanceBasedEvents())
        {
            System.out.println("Reached");
            Engine.getInstance().getEventManager().queueEvent(evt);
        }

        for (BaseEventData evt : checkForTimeBasedEvents())
        {
            Engine.getInstance().getEventManager().queueEvent(evt);
        }

        for (Spawner spawner : spawners) {
            spawner.update();
        }

//        Vector<BaseEventData> timeEvents = checkForTimeBasedEvents();
//        Iterator<BaseEventData> tIter = timeEvents.iterator();
//        while(tIter.hasNext())
//        {
//            Engine.getInstance().getEventManager().triggerEvent(tIter.next());
//        }
//
//        Iterator<Spawner> spawnIter = spawners.iterator();
//        while(spawnIter.hasNext())
//        {
//            Spawner spawner = spawnIter.next();
//            spawner.update();
//        }

        elapsedTime += Gdx.graphics.getDeltaTime();
        elapsedDistance = player.getPositionX();
    }

    @Override
    public void render() {
        //remove this function
    }

    @Override
    public void start() {
        Engine.getInstance().getEntityManager().getCamera().get().translate(50, 0);
        Engine.getInstance().getEntityManager().getCamera().setCameraObserver(new CameraObserver());
        player = Engine.getInstance().getEntityManager().createEntity("Player");
        player.setTextureName("Android/assets/ship.png");
        player.setEntityObserver(new PlayerMovementObserver());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void stop() {
        //stop the game and switch to menu screen
        Engine.getInstance().getEntityManager().clearEntities();
        player = null;
        //wipe the spawners of entities
        //clean up everything
    }
}
