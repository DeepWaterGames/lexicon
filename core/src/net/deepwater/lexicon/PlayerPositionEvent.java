package net.deepwater.lexicon;

import com.badlogic.gdx.math.Vector2;
import net.deepwater.engine.BaseEventData;

/**
 * Created by Kyle on 12/5/2015.
 */
public class PlayerPositionEvent extends BaseEventData {
    public Vector2 position;
    PlayerPositionEvent(Vector2 position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return PlayerPositionEvent.class.getName();
    }
}
