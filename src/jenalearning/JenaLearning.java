/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jenalearning;

import org.apache.jena.atlas.logging.LogCtl;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.vocabulary.DC_10;
import org.apache.jena.vocabulary.VCARD;


/**
 *
 * @author usuario
 */
public class JenaLearning {

    static String bookURI = "http://si.com/hojarasca";
    static String AuthorURI = "http://si.com/Marquez";
    static String bookTitle = "La Hojarasca";
    static String AuthorName = "Gabriel";
    static String AuthorSurname = "Garcia Marquez";
    static String AuthorFullname = AuthorName + " " + AuthorSurname;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LogCtl.setCmdLogging(); //avoid loging warnings
        
        Model model = ModelFactory.createDefaultModel();
        
        Resource bookRSC = model.createResource(bookURI);
        bookRSC.addProperty(DC_10.title, bookTitle);
        
        Resource authorRSC = model.createResource(AuthorURI);
        authorRSC.addProperty(VCARD.NAME, AuthorFullname);
        authorRSC.addProperty(VCARD.Family, AuthorSurname);
        authorRSC.addProperty(VCARD.Given, AuthorName);
        
        
        bookRSC.addProperty(DC_10.creator, authorRSC);
        
        model.write(System.out); //printed in console formated as XML/RDF
        System.out.println("++++++++++++++++");
        RDFDataMgr.write(System.out, model, RDFFormat.TURTLE_PRETTY); // printed in console formated as Turtle
        
        //model.read("turtle.ttl"); //read a model from Turtle file
        
        
        StmtIterator it = model.listStatements();
        
        while (it.hasNext()) {
            Statement next = it.nextStatement();
            
            Resource subject = next.getSubject();
            Property prp = next.getPredicate();
            RDFNode object = next.getObject(); // object could be resource or literal so using of RDFNode is needed.
            System.out.println(subject.toString() + " " + prp.toString());
            if(object.isResource())
                System.out.println(object.toString());
            else
                System.out.println(object);
        }
        
        
        //Retrieve a Resource by its URI
        Resource rtvResource = model.getResource(AuthorURI);
        
        //Join two graphs
        Model model2 = ModelFactory.createDefaultModel();
        Resource author_model2 = model2.createResource(AuthorURI);
        author_model2.addProperty(VCARD.BDAY, "22/02/1945");
        
        Model joint = model.union(model2);
        System.out.println("----------------------------------------------------------");
        joint.write(System.out);
        
        
    }
    
}
