package net.deepwater.lexicon;

import com.sun.corba.se.impl.orbutil.ObjectUtility;
import com.sun.deploy.util.ArrayUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import sun.nio.cs.ArrayDecoder;

/**
 * Created by nickc on 12/8/2015.
 */
public class BaseGameConfigLoader
{
    private BaseGameManager gameManager;

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
        }
    }

    public void processAction(String action, String start, String interval, String end, String event)
    {

    }
}
