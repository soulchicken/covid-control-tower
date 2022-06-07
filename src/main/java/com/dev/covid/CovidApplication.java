package com.dev.covid;

import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dev.covid.model.SelfQuarantine;

@SpringBootApplication
public class CovidApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidApplication.class, args);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("covid");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			tx.begin();
			SelfQuarantine selfQuarantine = new SelfQuarantine();
			selfQuarantine.setSelfQuarantinename("김치킨");
			selfQuarantine.setSelfQuarantinephoneNumber("01012341234");
			selfQuarantine.setSelfQuarantineDate(formatter.parse("2022-06-01"));
			selfQuarantine.setSelfQuarantineRelease(formatter.parse("2202-06-08"));
			em.persist(selfQuarantine);
			System.out.println(selfQuarantine);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}
