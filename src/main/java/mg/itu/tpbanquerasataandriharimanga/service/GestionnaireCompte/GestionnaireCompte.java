/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquerasataandriharimanga.service.GestionnaireCompte;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.itu.tpbanquerasataandriharimanga.entity.CompteBancaire;

/**
 * Façade pour gérer les Customers.
 *
 * @author rasat
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "rasata", // nom et
        password = "root", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)
@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    @Transactional
    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public List<CompteBancaire> getAllComptes() {
        TypedQuery<CompteBancaire> query = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        return query.getResultList();
    }

    public long nbComptes() {
        TypedQuery<Long> query = em.createQuery("SELECT count(c) FROM CompteBancaire c", Long.class);
        return query.getSingleResult();
    }

    public CompteBancaire findById(int id) {
        return em.find(CompteBancaire.class, id);
    }

    @Transactional
    public CompteBancaire update(CompteBancaire compte) {
        return em.merge(compte);
    }
    
    @Transactional
    public void transferer(CompteBancaire source, CompteBancaire destination,
            int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }

}
