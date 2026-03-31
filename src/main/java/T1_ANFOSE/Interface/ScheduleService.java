package T1_ANFOSE.Interface;

public interface ScheduleService {
    boolean hasScheduleConflict(String StudentId, String courseID);
}
