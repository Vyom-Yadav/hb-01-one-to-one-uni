package com.luv2code.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

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
            int theId = 3;
            InstructorDetail tempInstructorDetail =
                    session.get(InstructorDetail.class, theId);

            System.out.println("Instructor Details of: " + tempInstructorDetail);

            System.out.println("The associated instructor: "
                    + tempInstructorDetail.getInstructor());

            // now let's delete the instructor detail.
            System.out.println("Deleting tempInstructorDetail: "
                    + tempInstructorDetail);

            // remove the associated object reference
            // break bi-directional link
            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            session.delete(tempInstructorDetail);

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
