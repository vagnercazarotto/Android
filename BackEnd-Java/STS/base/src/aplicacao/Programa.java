package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dominio.Pessoa;
import javax.persistence.Persistence;

public class Programa {
	
	public static void main(String[] args) { 
		

		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("base-JPA");
		EntityManager em = emf.createEntityManager();
		
		Pessoa p = em.find(Pessoa.class,2);
		
		System.out.println(p.toString());
		em.close();
		emf.close();
		
	}
	
}
