/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiComponents;

import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.ProcessingTarget;
import org.gephi.preview.api.RenderTarget;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import processing.core.PApplet;

/**
 *
 * @author Peppe
 */
public class AppletView1 {
        private ProcessingTarget target;
	private PreviewController previewController;
	private PApplet applet;
        public PApplet getPApplet() {
            
            previewController=Lookup.getDefault().lookup(PreviewController.class);
            previewController.refreshPreview(null);
            //New Processing target, get the PApplet
            target = (ProcessingTarget) previewController.getRenderTarget(RenderTarget.PROCESSING_TARGET);
            applet = target.getApplet();
            applet.init();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
            }
            //Refresh the preview and reset the zoom
            previewController.render(target);
            target.refresh();
            target.resetZoom();
            target.zoomMinus();
            return applet;
	}
}
