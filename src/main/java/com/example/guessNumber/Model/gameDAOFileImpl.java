/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.guessNumber.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author abekoppal
 */
@Repository
public class gameDAOFileImpl implements gameDAO {
    
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public gameDAOFileImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("gameID"));
            game.setAnswer(rs.getString("answer"));
            game.setInProgress(rs.getBoolean("inProgress"));    
            return game;
        }
    }

    @Override
    public Game add(Game game) {
        final String INSERT_GAME = "INSERT INTO game(answer) VALUES (?)";
        jdbcTemplate.update(INSERT_GAME, game.getAnswer());
        
        int newGameId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setId(newGameId);
        return game;
    }

    @Override
    public Game findById(int Id) {
        try{
            final String SELECT_GAME_BY_ID = "SELECT * FROM game WHERE gameID = ?";
            return jdbcTemplate.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), Id);
        }catch(DataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Game> getAll() {
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbcTemplate.query(SELECT_ALL_GAMES, new GameMapper());
    }

    @Override
    public boolean update(Game game) {
       /* final String UPDATE_GAME = "UPDATE game SET inProgress = ? WHERE gameID = ?";
        jdbcTemplate.update(UPDATE_GAME, game.isInProgress(), game.getId());*/
        
         final String UPDATE_GAME = "UPDATE game SET "
                + "answer = ?, "
                + "inProgress = ? "
                + "WHERE gameID = ?;";

        return jdbcTemplate.update(UPDATE_GAME,
                game.getAnswer(),
                game.isInProgress(),
                game.getId()) > 0;
    }

    @Override
    public boolean deleteById(int Id) {
        final String DELETE_GAME = "DELETE FROM game WHERE Id = ?;";
        return jdbcTemplate.update(DELETE_GAME, Id) > 0;
    }
    
}
