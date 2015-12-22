package net.deepwater.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import net.deepwater.lexicon.PlayerPositionEvent;

public class CameraObserver
{
    CameraEventListener evtListener;
    float lastX;
    public CameraObserver()
    {
        evtListener = new CameraEventListener();
        lastX = -100;
    }

    public void onUpdate(Camera camera)
    {
        camera.get().position.set(evtListener.playerPosition.x + Gdx.graphics.getWidth() * .3F, camera.get().position.y, camera.get().position.z);
    }

    public boolean keyDown(Camera Camera, int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean keyUp(Camera camera, int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean keyTyped(Camera camera, char character) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean touchDown(Camera camera, int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean touchUp(Camera camera, int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean touchDragged(Camera camera, int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean mouseMoved(Camera camera, int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean scrolled(Camera camera, int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    private class CameraEventListener extends EventListener {
        public Vector2 playerPosition = new Vector2();
        public CameraEventListener() {
             Engine.getInstance().getEventManager().registerEventListener(PlayerPositionEvent.class.getName(), this);
        }

        @Override
        public void handleEvent(BaseEventData data) {
            if (data.getClass().getName().equals(PlayerPositionEvent.class.getName())) {
                playerPosition = ((PlayerPositionEvent)data).position;
            }
        }
    }
}
