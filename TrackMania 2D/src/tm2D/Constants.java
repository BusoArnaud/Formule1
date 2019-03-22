package tm2D;

import java.io.File;

public interface Constants {
  String OS = System.getProperty("os.name").toLowerCase();
  String RELATIVE_PATH = (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) ? "TrackMania 2D" + File.separatorChar : "";
  String RELATIVE_PATH_IMAGE_CIRCUIT = RELATIVE_PATH + "ImagesCircuit" + File.separatorChar;
}
