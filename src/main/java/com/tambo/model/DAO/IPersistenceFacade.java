/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.model.DAO;

import java.lang.ProcessBuilder.Redirect.Type;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface IPersistenceFacade{
  public List search() throws Exception;
  public List searchByCriteria(List<String> crit, List values)throws Exception;
    public boolean make()throws Exception;
    public boolean update(List<String> crit, List values)throws Exception;
}
