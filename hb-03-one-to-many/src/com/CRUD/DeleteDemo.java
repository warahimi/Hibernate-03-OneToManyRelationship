package com.CRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.etity.Instructor;
import com.etity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		//Create session factory 
		SessionFactory factory = new Configuration()
										.configure()
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.buildSessionFactory();
		
		//Create Session
		Session theSession = factory.getCurrentSession();
		
		try 
		{
			
			
			//Start a transaction
			theSession.beginTransaction();
			
			
			
			//1 - get the instructor by their id/PK
			int instructID = 1;
			Instructor tempInstructor = theSession.get(Instructor.class, instructID); // will return null if not found
			System.out.println("Found Instructor: "+tempInstructor);
			
			
			
			//2 - Delete the instructor 
			if(tempInstructor != null)
			{
				System.out.println("Deleting...."+tempInstructor);
				
				//this will also delete the assocaited instructor detail . beacuse of cascadeType.ALL 
				theSession.delete(tempInstructor);
			}
			
			
			//commit the transaction
			theSession.getTransaction().commit(); 
			System.out.println("--------------------Done---------------------------");
			
		} 
		catch (Exception e)
		{
			//e.printStackTrace();
		}

	}

}
