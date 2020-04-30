package com.CRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.etity.Course;
import com.etity.Instructor;
import com.etity.InstructorDetail;

public class GetInstructorCoursesDemo {

	public static void main(String[] args) {
		//Create session factory 
		SessionFactory factory = new Configuration()
										.configure()
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.buildSessionFactory();
		
		//Create Session
		Session theSession = factory.getCurrentSession();
		
		try 
		{

			//Start a transaction
			theSession.beginTransaction();
			
			//get the instructor from db
			int id = 1;
			Instructor tempInstructor = theSession.get(Instructor.class, id);
			System.out.println("Retrived Instructor: " + tempInstructor);
			
			
			//get the courses for the instructor 
			System.out.println("Courses related to the instructor: "+ tempInstructor.getCourses());
			
			
			
			//commit the transaction
			theSession.getTransaction().commit(); 
			System.out.println("--------------------Done---------------------------");
			
		} 
		catch (Exception e)
		{
			//e.printStackTrace();
		}
		finally
		{
			//Cleaning up
			theSession.close();
			factory.close();
			
		}

	}

}
