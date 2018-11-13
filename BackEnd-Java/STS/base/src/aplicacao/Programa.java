package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dominio.Pessoa;
import javax.persistence.Persistence;

public class Programa {
	
	public static void main(String[] args) { 
		
		Pessoa p1 = new Pessoa(null,"carlos", "carlos@gmail.com");
		Pessoa p2 = new Pessoa(null,"carlos 2 ", "carlos2@gmail.com");
		Pessoa p3 = new Pessoa(null,"carlos 3", "carlos3@gmail.com");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("base-JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.getTransaction().commit();
		System.out.println("OK, done");
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		
	}
	
}
