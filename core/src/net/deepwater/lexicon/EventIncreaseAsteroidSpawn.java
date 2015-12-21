package net.deepwater.lexicon;

import net.deepwater.engine.BaseEventData;

/**
 * Created by nickc on 12/20/2015.
 */
public class EventIncreaseAsteroidSpawn extends BaseEventData {
    public float amount;

    EventIncreaseAsteroidSpawn(float amount)
    {
        this.amount = amount;
    }

    @Override
    public String getName()
    {
        return EventIncreaseAsteroidSpawn.class.getName();
    }
}
