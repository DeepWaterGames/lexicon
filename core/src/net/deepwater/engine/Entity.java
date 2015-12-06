package net.deepwater.engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class Entity {
	protected final double pi = 3.1415926535;
	
	protected Texture texture;
	protected Sprite sprite;
	protected SpriteBatch batch;

	protected float scale;
	
	protected String name;
	
	protected String textureName;
	
	protected Rectangle bounds;

	protected Vector2 position;
	protected float angle;
	
	EntityObserver entityObserver;
	
	EntityVisitor visitor;
	
	boolean textureOutOfDate;

	boolean isOverlay;
	
	EntityAnimation animation;
	
	public Entity()
	{
		batch = new SpriteBatch();
		position = new Vector2();
		position.set(0F, 0F);
		angle = 0F;
		scale = 1F;
		textureName = null;
		textureOutOfDate = false;
		animation = new EntityAnimation(this);
	}

	public Entity(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	public EntityAnimation getAnimation()
	{
		return this.animation;
	}
	
	public void setTexture(String textureName, Texture texture)
	{
		this.textureName = textureName;
		this.texture = texture;
		this.sprite = new Sprite(this.texture);
		this.bounds = this.sprite.getBoundingRectangle();
	}

	public void setOverlay(boolean overlay)
	{
		this.isOverlay = overlay;
	}

	public boolean isOverlay()
	{
		return this.isOverlay;
	}
	
	public Texture getTexture()
	{
		return this.texture;
	}
	
	public void setBounds(Rectangle rect)
	{
		this.bounds = rect;
	}
	
	public void setBounds(float x, float y, float width, float height)
	{
		bounds.set(x, y, width, height);
	}
	
	public Rectangle getBounds()
	{
		return this.bounds;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setPosition(float posX, float posY)
	{
		position.set(posX, posY);
	}
	
	public float getPositionX()
	{
		return position.x;
	}
	
	public float getPositionY()
	{
		return position.y;
	}
	
	public void move(float movX, float movY)
	{
		position.add(movX, movY);
	}
	
	public void setScale(float factor)
	{
		scale = factor;
	}
	
	public void scale(float factor)
	{
		scale *= factor;
	}
	
	public float getScale()
	{
		return scale;
	}
	
	public void setRotation(float angle)
	{
		this.angle = angle;
	}
	
	public void rotate(float angle)
	{
		this.angle += angle;
	}
	
	public float getRotation()
	{
		return angle;
	}
	
	public void init()
	{
		if(textureName == null)
		{
			return;
		}
		
		if(Engine.getInstance().getAssetManager().isLoaded(textureName) == false)
		{
			Engine.getInstance().getAssetManager().load(textureName, Texture.class);
			Engine.getInstance().getAssetManager().finishLoading();
		}
		
		this.texture = Engine.getInstance().getAssetManager().get(textureName, Texture.class);
		if(this.texture == null)
		{
			return;
		}
		
		//dont use sprite
		this.sprite = new Sprite(this.texture);
		//calculate bounds from Texture
		bounds = this.sprite.getBoundingRectangle();
		//get rid of this
		if(this.entityObserver != null)
		{
			this.entityObserver.onInit(this);
		}
	}
	
	public void update()
	{
		//notify update observers
		if(this.textureOutOfDate)
		{
			init();
			this.textureOutOfDate = false;
		}
		
		if(sprite == null)
		{
			return;
		}
		
		this.animation.update();
	
		// TODO(Nick) translate down because of stupid libgdx bases the scaling off the bottom left corner
		this.sprite.setPosition(position.x - this.sprite.getWidth() / 2, position.y - this.sprite.getHeight() / 2);
		this.sprite.setRotation(angle);
		this.sprite.setScale(scale);

		if(isOverlay()) {
			batch.setProjectionMatrix(Engine.getInstance().getEntityManager().getOverlayCamera().combined);
		}
		else
		{
			batch.setProjectionMatrix(Engine.getInstance().getEntityManager().getCamera().get().combined);
		}
		
		if(this.entityObserver != null)
		{
			this.entityObserver.onUpdate(this);
		}
	}
	
	public void setTextureName(String name)
	{
		this.textureName = name;
		//flag for re-initialize
		this.textureOutOfDate = true;
	}
	
	public String getTextureName()
	{
		return this.textureName;
	}
	
	public void render()
	{
		if(sprite == null)
		{
			return;
		}
		
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}
	
	public void accept(EntityVisitor visitor)
	{
		visitor.visit(this);
	}
	
	public void setEntityObserver(EntityObserver observer)
	{
		this.entityObserver = observer;
	}
	
	public EntityObserver getEntityObserver()
	{
		return this.entityObserver;
	}
	
	public boolean notifyKeyDown(int keycode)
	{
		if(this.entityObserver == null)
		{
			return false;
		}
		
		this.entityObserver.keyDown(this, keycode);
		return true;
	}
	
	public boolean notifyKeyUp(int keycode)
	{
		if(this.entityObserver == null)
		{
			return false;
		}
		
		this.entityObserver.keyUp(this, keycode);
		return true;
	}
	
	public boolean notifyKeyTyped(char character) 
	{
		if(this.entityObserver == null)
		{
			return false;
		}
		
		this.entityObserver.keyTyped(this, character);
		return true;
	}

	public boolean notifyTouchDown(int screenX, int screenY, int pointer, int button) 
	{
		if(this.entityObserver == null)
		{
			return false;
		}
		
		this.entityObserver.touchDown(this, screenX, screenY, pointer, button);
		return true;
	}

	public boolean notifyTouchUp(int screenX, int screenY, int pointer, int button) 
	{
		if(this.entityObserver == null)
		{
			return false;
		}
		
		this.entityObserver.touchUp(this, screenX, screenY, pointer, button);
		return true;
	}

	public boolean notifyTouchDragged(int screenX, int screenY, int pointer) 
	{
		if(this.entityObserver == null)
		{
			return false;
		}
		
		this.entityObserver.touchDragged(this, screenX, screenY, pointer);
		return true;
	}

	public boolean notifyMouseMoved(int screenX, int screenY) 
	{
		if(this.entityObserver == null)
		{
			return false;
		}
		
		this.entityObserver.mouseMoved(this, screenX, screenY);
		return false;
	}

	public boolean notifyScrolled(int amount) 
	{
		if(this.entityObserver == null)
		{
			return false;
		}
		
		this.entityObserver.scrolled(this, amount);
		return true;
	}

}
