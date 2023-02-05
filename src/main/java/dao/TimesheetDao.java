package dao;

import entity.Employee;
import entity.Timesheet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class TimesheetDao {
    public void saveTimesheet(Timesheet timesheet) {
        // try to save data into DB
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();
            session.save(timesheet);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    public Timesheet removeTimesheet(Integer id) {
        return null;
    }

    public List<Timesheet> getTimesheet(Employee employee) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Timesheet> query = session.createQuery("from Timesheet where TimesheetEmployeeID = :P1 ", Timesheet.class);
            query.setParameter("P1", employee.getEmployeeID());
            return query.list();
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
