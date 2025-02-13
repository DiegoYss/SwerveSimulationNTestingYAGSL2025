package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Subsystems.SwerveDriveSubsystem;
import swervelib.SwerveInputStream;

public class RobotContainer {
  private final SwerveDriveSubsystem drivebase = new SwerveDriveSubsystem();
  private final CommandXboxController m_DriverController = new CommandXboxController(0);

  SwerveInputStream driveAngularVelocity = SwerveInputStream.of(
    drivebase.getSwerveDrive(), 
    () -> m_DriverController.getLeftX() * -1, 
    () -> m_DriverController.getLeftY() * -1)
    .withControllerRotationAxis(m_DriverController::getRightX)
    .deadband(OperatorConstants.DeadBand)
    .scaleTranslation(0.8)
    .allianceRelativeControl(true);

  SwerveInputStream driveDriectAngle = driveAngularVelocity.copy().withControllerHeadingAxis(
    m_DriverController::getRightX, 
    m_DriverController::getRightY)
    .headingWhile(true);

  Command driveFieldOrientedDriectAngle = drivebase.driveFieldOriented(driveDriectAngle);

  Command driveFieldOrientedAngularVelocity = drivebase.driveFieldOriented(driveAngularVelocity);

  public RobotContainer() {
    configureBindings();

    drivebase.setDefaultCommand(driveFieldOrientedAngularVelocity);
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
