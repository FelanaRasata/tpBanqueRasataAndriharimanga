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
import mg.itu.tpbanquerasataandriharimanga.util.Util;

/**
 *
 * @author rasat
 */
@Named(value = "ajoutCompte")
@ViewScoped
public class AjoutCompte implements Serializable {

    String nom;
    int solde;

    @Inject
    private GestionnaireCompte compteManager;

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String ajouter() {
        boolean erreur = false;
        CompteBancaire compte = new CompteBancaire(nom, solde);

        if (solde < 0) {
            Util.messageErreur("Solde inséré invalide", "Solde inséré invalide", "form:solde");
            erreur = true;
        }

        if (erreur) {
            return null;
        }

        compteManager.creerCompte(compte);

        String message = "Compte Bancaire correctement créé : # "
                + compte.getId();
        Util.addFlashInfoMessage(message);

        return "listeComptes?faces-redirect=true";
    }

}
