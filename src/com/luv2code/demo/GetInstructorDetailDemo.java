package com.luv2code.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // Create session
        Session session = factory.getCurrentSession();

        try {
            session = factory.getCurrentSession();

            //start a transaction
            session.beginTransaction();

            // get instructor by primary key
            int theId = 1;
            InstructorDetail tempInstructorDetail =
                    session.get(InstructorDetail.class, theId);

            System.out.println("Instructor Details of: " + tempInstructorDetail);

            System.out.println("The associated instructor: " + tempInstructorDetail.getInstructor());

            // Commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } catch (Exception a) {
            a.printStackTrace();
        } finally {
            // handle that leak issue.
            session.close();

            factory.close();
        }
    }


}
