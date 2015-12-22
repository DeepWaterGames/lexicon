package net.deepwater.lexicon;
import com.badlogic.gdx.Gdx;

import java.util.*;
import net.deepwater.engine.*;
import net.deepwater.engine.EventListener;

public class AsteroidSpawner extends Spawner
{
    protected int maxNumBarriers = 10;
    protected float baseSpawnPosition = 0;

    int lastSpawn;

    protected ArrayList<Entity> barriers;

    protected Random random;

    public AsteroidSpawner()
    {
        barriers = new ArrayList<Entity>();
        random = new Random();
        random.setSeed(new Date().getTime());
    }

	@Override
	public void handleEvent(BaseEventData event)
	{
        if(event.getClass().getName().equals("EventIncreaseAsteroidSpawn"))
        {
            maxNumBarriers += ((EventIncreaseAsteroidSpawn) event).amount;
        }
	}

    @Override
    public void update()
    {
        float step = Gdx.graphics.getWidth() / maxNumBarriers;

        lastSpawn++;

        if(random.nextInt(Math.max(1, 70 - lastSpawn)) == 0) //50% spawn frequency
        {
            int height = random.nextInt(Gdx.graphics.getHeight());
            Entity e = Engine.getInstance().getEntityManager().createEntity();
            e.setTextureName("Android/assets/asteroid.png");
            e.setPosition(baseSpawnPosition + Engine.getInstance().getEntityManager().getCamera().get().position.x + Gdx.graphics.getWidth() / 2 + 200, height);
           // System.out.println("Spawned at: " + e.getPositionX() + "," + e.getPositionY());
            e.setScale(.5F);
            barriers.add(e);
            lastSpawn = 0;
        }

        Iterator<Entity> iter = barriers.iterator();
        while (iter.hasNext()) {
            Entity asteroid = iter.next();
            if (asteroid.getPositionX() < Engine.getInstance().getEntityManager().getCamera().get().position.x - Gdx.graphics.getWidth() / 2) {
                barriers.remove(iter);
                Engine.getInstance().getEntityManager().removeEntity(asteroid);
                System.out.println("Reached");
            }
        }
    }
}