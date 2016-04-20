package Ontology;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.tdb.TDBFactory;

import java.util.*;

public class Ontology {
	
	
	 public static final String directory = "TripleStore/Tourism" ;
	 public static final String source = "http://tourister.space/ontologies/tourism#";
	 public static final File file = new File("GeneratedFiles/tourism.owl");
	 
	 public static Dataset dataset;
	 public static Model model;
	 public static OntModel omodel;
	 
	 
	 
	
    
	
	 
	
	  public void updateOntologyWithHotel(Map m1)
	  {
		  
		  
		  
		  dataset = TDBFactory.createDataset(directory);
		 
		  
		  
		  model = dataset.getDefaultModel();
		  
		  
		  
		  dataset.begin(ReadWrite.WRITE);
		  omodel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,model);
		  
		  OntClass oc = omodel.getOntClass(source + "Apartment");
		  Individual ind = oc.createIndividual(source +  m1.get("name").toString().replaceAll("\\s", "") + "Ind");
		  
		  OntProperty op = omodel.getOntProperty(source + "hasName");
		  ind.addProperty(op,  m1.get("name").toString());
		  
		  
		 
		  
		  omodel.commit();
		  
		  dataset.end();
		  
		  
		  
		  FileOutputStream os=null;
	        try {
	            os = new FileOutputStream(file);
	            
	            model.write(os, "TTL",null);
	            os.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		  
		  
		  
	  }
	  
	  public void writeToFile(Model m)
	  {
		  
	  }

	

}
