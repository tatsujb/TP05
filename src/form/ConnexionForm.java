package form;

import bean.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valodia Tsacanias <https://github.com/valoTs> on 22/05/17.
 */

public class ConnexionForm {

    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "password";
    private String resultat;
    private Map<String, String> erreurs = new HashMap<>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur connecterUtilisateur(HttpServletRequest request) {

        // Récupération des champs du formulaire
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String password = getValeurChamp(request, CHAMP_PASS);
        Utilisateur utilisateur = new Utilisateur();

        // Validation du champ email.
        try {
            validationEmail(email);
        } catch (Exception e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        utilisateur.setEmail(email);

        // Validation du champ mot de passe.
        try {
            validationPassword(password);

        } catch (Exception e) {
            setErreur(CHAMP_PASS, e.getMessage());
        }
        utilisateur.setPassword(password);

        // Initialisation du résultat global de la validation.

        if (erreurs.isEmpty()) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }
        return utilisateur;
    }


     //Valide l'adresse email saisie.

    private void validationEmail(String email) throws Exception {
        if (email == null) {
            throw new Exception("Merci de saisir une adresse mail.");
        } else if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            throw new Exception("Merci de saisir une adresse mail valide.");
        }
    }


     // Valide le mot de passe saisi.

    private void validationPassword(String password) throws Exception {
        if (password != null) {
            if (password.length() < 3) {
                throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir votre mot de passe.");
        }
    }


     //Ajoute un message correspondant au champ spécifié à la map des erreurs.

    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }


//Méthode utilitaire qui retourne null si un champ est vide, et son contenu sinon.

    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);

        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }
}
