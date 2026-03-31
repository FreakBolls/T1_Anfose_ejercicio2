package T1_ANFOSE.Service;

import T1_ANFOSE.Interface.PrerequisiteService;
import T1_ANFOSE.Interface.ScheduleService;
import T1_ANFOSE.Interface.VacancyService;
import T1_ANFOSE.Model.Course;
import T1_ANFOSE.Model.Student;

public class EnrollmentService {

    private PrerequisiteService prerequisiteService;
    private ScheduleService scheduleService;
    private VacancyService vacancyService;

    public EnrollmentService(PrerequisiteService prerequisiteService, ScheduleService scheduleService, VacancyService vacancyService) {
        this.prerequisiteService = prerequisiteService;
        this.scheduleService = scheduleService;
        this.vacancyService = vacancyService;
    }

    public String enroll(Student student, Course course){

        if(!prerequisiteService.hasApprovedPrerequisites(student.getId(),course.getId())){
            return "No cumple prerequisitos";
        }

        if(scheduleService.hasScheduleConflict(student.getId(),course.getId())){
            return "Horario cruzado";
        }

        if(!vacancyService.hasVacancy(course.getId())){
            return "No hay vacantes disponibles";
        }


        if(student.isNew() && course.getCredits() >12){
            return "Alumno nuevo no puede exceder 12 créditos";
        }

        if(course.getCredits() >20){
            return "Excede máximo de créditos";
        }

        return  "Matricula Exitosa";
    }
}
