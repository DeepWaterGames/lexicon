package net.deepwater.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera
{
    protected OrthographicCamera camera;

    protected CameraObserver observer;

    public Camera()
    {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth/2, camera.viewportHeight/2);
    }

    public OrthographicCamera get()
    {
        return this.camera;
    }

    public void setCameraObserver(CameraObserver observer)
    {
        this.observer = observer;
    }

    public CameraObserver getCameraObserver()
    {
        return this.observer;
    }

    public void update()
    {
        this.camera.update();
        if(this.observer != null) {
            this.observer.onUpdate(this);
        }
    }

    public boolean notifyKeyDown(int keycode)
    {
        if(this.observer == null)
        {
            return false;
        }

        this.observer.keyDown(this, keycode);
        return true;
    }

    public boolean notifyKeyUp(int keycode)
    {
        if(this.observer == null)
        {
            return false;
        }

        this.observer.keyUp(this, keycode);
        return true;
    }

    public boolean notifyKeyTyped(char character)
    {
        if(this.observer == null)
        {
            return false;
        }

        this.observer.keyTyped(this, character);
        return true;
    }

    public boolean notifyTouchDown(int screenX, int screenY, int pointer, int button)
    {
        if(this.observer == null)
        {
            return false;
        }

        this.observer.touchDown(this, screenX, screenY, pointer, button);
        return true;
    }

    public boolean notifyTouchUp(int screenX, int screenY, int pointer, int button)
    {
        if(this.observer == null)
        {
            return false;
        }

        this.observer.touchUp(this, screenX, screenY, pointer, button);
        return true;
    }

    public boolean notifyTouchDragged(int screenX, int screenY, int pointer)
    {
        if(this.observer == null)
        {
            return false;
        }

        this.observer.touchDragged(this, screenX, screenY, pointer);
        return true;
    }

    public boolean notifyMouseMoved(int screenX, int screenY)
    {
        if(this.observer == null)
        {
            return false;
        }

        this.observer.mouseMoved(this, screenX, screenY);
        return false;
    }

    public boolean notifyScrolled(int amount)
    {
        if(this.observer == null)
        {
            return false;
        }

        this.observer.scrolled(this, amount);
        return true;
    }
}
