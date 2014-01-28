package wwasik.mpm.model;

import wwasik.mpm.model.person.Client;
import wwasik.mpm.model.person.Employee;
import java.util.Date;

/**
 * @author dom
 */
public class Project {
    private Date start;
    private Date stop;
    private Schedule schedule;
    private Employee menager;
    private Client contactPerson;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStop() {
        return stop;
    }

    public void setStop(Date stop) {
        this.stop = stop;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Employee getMenager() {
        return menager;
    }

    public void setMenager(Employee menager) {
        this.menager = menager;
    }

    public Client getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(Client contactPerson) {
        this.contactPerson = contactPerson;
    }
    
    

}
