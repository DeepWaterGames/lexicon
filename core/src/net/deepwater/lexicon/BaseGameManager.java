package net.deepwater.lexicon;

import net.deepwater.engine.BaseEventData;
import net.deepwater.engine.EventListener;
import net.deepwater.engine.GameStateManager;

/**
 * Created by nickc on 12/20/2015.
 */
public class BaseGameManager extends EventListener {
    private static BaseGameManager gameManager = new BaseGameManager();

    protected enum GameStateEnum {
        GAME_STATE_PLAY,
        GAME_STATE_MENU,
        GAME_STATE_LOAD;
    }

    public int getInt(GameStateEnum state)
    {
        switch(state)
        {
            case GAME_STATE_PLAY:
                return 0;
            case GAME_STATE_MENU:
                return 1;
            case GAME_STATE_LOAD:
                return 2;
            default:
                return -1;
        }
    }

    public BaseGameManager()
    {
        GameStateManager.getInstance().addState(new GameStatePlay());
        GameStateManager.getInstance().addState(new GameStateMenu());
    }

    @Override
    public void handleEvent(BaseEventData event)
    {
        String eventName = event.getName();
        if(eventName.equals("EventStartGameStatePlay"))
        {
            GameStateManager.getInstance().setActiveState(getInt(GameStateEnum.GAME_STATE_PLAY));
        }
    }

    public void pause()
    {
        GameStateManager.getInstance().pause();
    }

    public void resume()
    {
        GameStateManager.getInstance().resume();
    }

    public void run()
    {
        GameStateManager.getInstance().update(1000);
    }

    public static BaseGameManager getInstance()
    {
        return gameManager;
    }
}
