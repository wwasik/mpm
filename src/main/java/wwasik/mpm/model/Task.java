package wwasik.mpm.model;

import java.util.Calendar;
import java.util.Date;

/**
 * @author dom
 */
public interface Task {

    public Date getStart();
    public void setStart(Date start);
    public Date getStop();
    public void setStop(Date stop);
    public Task getPrevious();
    public void setPrevious(Task previous);
    public String getDescription();
    public void setDescription(String description);
    public Integer getProgress();
    public void setProgress(Integer progress);
    public Long countDuration();
}
