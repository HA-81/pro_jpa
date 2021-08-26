package com.sdzee.servlets;

import java.io.IOException;

import com.sdzee.dao.UtilisateurDao;
import com.sdzee.entities.Utilisateur;

@WebServlet( urlPatterns = { "/inscription" } )
public class Inscription extends HttpServlet {
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String VUE      = "/WEB-INF/inscription.jsp";

    // Injection de notre EJB (Session Bean Stateless)
    @EJB
    private UtilisateurDao     utilisateurDao;

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        /* Pr�paration de l'objet formulaire */
        InscriptionForm form = new InscriptionForm( utilisateurDao );

        /* Traitement de la requ�te et r�cup�ration du bean en r�sultant */
        Utilisateur utilisateur = form.inscrireUtilisateur( request );

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}
