/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerasataandriharimanga.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.tpbanquerasataandriharimanga.entity.CompteBancaire;
import mg.itu.tpbanquerasataandriharimanga.service.GestionnaireCompte.GestionnaireCompte;

/**
 *
 * @author rasat
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

    private Integer id;
    private CompteBancaire compte;

    @Inject
    private GestionnaireCompte compteManager;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Creates a new instance of Operations
     */
    public Operations() {
    }

    public void loadCompte() {
        compte = compteManager.findById(id);
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }
    
    
}
