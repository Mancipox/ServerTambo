package com.tambo.model.VO;

import com.tambo.model.VO.Meeting;
import com.tambo.model.VO.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-14T08:04:50")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-22T18:50:55")
>>>>>>> 3rd_iter
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile SingularAttribute<Question, Integer> karma;
    public static volatile SingularAttribute<Question, User> studentEmail;
    public static volatile SingularAttribute<Question, User> teacherEmail;
    public static volatile SingularAttribute<Question, Integer> questionId;
    public static volatile SingularAttribute<Question, String> description;
    public static volatile SingularAttribute<Question, Meeting> meetingId;
    public static volatile SingularAttribute<Question, Boolean> state;

}