/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiComponents;

import java.io.File;

/**
 *
 * @author Peppe
 */
public class FileNode extends File {

    public FileNode(String directory) {
        super(directory);
    }

    public FileNode(FileNode parent, String child) {
        super(parent, child);
    }

    @Override
    public String toString() {
        return getName();
    }
}