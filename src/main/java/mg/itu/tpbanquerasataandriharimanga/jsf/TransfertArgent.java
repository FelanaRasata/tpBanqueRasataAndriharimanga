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

        boolean erreur = false;

        CompteBancaire source = compteManager.findById(idSource);
        if (source == null) {
            // Message d'erreur associé au composant source ; form:source est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "source"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else {
            if (source.getSolde() < montant) {
                // à compléter pour le cas où le solde du compte source est insuffisant...

                Util.messageErreur("Le montant du solde est insuffisant pour ce transfert", "Manque de fond", "form:montant");

                erreur = true;
            }
        }

        CompteBancaire destination = compteManager.findById(idDestination);

        if (destination == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
            erreur = true;
        }

        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }

        compteManager.transferer(source, destination, montant);

        // Message de succès ; addFlash à cause de la redirection.
        String message = "Transfert correctement effectué : "
                + " le montant " + montant
                + " de " + source.getNom() + " vers " + destination.getNom();
        Util.addFlashInfoMessage(message);

        return "listeComptes?faces-redirect=true";
    }

}
