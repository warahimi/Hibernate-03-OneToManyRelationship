package com.CRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.etity.Course;
import com.etity.Instructor;
import com.etity.InstructorDetail;

public class DeleteCoursesDemo {

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
			
			// note that when we delete a course we cannot delete the associated Instructor
			
			//get a course from db
			int courseID = 10 ; //check the course id from da from course table
			Course tempCourse = theSession.get(Course.class, courseID);
			
			
			//delete the course
			System.out.println("Deleting the course");
			theSession.delete(tempCourse); // this will delete only the course because of the cascading we provided
			
			
			
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
