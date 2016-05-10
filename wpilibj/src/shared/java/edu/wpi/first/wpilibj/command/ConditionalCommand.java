package edu.wpi.first.wpilibj.command;
/**
 * A {@link ConditionalCommand} is a {@link Command} that starts one of two commands
 * 
 * <p>
 * A {@link ConditionalCommand} uses {@link ConditionalCommand#condition()} to determine whether it should run
 * {@link ConditionalCommand#onTrue} or {@link ConditionalCommand#onFalse}. 
 * </p>
 * 
 * <p>
 * A {@link ConditionalCommand} adds the proper {@link Command} to the {@link Scheduler} during {@link ConditionalCommand#initialize()}
 * and then {@link ConditionalCommand#isFinished()} will return true.
 * </p>
 * @author Patrick Murphy
 * @author Toby Macaluso
 * @see Command
 * @see Scheduler
 *
 */
public abstract class ConditionalCommand extends Command {
	/** The Command to execute if {@link ConditionalCommand#condition()} returns true*/
	private Command onTrue;
	/** The Command to execute if {@link ConditionalCommand#condition()} returns false*/
	private Command onFalse;
	/** Keeps track of if the command has finished*/
	private boolean isFinished = false;
	/**
	 * Creates a new ConditionalCommand with given onTrue and onFalse Commands
	 * @param onTrue The Command to execute if {@link ConditionalCommand#condition()} returns true
	 * @param onFalse The Command to execute if {@link ConditionalCommand#condition()} returns false
	 */
	public ConditionalCommand(Command onTrue,Command onFalse){
		this.onTrue = onTrue;
		this.onFalse = onFalse;
	}
	/**
	 * Creates a new ConditionalCommand with given onTrue and onFalse Commands
	 * @param name the name for this command group
	 * @param onTrue The Command to execute if {@link ConditionalCommand#condition()} returns true
	 * @param onFalse The Command to execute if {@link ConditionalCommand#condition()} returns false
	 */
	public ConditionalCommand(String name, Command onTrue, Command onFalse){
		super(name);
		this.onTrue = onTrue;
		this.onFalse = onFalse;
	}
	/**
	 * The Condition to test to determine which Command to run
	 * This must be overridden by subclasses
	 * @return true if {@link ConditionalCommand#onTrue} should be run, false if {@link ConditionalCommand#onFalse} should be run
	 */
	protected abstract boolean condition();
	@Override
	/**
	 * Calls {@link ConditionalCommand#condition()} and runs the proper command
	 */
	protected void initialize() {
		if(condition()){
			Scheduler.getInstance().add(onTrue);
		}
		else{
			Scheduler.getInstance().add(onFalse);
		}
		isFinished = true;
	}

	@Override
	protected void execute() {
	}
	
	@Override
	protected boolean isFinished() {
		return isFinished;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
