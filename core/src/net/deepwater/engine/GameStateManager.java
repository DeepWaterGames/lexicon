package net.deepwater.engine;

import java.util.Stack;
import java.util.Vector;

/**
 * Created by Kyle on 9/6/2015.
 */
public class GameStateManager extends EventListener{
    protected Vector<GameState> states;
    protected int activeState = 0;

    private static GameStateManager gameStateManager = new GameStateManager();

    public GameStateManager() {
        states =  new Vector<GameState>();
    }

    //remove the push and pop to allow switching between states better
    //can be overriden

    public void addState(GameState state)
    {
        states.add(state);
    }

    public void removeState(GameState state)
    {
        states.remove(state);
    }

    public void setActiveState(int index)
    {
        if(index > states.size())
        {
            stop();
            this.activeState = index;
            start();
        }
    }

    public void start()
    {
        states.get(activeState).start();
    }

    public void update(float dt)
    {
        states.get(activeState).update(dt);
    }

    public void pause()
    {
        states.get(activeState).pause();
    }

    public void resume()
    {
        states.get(activeState).resume();
    }

    public void stop()
    {
        states.get(activeState).stop();
    }

    public static GameStateManager getInstance()
    {
        return gameStateManager;
    }
}
