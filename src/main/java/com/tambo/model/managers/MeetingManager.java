
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.model.managers;

import com.tambo.facade.IPersistenceFacade;
import com.tambo.facade.PersistenceFacadeFactory;
import com.tambo.model.VO.Meeting;
import com.tambo.utils.Utils;
import java.util.List;

/**
 *
 * @author usuario
 */
public class MeetingManager {
    IPersistenceFacade facade= new PersistenceFacadeFactory().getNewFacade();
    List<Meeting> meets;
    Meeting meet= new Meeting();
    public String getMeets() throws Exception{
       meets= facade.search(meet);
    return Utils.toJson(meets);
    }
    
    public String persist(String jsonM) throws Exception{
        meet= (Meeting) Utils.fromJson(jsonM, Meeting.class);
        boolean res=facade.make(meet);
    return Utils.toJson(res);
    }
}

