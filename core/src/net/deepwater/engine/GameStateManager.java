package net.deepwater.engine;

import java.util.Stack;

/**
 * Created by Kyle on 9/6/2015.
 */
public class GameStateManager {
    Stack<GameState> stateStack;

    public GameStateManager() {
        stateStack = new Stack<GameState>();
    }

    public void pushState(GameState state) {
        if (stateStack.size() > 0) {
            stateStack.peek().pause();
        }
        stateStack.push(state);
        state.start();
    }

    public void popState() {
        stateStack.peek().stop();
        stateStack.pop();
        stateStack.peek().resume();
    }

    public void update(float dt) {
        if (stateStack.size() > 0) {
            stateStack.peek().update(dt);
        }
    }

    public void render() {
        if (stateStack.size() > 0) {
            stateStack.peek().render();
        }
    }
}
