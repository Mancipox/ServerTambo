package com.tambo.model.VO;

import com.tambo.model.VO.Meeting;
import com.tambo.model.VO.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-24T00:36:28")
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