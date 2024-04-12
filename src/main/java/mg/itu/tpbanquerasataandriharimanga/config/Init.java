/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquerasataandriharimanga.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import java.io.Serializable;
import mg.itu.tpbanquerasataandriharimanga.entity.CompteBancaire;
import mg.itu.tpbanquerasataandriharimanga.service.GestionnaireCompte.GestionnaireCompte;

/**
 *
 * @author rasat
 */
@ApplicationScoped
public class Init  implements Serializable {

    @Inject
    private GestionnaireCompte compteManager;

    public void init(
            @Observes
            @Initialized(ApplicationScoped.class) 
            ServletContext context) {
        
        long count = compteManager.nbComptes();
        
        System.out.println("******************COUNT : " + count);
        
        if (count == 0) {
            compteManager.creerCompte(new CompteBancaire("John Lennon", 150000));
            compteManager.creerCompte(new CompteBancaire("Paul McCartney", 950000));
            compteManager.creerCompte(new CompteBancaire("Ringo Starr", 20000));
            compteManager.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
        }
    }
}
