package net.deepwater.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//might as well merge this class with net.deepwater.engine.Entity if its
//coupled so tightly with it
public class EntityAnimation
{
	protected Animation animationTrack;
	
	protected int frameRows;
	protected int frameColumns;
	
	protected TextureRegion currentFrame;
	
	protected float elapsedTime;
	
	protected Entity entity;
	
	public EntityAnimation(Entity entity)
	{
		this.entity = entity;
	}
	
	public Animation createAnimationTrack(int frameRows, int frameColumns, float frameDuration)
	{
		this.frameRows = frameRows;
		this.frameColumns = frameColumns;
		
		if(this.entity == null)
		{
			return null;
		}
		
		if(this.entity.getTexture() == null)
		{
			return null;
		}
		
		TextureRegion[][] tmp = TextureRegion.split(this.entity.getTexture(), this.entity.getTexture().getWidth() 
			/ frameColumns, this.entity.getTexture().getWidth() / frameRows);
			
		TextureRegion[] frames = new TextureRegion[frameRows * frameColumns];
		//convert 2D array into 1D array
		int index = 0;
        for (int i = 0; i < frameRows; i++) 
		{
            for (int j = 0; j < frameColumns; j++) 
			{
                frames[index++] = tmp[i][j];
            }
        }
		
		this.animationTrack = new Animation(frameDuration, frames);
		this.elapsedTime = 0F;
		return animationTrack;
	}
	
	public Animation getAnimationTrack()
	{
		return this.animationTrack;
	}
	
	public void update()
	{
		//progress animation
		if(this.animationTrack != null)
		{
			elapsedTime = Gdx.graphics.getDeltaTime();
			currentFrame = animationTrack.getKeyFrame(elapsedTime, true);
			this.entity.setTexture(entity.getTextureName(), currentFrame.getTexture());
		}
	}
};
//net.deepwater.engine.Entity::setAnimator(net.deepwater.engine.EntityAnimation animator);