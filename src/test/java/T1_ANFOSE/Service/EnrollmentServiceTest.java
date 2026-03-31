package T1_ANFOSE.Service;

import T1_ANFOSE.Interface.PrerequisiteService;
import T1_ANFOSE.Interface.ScheduleService;
import T1_ANFOSE.Interface.VacancyService;
import T1_ANFOSE.Model.Course;
import T1_ANFOSE.Model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentServiceTest {

    @Test
    @DisplayName("should Not Enroll Student When Prerequisites are not met")
    void shouldNotEnrollStudentWhenPrerequisitesAreNotMet(){
        PrerequisiteService prerequisiteService = mock(PrerequisiteService.class);
        ScheduleService scheduleService = mock(ScheduleService.class);
        VacancyService vacancyService = mock(VacancyService.class);

        when(prerequisiteService.hasApprovedPrerequisites("S1","C1")).thenReturn(false);

        EnrollmentService service =  new EnrollmentService(prerequisiteService, scheduleService, vacancyService);

        Student student = new Student("S1",false);
        Course course = new Course("C1",5);

        String addResult = service.enroll(student,course);

        assertEquals("No cumple prerequisitos",addResult);
    }


    @Test
    @DisplayName("should Not Enroll Student When There is a Schedule Conflict")
    void shouldNotEnrollStudentWhenScheduleConflictExists(){
        PrerequisiteService prerequisiteService = mock(PrerequisiteService.class);
        ScheduleService scheduleService = mock(ScheduleService.class);
        VacancyService vacancyService = mock(VacancyService.class);

        when(prerequisiteService.hasApprovedPrerequisites("S1","C1")).thenReturn(true);
        when(scheduleService.hasScheduleConflict("S1","C1")).thenReturn(true);

        EnrollmentService service =  new EnrollmentService(prerequisiteService, scheduleService, vacancyService);

        Student student = new Student("S1",false);
        Course course = new Course("C1",5);

        String addResult = service.enroll(student,course);

        assertEquals("Horario cruzado",addResult);
    }



    @Test
    @DisplayName("Should enroll successfully when all conditions are met")
    void shouldEnrollSuccessfullyWhenAllConditionsAreMet(){
        PrerequisiteService prerequisiteService = mock(PrerequisiteService.class);
        ScheduleService scheduleService = mock(ScheduleService.class);
        VacancyService vacancyService = mock(VacancyService.class);

        when(prerequisiteService.hasApprovedPrerequisites("S1","C1")).thenReturn(true);
        when(scheduleService.hasScheduleConflict("S1","C1")).thenReturn(false);
        when(vacancyService.hasVacancy("C1")).thenReturn(true);

        EnrollmentService service =  new EnrollmentService(prerequisiteService, scheduleService, vacancyService);

        Student student = new Student("S1",false);
        Course course = new Course("C1",10);

        String addResult = service.enroll(student,course);

        assertEquals("Matricula Exitosa",addResult);
    }


}