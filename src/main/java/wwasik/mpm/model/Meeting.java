package wwasik.mpm.model;

import java.util.Date;
import java.util.List;

/**
 * @author Wojtek
 */
public class Meeting implements Task {

    private Date start;
    private Date stop;
    private Task previous;
    private String description;
    private Integer progress;
    private List<Employee> contributors;
    private String note;
    private String presentation;

    @Override
    public Date getStart() {
        return start;
    }

    @Override
    public void setStart(Date start) {
        this.start = start;
    }

    @Override
    public Date getStop() {
        return stop;
    }

    @Override
    public void setStop(Date stop) {
        this.stop = stop;
    }

    @Override
    public Task getPrevious() {
        return previous;
    }

    @Override
    public void setPrevious(Task previous) {
        this.previous = previous;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getProgress() {
        return progress;
    }

    @Override
    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    @Override
    public Long countDuration() {
        return stop.getTime() - start.getTime();
    }
}
