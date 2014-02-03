package wwasik.mpm.model;

import java.util.Date;
import java.util.List;

/**
 * @author dom
 */
public class Project {

    private Date start;
    private Date stop;
    private List<Task> schedule;
    private OwnEmployee manager;
    private List<Employee> contactPerson;
    private Company owner;
    private Integer progress;
}
