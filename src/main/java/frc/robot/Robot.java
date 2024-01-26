// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Talon left1 = new Talon(RobotMap.kLeft1MotorId); 
  private Talon left2 = new Talon(RobotMap.kLeft2MotorId); 
  private Talon right1 = new Talon(RobotMap.kRight1MotorId); 
  private Talon right2 = new Talon(RobotMap.kRight2MotorId);
  private Talon spinningMotor = new Talon(RobotMap.kspinningMotorId);
  private MotorControllerGroup leftMotors = new MotorControllerGroup(left1, left2);
  private MotorControllerGroup rightMotors = new MotorControllerGroup(right1, right2);
  private DifferentialDrive differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
  private XboxController drivController = new XboxController(0);
  private XboxController operator = new XboxController(1);
  private Timer timer = new Timer();
  private DifferentialDrive drive;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    rightMotors.setInverted(true);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {

    timer.start();
    timer.reset();
  }

  @Override
  public void autonomousPeriodic() {

    
    if (timer.get()<RobotMap.Autotime1){
      drive.arcadeDrive(0.6, 0);

    } else {
      drive.arcadeDrive(0, 0);
    }
    

  }
  

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    differentialDrive.arcadeDrive(RobotMap.kMoveSpeed*drivController.getLeftY(), -RobotMap.kMoveSpeed*drivController.getRightX());

    if(operator.getRawButton(1)){
      spinningMotor.set(0.7);
    }
    else if (operator.getRawButton(2)){
      spinningMotor.set(-0.7);
    }
    else{
      spinningMotor.set(0);
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
