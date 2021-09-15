package com.luv2code.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // Create session
        Session session;

        try {

            session = factory.getCurrentSession();
            // create the objects
            Instructor tempInstructor =
                    new Instructor("Vrinda", "Yadav", "jackhammervyom@gmail.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail("www.github.com/Vrinda-Yadav", "Studying");

            //associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            //start a transaction
            session.beginTransaction();

            //save the instructor

            // Note: This will ALSO save the details of the object associated
            // because of CascadeType.All
            //
            session.save(tempInstructor);

            // Commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (Exception a) {
            a.printStackTrace();
        }
    }


}
