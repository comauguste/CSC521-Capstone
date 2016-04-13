/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.interfaces;

import java.util.List;

/**
 *
 * @author Auguste C
 */
public interface CredentialsDaoInterface<T> {
    
    public List<T> getApproveUsers();
    
}
