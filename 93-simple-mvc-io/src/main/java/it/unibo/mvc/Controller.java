package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String HOME = System.getProperty("user.home");
    private static final String DEFAULT = "output.txt";

    private File file = new File(HOME + System.getProperty("file.separator") + DEFAULT);

    public void setNewFile(final File file){
        if(file.getParentFile().exists()){
            this.file = file;
        } else {
            throw new IllegalArgumentException("Element in question does not exist");
        }
    }

    public File getFile() {
        return this.file;
    }

    public String getFilePath() {
        return file.getPath();
    }

    public void write(String content) throws IOException {
        try (PrintStream out = new PrintStream(file, StandardCharsets.UTF_8)) {
            out.println(content);
        }
    }
}
