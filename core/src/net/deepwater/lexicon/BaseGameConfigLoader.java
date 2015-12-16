package net.deepwater.lexicon;

import com.badlogic.gdx.graphics.g3d.particles.ParticleSorter;
import com.sun.corba.se.impl.orbutil.ObjectUtility;
import com.sun.deploy.util.ArrayUtil;

import net.deepwater.engine.BaseEventData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import sun.nio.cs.ArrayDecoder;

import static net.deepwater.lexicon.BaseGameManager.*;

/**
 * Created by nickc on 12/8/2015.
 */
public class BaseGameConfigLoader
{
    private BaseGameManager gameManager;

    private static BaseGameConfigLoader loader = new BaseGameConfigLoader();

    BaseGameConfigLoader()
    {
    }

    public void setGameManager(BaseGameManager manager)
    {
        this.gameManager = manager;
    }

    //make a parser so it can load files that specify actions
    //each action has a specific "keyword" that allows it to be referenced from file
    public void loadFromFile(String fileName)
    {
        try {
            BufferedReader file = new BufferedReader(new FileReader(fileName));
            String line = null;
            while ((line = file.readLine()) != null)
            {
                char[] characters = line.toCharArray();

                Vector<Character> word = new Vector<Character>();

                String actionType = null;
                String start = null;
                String interval = null;
                String end = null;
                String eventType = null;

                for(int i = 0; i < characters.length; i++)
                {
                    if(characters[i] == ' ') {
                        //assume theres space... process the word
                        String s = new String(String.valueOf(word.toArray()));

                        if(actionType == null)
                        {
                            actionType = s;
                        }
                        else if(start == null)
                        {
                            start = s;
                        }
                        else if(interval == null)
                        {
                            interval = s;
                        }
                        else if(end == null)
                        {
                            end = s;
                        }
                        else if(eventType == null)
                        {
                            eventType = s;
                        }

                        word.clear();
                        continue;
                    }

                    word.add(characters[i]);
                }

                this.processAction(actionType, start, interval, end, eventType);

                word.clear();
            }

            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void processAction(String action, String start, String interval, String end, String event)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        int type = -1;
        if(action.equals("DistanceAction"))
        {
            type = 0;
        }

        else if(action.equals("TimedAction"))
        {
            type = 1;
        }

        double s = start.equals("-") ? -1 : Double.valueOf(start);
        double i = interval.equals("-") ? -1 : Double.valueOf(interval);
        double e = end.equals("-") ? -1 : Double.valueOf(end);

        Class a = Class.forName(event);
        BaseEventData eventData = (BaseEventData) a.newInstance();

        if(type == 0)
        {
            if((s > -1) && (i > -1) && e > -1)
            {
                BaseGameManager.getInstance().setAction((float) s, (float) i, (float) e, eventData);
            }
            else if((s > -1) && (i > -1) && (e == -1))
            {
               BaseGameManager.getInstance().setAction((float) s, (float) i, eventData);
            }
            else if((s > -1) && (i == -1) && (e == -1))
            {
                BaseGameManager.getInstance().setAction((float) s, eventData);
            }
        }
        else if(type == 1)
        {
            if((s > -1) && (i > -1) && e > -1)
            {
                BaseGameManager.getInstance().setTimedAction((double) s, (double) i, (double) e, eventData);
            }
            else if((s > -1) && (i > -1) && (e == -1))
            {
                BaseGameManager.getInstance().setTimedAction((double) s, (double) i, eventData);
            }
            else if((s > -1) && (i == -1) && (e == -1))
            {
                BaseGameManager.getInstance().setTimedAction((double) s, eventData);
            }
        }
    }

    public static BaseGameConfigLoader getInstance()
    {
        return loader;
    }
}
