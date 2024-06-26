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
@Named(value = "modification")
@ViewScoped
public class ModificationCompte implements Serializable {

    private Integer id;
    CompteBancaire compte;

    private String nom;

    @Inject
    private GestionnaireCompte compteManager;

    /**
     * Creates a new instance of ModificationCompte
     */
    public ModificationCompte() {
    }

    public void loadCompte() {
        compte = compteManager.findById(id);
        this.setNom(nom = compte.getNom());

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String modification() {

        compteManager.update(compte);

        String message = "Modification enregistré sur compte de " + compte.getId();

        if (!compte.getNom().equals(nom)) {
            message += " nom : " + nom + " de " + compte.getNom();
        }

        Util.addFlashInfoMessage(message);
        return "listeComptes?faces-redirect=true";
    }
}
