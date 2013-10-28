/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Lipari
 */
public class MioProjectTree implements TreeModel{
    private File rootTree;
     private Vector listeners = new Vector();
     
    public MioProjectTree(String root){
        rootTree=new File(root);
    }
    @Override
    public Object getRoot() {
        return rootTree;
        }

    @Override
    public Object getChild(Object genitore, int index) {
        File dir=(File) genitore;
        String[]figli=dir.list();
        return new TreeFile(dir,figli[index]);
    }

    @Override
    public int getChildCount(Object o) {
        File file=(File)o;
        if(file.isDirectory()){
            String[]fileList=file.list();
            if(fileList!=null)
                return file.list().length;
        }
        return 0;
    }

    @Override
    public boolean isLeaf(Object o) {
        File file=(File)o;
        return file.isFile();
    }
    @Override
    public int getIndexOfChild(Object genitore, Object figlio) {
        File dir=(File)genitore;
        File file=(File)figlio;
        String[]figli=dir.list();
        for(int i=0;i<figli.length;i++)
            if(file.getName().equals(figli[i]))
                return i;
        return -1;
    }
    @Override
    public void valueForPathChanged(TreePath path, Object value) {
    File oldFile = (File) path.getLastPathComponent();
    String fileParentPath = oldFile.getParent();
    String newFileName = (String) value;
    File targetFile = new File(fileParentPath, newFileName);
    oldFile.renameTo(targetFile);
    File parent = new File(fileParentPath);
    int[] changedChildrenIndices = { getIndexOfChild(parent, targetFile) };
    Object[] changedChildren = { targetFile };
    fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices, changedChildren);
 
  }
 
  private void fireTreeNodesChanged(TreePath parentPath, int[] indices, Object[] children) {
    TreeModelEvent event = new TreeModelEvent(this, parentPath, indices, children);
    Iterator iterator = listeners.iterator();
    TreeModelListener listener = null;
    while (iterator.hasNext()) {
      listener = (TreeModelListener) iterator.next();
      listener.treeNodesChanged(event);
    }
  }
  @Override
  public void addTreeModelListener(TreeModelListener listener) {
    listeners.add(listener);
  }
  @Override
  public void removeTreeModelListener(TreeModelListener listener) {
    listeners.remove(listener);
  }

private class TreeFile extends File {
    public TreeFile(File parent, String child) {
      super(parent, child);
    }
 
    public String toString() {
      return getName();
    }
  }
    
}
