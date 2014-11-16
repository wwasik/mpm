package wwasik.mpm.model;

import java.util.Date;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * @author dom
 */
public class Project {

    @Id
    private String id;
    @NotEmpty
    private Date start;
    @NotEmpty
    private Date stop;
    @DBRef
    private List<Task> schedule;
    @DBRef
    private OwnEmployee manager;
    @DBRef
    private List<Employee> contactPerson;
    @DBRef
    private Company owner;
    @NotEmpty
    private Integer progress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<Task> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Task> schedule) {
        this.schedule = schedule;
    }

    public OwnEmployee getManager() {
        return manager;
    }

    public void setManager(OwnEmployee manager) {
        this.manager = manager;
    }

    public List<Employee> getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(List<Employee> contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Company getOwner() {
        return owner;
    }

    public void setOwner(Company owner) {
        this.owner = owner;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

}
