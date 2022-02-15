/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.guessNumber.Model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author abekoppal
 */
public class Game {
    
    private int Id;
    private String answer;
    private boolean inProgress;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.Id;
        hash = 23 * hash + Objects.hashCode(this.answer);
        hash = 23 * hash + (this.inProgress ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (this.inProgress != other.inProgress) {
            return false;
        }
        return Objects.equals(this.answer, other.answer);
    }

    @Override
    public String toString() {
        return "Game{" + "Id=" + Id + ", answer=" + answer + ", inProgress=" + inProgress + '}';
    }
    
}
