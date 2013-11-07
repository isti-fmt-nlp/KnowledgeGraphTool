/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supportGui;

/**
 *
 * @author Peppe
 */
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileSelectorModel implements TreeModel {

    private FileNode root;
    private HiddenFileFilter fileFilter=new HiddenFileFilter();

    /**
     * the constructor defines the root.
     */
    public FileSelectorModel(String directory) {
        root = new FileNode(directory);
    }

    public Object getRoot() {
        return root;
    }

    /**
     * returns the <code>parent</code>'s child located at index <code>index</code>.
     */
    public Object getChild(Object parent, int index) {
        FileNode parentNode = (FileNode) parent;
        return new FileNode(parentNode,parentNode.listFiles(fileFilter)[index].getName());
    }

    /**
     * returns the number of child.  If the node is not a directory, or its list of children
     * is null, returns 0.  Otherwise, just return the number of files under the current file.
     */
    public int getChildCount(Object parent) {
        FileNode parentNode = (FileNode) parent;
        if (parent == null 
                || !parentNode.isDirectory()
                || parentNode.listFiles(fileFilter) == null) {
            return 0;
        }

        return parentNode.listFiles(fileFilter).length;
    }

    /**
     * returns true if {{@link #getChildCount(Object)} is 0.
     */
    public boolean isLeaf(Object node) {
        File x=(File)node;
        return x.isFile();
    }

    /**
     * return the index of the child in the list of files under <code>parent</code>.
     */
    public int getIndexOfChild(Object parent, Object child) {
        FileNode parentNode = (FileNode) parent;
        FileNode childNode = (FileNode) child;
        return Arrays.asList(parentNode.list()).indexOf(childNode.getName());
    }

    // The following methods are not implemented, as we won't need them for this example.

    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    public void addTreeModelListener(TreeModelListener l) {
    }

    public void removeTreeModelListener(TreeModelListener l) {
    }
    private class HiddenFileFilter implements FileFilter {
        public boolean accept(File pathname) {
                return !pathname.isHidden();
        }
    }
}
