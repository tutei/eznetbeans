/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.ez.eztpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;

import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "File",
id = "no.ez.eztpl.eZClearCache")
@ActionRegistration(iconBase = "no/ez/eztpl/ezcache.png",
displayName = "#CTL_eZClearCache")
@ActionReferences({
    @ActionReference(path = "Toolbars/Debuggin", position = 0)
})
@Messages("CTL_eZClearCache=eZClearCache")
public final class eZClearCache implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body

        Project op = OpenProjects.getDefault().getMainProject();
        if (op != null) {

            File var = new File(op.getProjectDirectory().getPath() + File.separator + "var");


            if (var.exists()) {
                File cache = new File(var.getPath() + File.separator + "cache");
                if (cache.exists()) {
                    deleteChildren(cache);
                }
                File[] children = var.listFiles();
                for (int x = 0; x < children.length; x++) {
                    File f = new File(children[x].getPath() + File.separator + "cache");
                    if (f.exists()) {
                        deleteChildren(f);
                    }

                }
            }
        }


    }

    public void deleteChildren(File folder) {
        File[] children = folder.listFiles();
        for (int x = 0; x < children.length; x++) {
            try {                
                if(children[x].isFile())children[x].delete();
                else deleteDir(children[x]);
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }

    }
    
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
    
        // The directory is now empty so delete it
        return dir.delete();
    } 
}
