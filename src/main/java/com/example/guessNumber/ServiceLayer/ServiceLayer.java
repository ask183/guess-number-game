/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.guessNumber.ServiceLayer;

import com.example.guessNumber.Model.Game;
import com.example.guessNumber.Model.Round;
import java.util.List;

/**
 *
 * @author abekoppal
 */
public interface ServiceLayer {
    
    public Game createGame();
    
    public Round createRound(int gameId, String guess);
    
    public List<Game> getAllGames();    //BUT not show the answer for game(s) in progress!!!
    
    public Game getGameByID(int gameID);   //BUT not show the answer for game in progress!!!
    
    public List<Round> getRoundsForGame(int gameID);

    
}
