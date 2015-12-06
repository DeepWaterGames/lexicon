package net.deepwater.lexicon;
import com.badlogic.gdx.Gdx;

import java.util.*;
import net.deepwater.engine.*;
import net.deepwater.engine.EventListener;

public class AsteroidSpawner extends EventListener
{
    protected int maxNumBarriers = 10;
    protected int spawnRate = 40; //40%
    protected float baseSpawnPosition = 0;

    protected ArrayList<Entity> barriers;

    protected static AsteroidSpawner spawner = new AsteroidSpawner();

    public AsteroidSpawner()
    {
        barriers = new ArrayList<Entity>();
    }

	/*@Override
	public void handleEvent(BaseEventData event)
	{
		maxNumBarriers += ((IncreaseBarriersEvent)event).getAmount();
	}*/

    public void update()
    {
        float position = 0;
        float step = Gdx.graphics.getWidth() / maxNumBarriers;

        Random random = new Random();

        if(random.nextInt(10) < 5) //50% spawn frequency
        {
            Entity e = Engine.getInstance().getEntityManager().createEntity();
            e.setTextureName("Android/assets/badlogic.jpg");
            random = new Random();
            float variance = random.nextInt(41) - 20; //number between -20 and 20
            e.setPosition(baseSpawnPosition + position + variance, random.nextInt(1080));
            System.out.println("Spawned at: " + e.getPositionX() + "," + e.getPositionY());

        }

        baseSpawnPosition += step;

        //TODO: loop thru and if the asteroid is outta camera sight, then delete it from entity manager

    }

    public static AsteroidSpawner getInstance()
    {
        return spawner;
    }
}