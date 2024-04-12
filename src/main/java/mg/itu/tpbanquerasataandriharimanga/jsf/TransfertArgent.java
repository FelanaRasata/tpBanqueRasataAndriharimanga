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
@Named(value = "transfert")
@ViewScoped
public class TransfertArgent implements Serializable {

    int idSource;
    int idDestination;
    int montant;

    @Inject
    private GestionnaireCompte compteManager;

    /**
     * Creates a new instance of TransfertArgent
     */
    public TransfertArgent() {
    }

    public int getIdSource() {
        return idSource;
    }

    public int getIdDestination() {
        return idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setIdSource(int idSource) {
        this.idSource = idSource;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String transferer() {

        CompteBancaire source = compteManager.findById(idSource);
        CompteBancaire destination = compteManager.findById(idDestination);
        System.out.println("****************\"listeComptes?faces-redirect=true\"");
        compteManager.transferer(source, destination, montant);

        System.out.println("****************\"listeComptes?faces-redirect=true\"");

        return "listeComptes?faces-redirect=true";
    }

}
