/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2014-2016. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "Commands/Scheduler.h"
#include "Commands/ConditionalCommand.h"
#include "command/MockCommand.h"
#include "gtest/gtest.h"
#include <DriverStation.h>
#include <RobotState.h>

bool condition = true;
bool GetCondition(){
	return condition;
}
class ConditionalCommandTest : public testing::Test {
public:
  ConditionalCommand *command;
  MockCommand *onTrue, *onFalse;
protected:
  virtual void SetUp() override {
	condition = true;
    RobotState::SetImplementation(DriverStation::GetInstance());
    Scheduler::GetInstance()->SetEnabled(true);
    onTrue = new MockCommand();
    onFalse = new MockCommand();
    command = new ConditionalCommand(GetCondition,onTrue, onFalse);
  }

  virtual void TearDown() override {
	  delete(command);
  }
  /**
   * Tears Down the Scheduler at the end of each test.
   * Must be called at the end of each test inside each test in order to prevent
   * them being deallocated
   * when they leave the scope of the test (causing a segfault).
   * This can not be done within the virtual void Teardown() method because it
   * is called outside of the scope of the test.
   */
  void TeardownScheduler() { Scheduler::GetInstance()->ResetAll(); }

};
TEST_F(ConditionalCommandTest, TestOnTrue){
	condition = true;;
	Scheduler::GetInstance() -> AddCommand(command);
	Scheduler::GetInstance() -> Run();
	Scheduler::GetInstance() -> Run();
	Scheduler::GetInstance() -> Run();
	EXPECT_TRUE(onTrue->GetInitializeCount() >0)<<"Did not initialize the true command\n";
	EXPECT_TRUE(onFalse->GetInitializeCount() == 0)<<"Initialized the false command\n";
	EXPECT_FALSE(command->IsRunning())<<"Conditional command has not ended\n";
	TeardownScheduler();
}

TEST_F(ConditionalCommandTest, TestOnFalse){
	condition = false;
	Scheduler::GetInstance() -> AddCommand(command);
	Scheduler::GetInstance() -> Run();
	Scheduler::GetInstance() -> Run();
	Scheduler::GetInstance() -> Run();
	EXPECT_TRUE(onFalse->GetInitializeCount() >0)<<"Did not initialize the false command";
	EXPECT_TRUE(onTrue->GetInitializeCount() == 0)<<"Initialized the true command";
	EXPECT_FALSE(command->IsRunning())<<"Conditional command has not ended";
	TeardownScheduler();
}
