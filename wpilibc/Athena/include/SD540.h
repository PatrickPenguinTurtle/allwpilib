/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2016. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#pragma once

#include "PWMSpeedController.h"

/**
 * Mindsensors SD540 Speed Controller
 */
class SD540 : public PWMSpeedController {
 public:
  explicit SD540(uint32_t channel);
  virtual ~SD540() = default;
};
