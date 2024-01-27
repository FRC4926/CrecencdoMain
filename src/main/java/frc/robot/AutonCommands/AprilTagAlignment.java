// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.AutonCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.RobotContainer;

public class AprilTagAlignment extends Command {
  double turnEffort;
  double driveEffort;
  PIDController alignPidController = new PIDController(0.05, 0, 0);

  /** Creates a new AprilTagAlignmentCommand. */
  public AprilTagAlignment() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.Subsystems.m_limelightSubsystem);
    alignPidController.setSetpoint(0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.Subsystems.m_limelightSubsystem.runLime();
    turnEffort = alignPidController.calculate(RobotContainer.Subsystems.m_limelightSubsystem.getX());
      RobotContainer.Subsystems.m_driveSubsystem.drive(0, -turnEffort);
    }    
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return alignPidController.getPositionError()<1 && RobotContainer.Subsystems.m_driveSubsystem.frontLeftMotor.getEncoder().getVelocity()<5;
  }
}
