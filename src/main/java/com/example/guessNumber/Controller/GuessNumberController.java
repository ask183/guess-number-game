/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.guessNumber.Controller;

import com.example.guessNumber.Model.Game;
import com.example.guessNumber.Model.Round;
import com.example.guessNumber.ServiceLayer.ServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abekoppal
 */
@RestController
@RequestMapping("/api")
public class GuessNumberController {
    
    @Autowired
    ServiceLayer myServ;
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int createNewGame(){
        Game myNewGame = myServ.createGame();
        
        return myNewGame.getId();
    }
    
    
    @PostMapping("/guess")
    public String createRoundWithAGuess(int gameId, String guess){
        Round myRound = myServ.createRound(gameId, guess);
        return myRound.getResult();
    }   
    
    
    @GetMapping("/game")
    public List<Game> listGames(){
        List<Game> listOfAllGames = myServ.getAllGames();
        return listOfAllGames;
    }
    
    @GetMapping("/game/{Id}")
    public Game listGameById(@PathVariable("Id") int gameId){
        Game myGame = myServ.getGameByID(gameId);
        return myGame;
    }
    
    @GetMapping("/rounds/{Id}")
    public List<Round> listRoundsForGame(@PathVariable("Id") int gameId){
        List<Round> roundsForAGame = myServ.getRoundsForGame(gameId);
        return roundsForAGame;
    }
    
    
    
    
}
