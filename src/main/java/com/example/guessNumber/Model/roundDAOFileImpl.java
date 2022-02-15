/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.guessNumber.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abekoppal
 */
@Repository
public class roundDAOFileImpl implements roundDAO {
    
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private gameDAO gd;
    
    @Autowired
    public roundDAOFileImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int rowNum) throws SQLException {
            Round round = new Round();
            round.setId(rs.getInt("roundID"));
            round.setGuess(rs.getString("guess"));
            
            Timestamp timestamp = rs.getTimestamp("guessTime");
            round.setTime(timestamp.toLocalDateTime());
            
            round.setResult(rs.getString("result"));
            
            return round;
        }
    }
    
    @Override
    @Transactional
    public Round add(Round round) {
        final String INSERT_ROUND = "INSERT INTO round(guess, guessTime, result, gameID) VALUES(?,?,?,?)";
        jdbcTemplate.update(INSERT_ROUND, round.getGuess(), round.getTime(), round.getResult(), round.getMyGame().getId());
        
        int newRoundID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setId(newRoundID);
        return findById(newRoundID);
    }

    @Override
    public Round findById(int Id) {
        try{
            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE roundID = ?";
            Round round = jdbcTemplate.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(), Id);
            round.setMyGame(gd.findById(Id));
            return round;
        }catch(DataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Round> getAll(int Id) {
        final String SELECT_ALL_ROUNDS = "SELECT * FROM round";
        List<Round> rounds = jdbcTemplate.query(SELECT_ALL_ROUNDS, new RoundMapper());
        for (Round round: rounds ){
            round.setMyGame(gd.findById(Id));
        }
        return rounds;
    }
    
    @Override
    public List<Round> getRoundsForAGame(int Id){
            final String SELECT_ALL_ROUNDS_FOR_GAME = "SELECT * FROM round WHERE gameID = ?";
            List<Round> rounds = jdbcTemplate.query(SELECT_ALL_ROUNDS_FOR_GAME, new RoundMapper(), Id);
            for (Round round: rounds ){
                round.setMyGame(gd.findById(Id));
            }
            return rounds;
    }
        
    

    @Override
    public boolean update(Round round) {
        final String UPDATE_ROUND = "UPDATE round SET "
                + "guess = ?, "
                + "guessTime = ?, "
                + "result = ?, "
                + "gameID = ?"
                + "WHERE roundID = ?;";

        return jdbcTemplate.update(UPDATE_ROUND,
                round.getGuess(),
                round.getTime(),
                round.getResult(),
                round.getMyGame(),
                round.getId()) > 0; 
    }

    @Override
    public boolean deleteById(int Id) {
        final String DELETE_ROUND = "DELETE FROM round WHERE Id = ?;";
        return jdbcTemplate.update(DELETE_ROUND, Id) > 0;
    }
    


    
    
}
