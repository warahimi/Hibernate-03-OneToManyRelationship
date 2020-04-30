package com.CRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.etity.Instructor;
import com.etity.InstructorDetail;

public class CreateDemo {

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
			//Create the objects
			Instructor tempInstructor = new Instructor("Hosna", "Jani", "HosnaJane45@gamil.com");
			InstructorDetail temInstructorDetail = new InstructorDetail("www.youtube.com/hosna", "crying ");
			//at this point object are not linked to each other
			
			//Associate the objects. likne/hock them up 
			tempInstructor.setInstructorDetail(temInstructorDetail); // we could do it in Insturctos constractor 
			// now they are assocaited in memory we save and commit to reflect it to database
			
			//Start a transaction
			theSession.beginTransaction();
			
			
			
			//save the instructor
			System.out.println("Saving the insturcro object.... : "+ tempInstructor);
			theSession.save(tempInstructor); // this will also save the instructorDetail object because of  cascadeType.ALL 
			//cacadetype.ALL saves/deletes... the actual object and any associated objects to it
			// it will save the objects in 2 seperate tables 
			
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
