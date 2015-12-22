package net.deepwater.engine;

import java.util.Stack;
import java.util.Vector;

/**
 * Created by Kyle on 9/6/2015.
 */
public class GameStateManager extends EventListener{
    protected Stack<GameState> states;

    private static GameStateManager gameStateManager = new GameStateManager();

    public GameStateManager() {
        states =  new Stack<GameState>();
    }

    public void start(GameState s)
    {
        if (!this.states.isEmpty())
            this.states.peek().pause();
        states.push(s);
        this.states.peek().start();
    }

    public void update(float dt)
    {
        if (!this.states.isEmpty())
            this.states.peek().update(dt);
    }

    public void pause()
    {
        if (!this.states.isEmpty())
            this.states.peek().pause();
    }

    public void resume()
    {
        if (!this.states.isEmpty())
            this.states.peek().resume();
    }

    public void stop() {
        this.states.peek().stop();
        this.states.pop();
        if (!this.states.isEmpty())
            this.states.peek().resume();
    }

    public static GameStateManager getInstance()
    {
        return gameStateManager;
    }
}
