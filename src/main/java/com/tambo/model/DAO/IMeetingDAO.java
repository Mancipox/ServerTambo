/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.model.DAO;

import com.tambo.model.VO.Meeting;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface IMeetingDAO {
        public int makeMeet(Meeting meet) throws Exception;
public List<Meeting> meets() throws Exception;
}
