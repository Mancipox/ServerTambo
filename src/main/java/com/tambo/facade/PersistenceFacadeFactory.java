/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.facade;

/**
 *
 * @author usuario
 */
public class PersistenceFacadeFactory {
    public IPersistenceFacade getNewFacade(){
        return new PersistenceFacade();
    }
}
