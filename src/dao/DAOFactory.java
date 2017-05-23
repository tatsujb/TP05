package dao;

import bean.Utilisateur;

/**
 * Created by  Stefan Matei <https://github.com/hormatei> on 23/05/17.
 */
public class DAOFactory {

    public static DAO<Utilisateur> getUtilisateurDAO() {
        return new UtilisateurDAO();
    }

}

