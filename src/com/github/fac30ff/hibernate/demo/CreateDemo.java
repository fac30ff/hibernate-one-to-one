package com.github.fac30ff.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.github.fac30ff.hibernate.demo.entity.Instructor;
import com.github.fac30ff.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			Instructor tempInstructor = new Instructor("I", "Am", "am@mail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.website.com", "coding");
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			//begin  a transaction
			session.beginTransaction();
			//save the instructor
			//Note: this will also save the details object because of CascadeType.ALL
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
