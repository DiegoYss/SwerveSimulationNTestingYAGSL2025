package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static edu.wpi.first.units.Units.Meter;

import java.io.File;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Filesystem;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

public class SwerveDriveSubsystem extends SubsystemBase{
  File directory = new File(Filesystem.getDeployDirectory(),"swerve");
  SwerveDrive swerveDrive;

  public SwerveDriveSubsystem() {
    try {
      swerveDrive = new SwerveParser(directory).createSwerveDrive(Constants.maximumSpeed, new Pose2d(new Translation2d(
        Meter.of(1),
        Meter.of(4)),
        Rotation2d.fromDegrees(0)));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public SwerveDrive getSwerveDrive() {
    return swerveDrive;
  }

  public void driveFieldOriented(ChassisSpeeds Velocity) {
    swerveDrive.driveFieldOriented(Velocity);
  }

    public Command driveFieldOriented(Supplier<ChassisSpeeds> Velocity){
    return run(()-> {swerveDrive.driveFieldOriented(Velocity.get());
    });
  }  
  
  @Override
  public void periodic(){}
}