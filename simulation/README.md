## Simulation Directory

Observe the following directory structure

.
|-- frc_gazebo_plugins (contains Gazebo Plugins)
|   |-- clock
|   |-- dc_motor
|   |-- encoder
|   |-- gyro
|   |-- plugins
|   |-- pneumatic_piston
|   |-- potentiometer
|   |-- rangefinder
|   |-- servo
|   
|-- frcsim.bat (launches gazebo with model/plugin paths on windows)
|-- frcsim.sh (launches gazebo with model/plugin paths on linux/mac)
|-- install.sh (convenient linux script to install Eclipse, Eclipse Plugins, and Gazebo)
|-- JavaGazebo (java library used by java simulation. Equivelant of gazebo_transport I believe)
|-- SimDS (linux driverstation)

The gazbeo plugins are currently built with CMake. 
Eventually they will be built with gradle. 
Read the Readme in that folder for more information

All of this is delivered to students via the eclipse plugins