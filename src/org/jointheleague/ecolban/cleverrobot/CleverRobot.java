package org.jointheleague.ecolban.cleverrobot;

/*********************************************************************************************
 * Vic's ultrasonic sensor running with Erik's Clever Robot for Pi
 * version 0.9, 170227
 **********************************************************************************************/
import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class CleverRobot extends IRobotAdapter {
	Sonar sonar = new Sonar();
	private boolean tailLight;
	public CleverRobot(IRobotInterface iRobot) {
		super(iRobot);
	}
	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		CleverRobot rob = new CleverRobot(base);
		rob.setup();
		while (rob.loop()) {
		}
		rob.shutDown();
	}
	private void setup() throws Exception {
		driveDirect(100, 100);
	}
	//boolean on = true;
	private boolean loop() throws Exception {
		driveDirect(300,300);
		readSensors(100);
		//left turn
		if(isBumpRight()){
			driveDirect(-200,0);
			Thread.sleep(800);
		}
		//right turn
		if(isBumpLeft()){
			driveDirect(0,-200);
			Thread.sleep(800);
		}
		//back out to the left
		if(isBumpLeft() && isBumpRight()){
			driveDirect(-200,-100);
			Thread.sleep(1000);
		}
		
		//this.setTailLight(on);
		//on = !on;
		return true;
	}
	private void shutDown() throws IOException {
		reset();
		stop();
		closeConnection();
	}
}