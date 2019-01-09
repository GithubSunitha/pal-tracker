package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> timeEntries = new HashMap<>();

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        TimeEntry newTimeEntry = new TimeEntry(timeEntries.size() + 1, timeEntry.getProjectId(), timeEntry.getUserId(),
                timeEntry.getDate(), timeEntry.getHours());
        timeEntries.put(newTimeEntry.getId(), newTimeEntry);

        return newTimeEntry;
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {

       /* TimeEntry currEntry = timeEntries.get(timeEntryId);
        System.out.println(currEntry.toString());
        //doesn't scale..but will do....
        currEntry.setUserId(timeEntry.getUserId());
        currEntry.setDate(timeEntry.getDate());
        currEntry.setProjectId(timeEntry.getProjectId());
        currEntry.setHours(timeEntry.getHours());
        timeEntries.put(timeEntryId, currEntry);*/

        TimeEntry updatedEntry = new TimeEntry(
                timeEntryId,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntries.put(timeEntryId, updatedEntry);

        return updatedEntry;

    }

    @Override
    public TimeEntry find(long timeEntry) {
        return timeEntries.get(timeEntry);
    }

    @Override
    public  TimeEntry delete(long timeEntryId) {
        return timeEntries.remove(timeEntryId);
    }

    public List<TimeEntry> list() {
        return timeEntries.values().stream()
                .collect(Collectors.toList());
    }

    /*

    TimeEntry createdTimeEntry = repo.create(new TimeEntry(projectId, userId, LocalDate.parse("2017-01-08"), 8));

    TimeEntry updatedEntry = repo.update(
            created.getId(),
            new TimeEntry(321L, 654L, LocalDate.parse("2017-01-09"), 5));


            epo.delete(created.getId());

            assertThat(repo.list()).isEmpty();

    */
}
