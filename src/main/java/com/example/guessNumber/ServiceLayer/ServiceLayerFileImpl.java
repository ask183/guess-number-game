/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.guessNumber.ServiceLayer;

import com.example.guessNumber.Model.Game;
import com.example.guessNumber.Model.Round;
import com.example.guessNumber.Model.gameDAO;
import com.example.guessNumber.Model.roundDAO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author abekoppal
 */
@Service
public class ServiceLayerFileImpl implements ServiceLayer {
    
    
    private gameDAO gDAO;
    private roundDAO rDAO;
    
    @Autowired
    public ServiceLayerFileImpl(gameDAO gDAO, roundDAO rDAO){
        this.gDAO = gDAO;
        this.rDAO = rDAO;
    }

    @Override
    public List<Game> getAllGames() {
        //nothing to do on service layer, the DAO takes care of everything
        //pass through 
        return gDAO.getAll();
    }

    @Override
    public Game getGameByID(int gameID) {
        //nothing to do on service layer, the DAO takes care of everything
        //pass through 
        return gDAO.findById(gameID);
    }

    @Override
    public List<Round> getRoundsForGame(int gameID) {
        return rDAO.getRoundsForAGame(gameID);
    }
    
    @Override
    public Game createGame() {
        //start a game
        Game game = new Game();
        game.getId();
        //generate an answer
        game.setAnswer(generateAnswerForAGame());
        //set correct status
        game.setInProgress(true);
        //add game object to gameDAO        
        game = gDAO.add(game);
        //return a game
        return game;
    }
    
    public String generateAnswerForAGame(){
        Random randomAnswer = new Random();
        int num1 = randomAnswer.nextInt(10);
        
        int num2 = randomAnswer.nextInt(10);
        while(num2 == num1){
            num2 = randomAnswer.nextInt(10);
        }
        
        int num3 = randomAnswer.nextInt(10);
        while(num3 == num2 || num3 == num1){
            num3 = randomAnswer.nextInt(10);
        }
        
        int num4 = randomAnswer.nextInt(10);
        while (num4 == num3 || num4 == num3 || num4 == num1 ){
            num4 = randomAnswer.nextInt(10);
        }
        
        String answer = String.valueOf(num1) + String.valueOf(num2) + String.valueOf(num3) + String.valueOf(num4);
        
        return answer;
    }
    
    
    @Override
    public Round createRound(int gameId, String guess) {
        Round round = new Round();  
        Game game = new Game();
        
        game = gDAO.findById(gameId);
        String answer = gDAO.findById(gameId).getAnswer();
        String result = determineResultOfARound(guess, answer);
        
        round.setResult(result);
        round.setGuess(guess);
        round.setMyGame(game);
        round.setTime(LocalDateTime.now());
        
        
        
        if(guess.equals(answer)){
            game.setInProgress(false);
            gDAO.update(game);
        }
        
        return rDAO.add(round);
    } 
    
   
    
    public String determineResultOfARound(String guess, String answer){
        char[] guessDigits = guess.toCharArray();
        char[] answerDigits = answer.toCharArray();
        int exactMatch = 0;
        int partialMatch = 0;
        
        for(int i = 0; i < guessDigits.length; i++ ){
            if (answer.indexOf(guessDigits[i]) > -1){   //-1 indicates the index value of guess Does Not Exists in answer
                if (guessDigits[i] == answerDigits[i]){
                    exactMatch++;
                }else{
                    partialMatch++;
                }
            }
        }
        
        String result = "e:" + exactMatch + ":p:" + partialMatch;
        return result;
    }
    
}
