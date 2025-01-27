package dao;

import entity.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class TaskDao {
    public void saveTask(Task task) {
        // try to save data into DB
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();
            session.save(task);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Task> getTasks() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("from Task", Task.class).list();
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Task getTaskByName(String fp_Name) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Task> query = session.createQuery("from Task where TaskName = :P1 ", Task.class);
            query.setParameter("P1", fp_Name);
            return query.list().get(0);
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getTaskNameByID (int TaskID) {
        try {
            String TaskName;
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Task> query = session.createQuery("from Task where TaskID = :P1 ", Task.class);
            query.setParameter("P1", TaskID);
            return query.list().get(0).getTaskName();
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
