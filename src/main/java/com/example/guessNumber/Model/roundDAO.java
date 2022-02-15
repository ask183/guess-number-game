/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.guessNumber.Model;

import java.util.List;

/**
 *
 * @author abekoppal
 */
public interface roundDAO {
    
    //C - CREATE
    Round add(Round round);
    
    //R - READONE
    Round findById(int Id);
    
    //R - READALL
    List<Round> getAll(int Id);
    
    List<Round> getRoundsForAGame(int Id);
    
    //U - UPDATE
    //true if round exists and is updated
    boolean update(Round round);
    
    //D - DELETE
    //true if round exists and is deleted
    boolean deleteById(int Id);
}
