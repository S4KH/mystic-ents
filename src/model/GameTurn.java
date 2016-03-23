package model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;

public class GameTurn extends Observable {
	
    Toolkit toolkit;
    Timer timer;
    
    public int gameTimer;

    public GameTurn() {
    	toolkit = Toolkit.getDefaultToolkit();
    }
    
    private void buildTimer() {
    	timer = new Timer();
        timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (--gameTimer == 0)
				{
					stop();
				}
				setChanged();
			    notifyObservers();
			}
    	}, GameConfig.getStartDelay(), GameConfig.getTimerPeriod());
    }

    public int getGameTimer() {
    	return gameTimer;
    }
    
    public void stop() {
    	timer.cancel();
    }    
    
    public void start() {
    	System.out.println("Start");
    	gameTimer = GameConfig.getMaxSeconds();
    	buildTimer();
    }
    
}