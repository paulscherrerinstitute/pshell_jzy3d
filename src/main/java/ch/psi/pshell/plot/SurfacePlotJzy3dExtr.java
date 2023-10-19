package ch.psi.pshell.plot;

import ch.psi.utils.IO;
import ch.psi.utils.Sys;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.Paths;

/**
 *
 */
public class SurfacePlotJzy3dExtr extends SurfacePlotJzy3d {

    static void extractLibraries() throws IOException {      
        String path = "natives/";
        boolean is64bits = System.getProperty("os.arch").contains("64");

        switch (Sys.getOSFamily()) {
            case Linux:
                if (is64bits) {
                    path += "linux-amd64/";
                } else {
                    path += "linux-i586/";
                }
                break;
            case Windows:
                if (is64bits) {
                    path += "windows-amd64/";
                } else {
                    path += "windows-i586/";
                }
                break;
            case Mac:
                path += "macosx-universal/";
                break;
        }

        String jar = IO.getExecutingJar(SurfacePlotJzy3dExtr.class);
        if (jar != null) {
            String folder = IO.getFolder(jar);
            String[] libs = IO.getJarChildren(jar, path);
            for (String lib : libs) {
                try {
                    File libFile = Paths.get(folder, lib).toFile();
                    String resourceName = path + lib;
                    //Don't overwrite existing libraries
                    //if ((!libFile.exists()) || (libFile.lastModified() < jarFile.lastModified())) {
                    if (!libFile.exists()) {
                        Logger.getLogger(SurfacePlotJzy3dExtr.class.getName()).log(Level.INFO, "Extracting: " + resourceName);
                        IO.extractZipFileContent(new File(jar), resourceName, libFile.getCanonicalPath());

                    }
                } catch (Throwable ex) {
                    Logger.getLogger(SurfacePlotJzy3dExtr.class.getName()).log(Level.SEVERE, "Error extracting: " + lib + " - " + ex.getMessage());
                    System.err.println(ex);
                }
            }
        } else {
            System.err.println("Cannot get Jzy3D jar file");
        }
    }

    static {
        //Extracting manually
        System.setProperty("jogamp.gluegen.UseTempJarCache", "false");
        try {
            extractLibraries();
        } catch (Throwable ex) {
            Logger.getLogger(SurfacePlotJzy3dExtr.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
        }
    }

}