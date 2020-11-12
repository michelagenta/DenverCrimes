package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	private EventsDao dao;
	private SimpleWeightedGraph<String, DefaultWeightedEdge> grafo; 
	
	
	public Model() {
		dao= new EventsDao(); 
	}

	public List<Integer> getMesi(){
		return dao.getMesi(); 
	}
	
	public List<String> getCategorie(){
		return dao.getCategorie(); 
	}
	
	public void creaGrafo(String categoria, Integer mese) {
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class); 
		List<Adiacenza> adiacenze= this.dao.getAdiacenze(categoria, mese); 
		
		for(Adiacenza a: adiacenze) {
			if(!this.grafo.containsVertex(a.getV1())) 
				this.grafo.addVertex(a.getV1()); 
			if(! this.grafo.containsVertex(a.getV2())) 
				this.grafo.addVertex(a.getV2()); 
			
			if(this.grafo.getEdge(a.getV1(), a.getV2())== null) {
				Graphs.addEdgeWithVertices(this.grafo, a.getV1(), a.getV2(), a .getPeso()); 
			}
			System.out.println(String.format("grafo con %d vertici e %d archi ", this.grafo.vertexSet().size(), this.grafo.edgeSet().size()));
		}
		
	/*	public List<Arco> getArchi(){
			double pesoMedio=0.0; 
			for(DefaultWeightedEdge e: this.grafo.edgeSet()) {
				pesoMedio+= this.grafo.getEdgeWeight(e); 
			}
			pesoMedio= pesoMedio/this.grafo.edgeSet().size(); 
			
			List<Arco> archi =new ArrayList<Arco>(); 
			for(DefaultWeightedEdge e: this.grafo.edgeSet()) {
				if(this.grafo.getEdgeWeight(e)> pesoMedio)
					archi.add(new Arco(grafo.getEdgeSource(e), grafo.getEdgeTarget(e), grafo.getEdgeWeight(e)));
				
			}
		}
		
	} */
		
	}
}


