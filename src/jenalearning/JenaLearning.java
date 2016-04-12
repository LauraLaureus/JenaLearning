/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jenalearning;

import org.apache.jena.atlas.logging.LogCtl;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
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
        
    }
    
}
