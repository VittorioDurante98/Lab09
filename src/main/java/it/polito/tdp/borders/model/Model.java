package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private SimpleGraph<Country, DefaultEdge> grafo;
	private Map<Integer, Country> nazioni;
	private List<Border> confini;
	private List<Country> listanazioni;
	private BordersDAO dao;
	private Map<Country, Country> visita= new HashMap<>();
	
	public Model() {
		this.dao= new BordersDAO();
		this.nazioni= new HashMap<>(dao.loadAllCountries());
	}

	public void creaGrafo(int year) {
		this.grafo= new SimpleGraph<>(DefaultEdge.class);
		this.listanazioni= new ArrayList<>(dao.getVertici(year, nazioni));
		this.confini= new ArrayList<>(dao.getCountryPairs(year, nazioni));
		Graphs.addAllVertices(grafo, listanazioni);
		
		for (Border i: confini) {
			grafo.addEdge(i.getStatoA(), i.getStatoB());
		}
	}
	
	
	public List<Country> getListanazioni() {
		return listanazioni;
	}

	public int numeroArchi(int year, Country c) {
		return this.grafo.outDegreeOf(c);
	}
	public int numeroVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public List<Country> vicini(Country c) {
		List<Country> listanazioni= new ArrayList<>();
		BreadthFirstIterator<Country, DefaultEdge> fi = new BreadthFirstIterator<>(grafo,c);
		
		visita.put(c, null);
		fi.addTraversalListener(new TraversalListener<Country, DefaultEdge>(){

			@Override
			public void connectedComponentFinished(ConnectedComponentTraversalEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void connectedComponentStarted(ConnectedComponentTraversalEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void edgeTraversed(EdgeTraversalEvent<DefaultEdge> e) {
				Country sorg = grafo.getEdgeSource(e.getEdge());
				Country dest = grafo.getEdgeTarget(e.getEdge());
				if(!visita.containsKey(dest) && visita.containsKey(sorg)) {
					visita.put(dest, sorg);
				}else if(visita.containsKey(dest) && !visita.containsKey(sorg)) {
					visita.put(sorg, dest);
				}
				
			}

			@Override
			public void vertexTraversed(VertexTraversalEvent<Country> e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void vertexFinished(VertexTraversalEvent<Country> e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		if(fi.hasNext()) {
			fi.next();
		}
		//	fi.next();
		
		for (Country step: visita.keySet()) {
			listanazioni.add(step);
		}
		listanazioni.remove(c);
		
		return listanazioni;
	}
	
}
