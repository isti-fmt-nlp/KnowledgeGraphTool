/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import graphView.GraphWindow;
import gui.FilterPane;
import java.awt.Color;
import java.io.File;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import org.gephi.filters.api.FilterController;
import org.gephi.filters.api.Query;
import org.gephi.filters.api.Range;
import org.gephi.filters.plugin.edge.EdgeWeightBuilder;
import org.gephi.filters.plugin.graph.DegreeRangeBuilder;
import org.gephi.filters.plugin.graph.InDegreeRangeBuilder;
import org.gephi.filters.plugin.operator.INTERSECTIONBuilder;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.GraphView;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeIterable;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewProperty;
import org.gephi.preview.api.ProcessingTarget;
import org.gephi.preview.api.RenderTarget;
import org.gephi.preview.types.DependantOriginalColor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import processing.core.PApplet;

    
/**
 *
 * @author Lipari
 */
public class GraphEditor implements Observer{
    private GraphWindow gw;
    private static GraphEditor ge=null;
    private PreviewController previewController;
    private ProcessingTarget target;
    private FilterPane fp=null;
    private GraphEditor(){
    }
   public static GraphEditor getInstance() {
        if(ge==null)
            ge=new GraphEditor();
         return ge;    
    }
    public void loadGraph(String path){
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		Workspace workspace=pc.getCurrentWorkspace();
                ImportController importController = Lookup.getDefault().lookup(ImportController.class);
                GraphModel graphModel;
                Container container;
            try {
                 File file = new File(path);
                 container = importController.importFile(file);
                 container.getLoader().setEdgeDefault(EdgeDefault.DIRECTED);   //Force DIRECTED
                 container.setAllowAutoNode(false);  //Don't create missing nodes
            } catch (Exception ex) {
                 ex.printStackTrace();
                  return;}
    	PreviewController previewController=Lookup.getDefault().lookup(PreviewController.class);
        PreviewModel previewModel=previewController.getModel();
        previewModel.getProperties().putValue(PreviewProperty.SHOW_NODE_LABELS,true);
        previewModel.getProperties().putValue(PreviewProperty.CATEGORY_EDGE_ARROWS,true);
        previewModel.getProperties().putValue(PreviewProperty.NODE_LABEL_COLOR, new DependantOriginalColor(Color.WHITE));
        previewModel.getProperties().putValue(PreviewProperty.EDGE_CURVED, Boolean.FALSE);
        previewModel.getProperties().putValue(PreviewProperty.EDGE_OPACITY, 50);
        previewModel.getProperties().putValue(PreviewProperty.EDGE_RADIUS, 5f);
        previewModel.getProperties().putValue(PreviewProperty.BACKGROUND_COLOR, Color.BLACK);
        previewController.refreshPreview();
      //Append imported data to GraphAPI
        importController.process(container, new DefaultProcessor(), workspace);
        graphModel=Lookup.getDefault().lookup(org.gephi.graph.api.GraphController.class).getModel(workspace);
        for(Node node: graphModel.getGraph().getNodes().toArray()){
            node.getNodeData().getTextData().setText(node.getNodeData().getLabel());
            node.getNodeData().getTextData().setVisible(true);
        }
    }
    public void ApplyFilters(boolean ckDegree,int minDegree,int maxDegree,boolean ckInDegree,int minInDeg,int maxInDeg,boolean ckEdge,float minEdge,float maxEdge){
        Query query1=null;
		Query query2=null;
		Query query3=null;
                GraphView view;
		//Get controllers and models
		GraphModel graphModel = Lookup.getDefault().lookup(org.gephi.graph.api.GraphController.class).getModel();
		FilterController filterController = Lookup.getDefault().lookup(FilterController.class);
		if(graphModel==null)
			return;
		if(ckDegree){
                    	DegreeRangeBuilder.DegreeRangeFilter degreeFilter = new DegreeRangeBuilder.DegreeRangeFilter();
                        degreeFilter.setRange(new Range(minDegree,maxDegree)); 
			query1 = filterController.createQuery(degreeFilter);
		}
		
		if(ckInDegree){
                    InDegreeRangeBuilder.InDegreeRangeFilter inDegreeFilter = new InDegreeRangeBuilder.InDegreeRangeFilter();
                    inDegreeFilter.setRange(new Range(minInDeg,maxInDeg));
                    query2 = filterController.createQuery(inDegreeFilter);
				
		}
		if(ckEdge){
			EdgeWeightBuilder.EdgeWeightFilter edgeFilter = new EdgeWeightBuilder.EdgeWeightFilter();
			edgeFilter.setRange(new Range(minEdge,maxEdge));
			query3 = filterController.createQuery(edgeFilter);		
		}
		
		/**Combine two filters with AND - Set query and query2 as sub-query of AND*/
		INTERSECTIONBuilder.IntersectionOperator intersectionOperator = new INTERSECTIONBuilder.IntersectionOperator();
		Query query4 = filterController.createQuery(intersectionOperator);
		if(ckDegree)
			filterController.setSubQuery(query4, query1);
		if(ckInDegree)
			filterController.setSubQuery(query4, query2);
		if(ckEdge)
			filterController.setSubQuery(query4, query3);
		//Set the filter result as the visible view
		view=graphModel.getVisibleView();
		if(!view.isMainView())
                    graphModel.destroyView(view);
                filterController.filterVisible(query4);
		filterController.filterVisible(query4);
		graphModel.setVisibleView(view);
                updateApplet();
                updateInfo(fp);
	}
    public void updateInfo(FilterPane fp){
        if(this.fp==null)
            this.fp=fp;
        GraphModel graphModel = Lookup.getDefault().lookup(org.gephi.graph.api.GraphController.class).getModel();
	//See if graph is well imported
        DirectedGraph graph = graphModel.getDirectedGraphVisible();
        fp.setInfo(graph.getEdgeCount(), graph.getNodeCount());
    }
     public void updateApplet(){
         PApplet applet;
         previewController=Lookup.getDefault().lookup(PreviewController.class);
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
                applet.mousePressed();
    }
    public PApplet getApplet(){
        PApplet applet;
        previewController=Lookup.getDefault().lookup(PreviewController.class);
        previewController.refreshPreview();
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
    public int getMaxDegree(){
		GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getModel();
		NodeIterable nodeIterable;
		Iterator <Node>ite;
		nodeIterable=graphModel.getDirectedGraph().getNodes();
		int max=0;
		int tmp=0;
		ite=nodeIterable.iterator();
		while(ite.hasNext()){
			tmp=graphModel.getDirectedGraph().getDegree(ite.next());
			if(max<tmp)
				max=tmp;
			}
		return max;
	
	}
	public int getMaxInDegree(){
		GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getModel();
		NodeIterable nodeIterable;
		nodeIterable=graphModel.getDirectedGraph().getNodes();
		Iterator <Node>ite;
		int max=0;
		int tmp=0;
		ite=nodeIterable.iterator();
		while(ite.hasNext()){
			tmp=graphModel.getDirectedGraph().getInDegree(ite.next());
			if(max<tmp)
				max=tmp;
			}
		return max;
	}


   
    public void setObserver(Observable o){
        o.addObserver(this);
    }

    @Override
    public void update(Observable o, Object o1) {
        updateApplet();
        updateInfo(fp);
    }
}
