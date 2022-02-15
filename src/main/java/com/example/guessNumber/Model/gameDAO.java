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
public interface gameDAO {
    
    //C - CREATE
    Game add(Game game);
    
    //R - READONE
    Game findById(int Id);
    
    //R - READALL
    List<Game> getAll();
    
    //U - UPDATE
    //true if game exists and is updated
    boolean update(Game game);
    
    //D - DELETE
    //true if game exists and is deleted
    boolean deleteById(int Id);
}
