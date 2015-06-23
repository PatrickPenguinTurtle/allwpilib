/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in $(WIND_BASE)/WPILib.  */
/*----------------------------------------------------------------------------*/
#pragma once

// If don't have C++11, define constexpr as const for WindRiver
#if (__cplusplus < 201103L)
	// MSVC is exception: see https://connect.microsoft.com/VisualStudio/feedback/details/763051/a-value-of-predefined-macro-cplusplus-is-still-199711l
	#if defined(_MSC_VER)
		#define _XKEYCHECK_H //this disables the rule that you can't use keywords as macros. very dangerous, but I can't think of another way
	#endif
	#define constexpr const
	#define nullptr NULL
#endif

// A macro to disallow the copy constructor and operator= functions
// This should be used in the private: declarations for a class
#define DISALLOW_COPY_AND_ASSIGN(TypeName) \
  TypeName(const TypeName&);               \
  void operator=(const TypeName&)

