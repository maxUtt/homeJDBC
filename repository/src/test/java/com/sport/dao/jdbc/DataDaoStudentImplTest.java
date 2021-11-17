package com.sport.dao.jdbc;

import com.sport.dao.jdbc.jdbc.DataDaoStudent;
import com.sport.model.Student;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DataDaoStudentImplTest {

    private DataDaoStudent daoStudent;

    @Test
    public void whenAddStudentToDB() {
        boolean result = daoStudent.add(new Student(1,"Vova","123","Vladimir",23, SecurityConfig.ROLE_STUDENT,99));
        assertTrue(result);
    }

    @Test
    public void  whenDeleteStudentFromDB(){
        daoStudent.add(new Student(1,"Kim","123","Vladimir",23, SecurityConfig.ROLE_STUDENT,99));
        boolean result = daoStudent.delete("Kim");
        assertTrue(result);
    }
}
