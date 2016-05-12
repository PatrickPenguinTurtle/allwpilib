package edu.wpi.first.wpilibj.command;

import java.util.logging.Logger;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.first.wpilibj.mocks.MockCommand;

public class ConditionalCommandTest extends AbstractCommandTest {
	private static final Logger logger = Logger.getLogger(ConditionalCommandTest.class.getName());
	ConditionalCommand command;
	MockCommand onTrue, onFalse;
	Boolean state;
	@Override
	protected Logger getClassLogger() {
		return logger;
	}
	@Before
	public void initCommands(){
		state = new Boolean(true);
		onTrue = new MockCommand();
		onFalse = new MockCommand();
		command = new ConditionalCommand(() -> this.state, onTrue, onFalse);
	}
	@Test
	public void testOnTrue(){
		state = true;
		Scheduler.getInstance().add(command);
		Scheduler.getInstance().run();//init command and select onTrue
		Scheduler.getInstance().run();//init onTrue
		Scheduler.getInstance().run();
		assertTrue("Did not initialize the true command",onTrue.getInitializeCount()>0);
		assertTrue("Initialized the false command", onFalse.getInitializeCount()==0);
		assertFalse("Conditional Command has not stopped", command.isRunning());
	}
	@Test
	public void testOnFalse(){
		state = false;
		Scheduler.getInstance().add(command);
		Scheduler.getInstance().run();//init command and select onFalse
		Scheduler.getInstance().run();//init onFalse
		Scheduler.getInstance().run();
		assertTrue("Did not initialize the false command", onFalse.getInitializeCount()>0);
		assertTrue("Initialized the true command", onTrue.getInitializeCount()==0);
		assertFalse("Conditional Command has not stopped", command.isRunning());
	}
}
