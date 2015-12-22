package net.deepwater.lexicon;

import net.deepwater.engine.BaseEventData;
import net.deepwater.engine.EventListener;
import net.deepwater.engine.GameStateManager;

/**
 * Created by nickc on 12/20/2015.
 */
public class BaseGameManager extends EventListener {
    private static BaseGameManager gameManager = new BaseGameManager();

    public BaseGameManager()
    {
        GameStateManager.getInstance().start(new GameStatePlay());
    }

    @Override
    public void handleEvent(BaseEventData event)
    {
        String eventName = event.getClass().getName();
        if(eventName.equals(""))
        {
//            GameStateManager.getInstance().setActiveState(getInt(GameStateEnum.GAME_STATE_PLAY));
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
