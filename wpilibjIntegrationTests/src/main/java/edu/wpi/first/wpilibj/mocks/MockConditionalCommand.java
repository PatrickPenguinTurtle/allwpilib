package edu.wpi.first.wpilibj.mocks;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class MockConditionalCommand extends ConditionalCommand {
	private boolean condition = false;
	public MockConditionalCommand(MockCommand onTrue, MockCommand onFalse){
		super(onTrue, onFalse);
	}
	@Override
	protected boolean condition() {
		return condition;
	}
	public void setCondition(boolean condition){
		this.condition = condition;
	}
}
