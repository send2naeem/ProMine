/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import java.util.ArrayList;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 *
 * @author Administrator
 */
public class Entity {
    
    public String word;
    public Double frequency;
    public Double probability;
    public Double information_gain;
    public Double similarity_measure;
    public ArrayList categories;       
        
    
    public Entity() {
        this.categories = new ArrayList();
        this.word = "";
        this.frequency = 0D;
        this.probability = 0D;
        this.information_gain = 0D;
        this.similarity_measure = 0D;
    }
    
    public Entity(String _input) {
        this();
        this.categories = new ArrayList();
        this.word = _input;
    }
    
    @Override
    public boolean equals(Object d) {
        if (!(d instanceof Entity)) {
            return false;
        }
        Entity entity = (Entity) d;
        return (this.word.toLowerCase().equals(entity.word.toLowerCase())
                && this.frequency.equals(entity.frequency)
                && this.information_gain.equals(entity.information_gain)
                && this.probability.equals(entity.probability)
                && this.similarity_measure.equals(entity.similarity_measure));
    }
    
    
}
