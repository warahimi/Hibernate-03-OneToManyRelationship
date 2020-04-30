package com.CRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.etity.Course;
import com.etity.Instructor;
import com.etity.InstructorDetail;

public class CreateCoursesDemo {

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
			
			
			//create some courses
			Course tempCourse1 = new Course("Java - Ultimate Guide");
			Course tempCourse2 = new Course("Database - Ultimate Guide");

			
			//add courses to instructors
			tempInstructor.Add(tempCourse1); // we actually us the convinience method , setting up our bi directional relationship
			tempInstructor.Add(tempCourse2);
			
			
			//save the courses
			theSession.save(tempCourse1);
			theSession.save(tempCourse2);
			
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
