/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jenalearning;

import org.apache.jena.atlas.logging.LogCtl;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;


/**
 *
 * @author usuario
 */
public class JenaLearning {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LogCtl.setCmdLogging(); //avoid loging warnings
        
        Model modelo = ModelFactory.createDefaultModel();
        
    }
    
}
