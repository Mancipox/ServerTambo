/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.facade;

import java.lang.ProcessBuilder.Redirect.Type;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface IPersistenceFacade<T>{
  public List search(T object) throws Exception;
  public List searchByCriteria(T object,List<String> crit, List values)throws Exception;
    public boolean make(T object)throws Exception;
    public boolean update(T object, String crit, Object value)throws Exception;
}
