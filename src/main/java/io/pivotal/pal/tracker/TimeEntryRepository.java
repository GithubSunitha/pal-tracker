package io.pivotal.pal.tracker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public interface TimeEntryRepository {

    public TimeEntry create(TimeEntry timeEntry);

    public TimeEntry update(long timeEntryId, TimeEntry timeEntry);

    public TimeEntry find(long timeEntry);

    public TimeEntry delete(long timeEntryId);

    public List<TimeEntry> list();
}
