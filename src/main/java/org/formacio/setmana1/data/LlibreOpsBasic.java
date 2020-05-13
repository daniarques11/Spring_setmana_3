package org.formacio.setmana1.data;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.formacio.setmana1.domini.Llibre;
import org.formacio.setmana1.domini.Recomanacio;
import org.springframework.stereotype.Component;

/**
 * Modifica aquesta classe per tal que sigui un component Spring que realitza les 
 * operacions de persistencia tal com indiquen les firmes dels metodes
 */

@Component
public class LlibreOpsBasic {
	
	@PersistenceContext
	private EntityManager em;
	/**
	 * Retorna el llibre amb l'ISBN indicat o, si no existeix, llança un LlibreNoExisteixException
	 */
	@Transactional
	public Llibre carrega (String isbn) throws LlibreNoExisteixException {
		return em.find(Llibre.class, isbn);
	}
	
	/**
	 * Sense sorpreses: dona d'alta un nou llibre amb les propietats especificaques
	 */
	@Transactional
	public void alta (String isbn, String autor, Integer pagines, Recomanacio recomanacio, String titol) {
		Llibre llibre = new Llibre();
		llibre.setIsbn(isbn);
		llibre.setAutor(autor);
		llibre.setPagines(pagines);
		llibre.setRecomanacio(recomanacio);
		llibre.setTitol(titol);
		em.persist(llibre);
		
	}
	
	/**
	 * Elimina, si existeix, un llibre de la base de dades
	 * @param isbn del llibre a eliminar
	 * @return true si s'ha esborrat el llibre, false si no existia
	 */
	 @Transactional
	 // TO-DO afegir possibilitat que no es trobi l'objecte a la BDD
	public boolean elimina (String isbn) {
		Llibre eliminar = em.find(Llibre.class, isbn);
		em.remove(eliminar);
		return true;
	}
	
	/**
	 * Guarda a bbdd l'estat del llibre indicat
	 */
	 @Transactional
	public void modifica (Llibre llibre) {
		 llibre = em.merge(llibre);
	}
	
	/**
	 * Retorna true o false en funcio de si existeix un llibre amb aquest ISBN
	 * (Aquest metode no llanca excepcions!)
	 */
	public boolean existeix (String isbn) {
		return false;
	}

	/**
	 * Retorna quina es la recomanacio per el llibre indicat
	 * Si el llibre indicat no existeix, retorna null
	 */
	public Recomanacio recomenacioPer (String isbn) {
		return null;
	}
	
}
