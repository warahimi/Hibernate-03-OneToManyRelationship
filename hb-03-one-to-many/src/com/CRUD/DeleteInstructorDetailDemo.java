package com.CRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.etity.Instructor;
import com.etity.InstructorDetail;

public class DeleteInstructorDetailDemo {

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
			
			
			//get the instructor detail object
			int id = 3;
			InstructorDetail temInstructorDetail = theSession.get(InstructorDetail.class, id);
			
			
			//print the isntructor detail
			System.out.println("Instructor Detail: "+temInstructorDetail);
			
			
			//print the associated instructor
			System.out.println("The Asscociated Instructor :"+temInstructorDetail.getInstructor());
			
			
			// now lets delete  the instructor details 
			
			//beofe deleting only the instructor detail, break the bi-drectional link
			temInstructorDetail.getInstructor().setInstructorDetail(null);// this breaks the linek by getting the instructor and setting its instructorDetail to null
			System.out.println("Deleting InstructorDetail: "+temInstructorDetail);
			theSession.delete(temInstructorDetail); // this will delete cascade the associated instructor as well ,break the 2 way link
			
			//commit the transaction
			theSession.getTransaction().commit(); 
			System.out.println("--------------------Done---------------------------");
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
