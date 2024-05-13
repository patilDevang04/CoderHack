package com.devang.coderhack.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
@Document(collection = "users")
public class User {
    @Id
    private String mongoEntityId; 

    private Long userId; 
    
    private String userName; 
    
    private Long score; 
    
    private Set<Badge> badges;
    
    public String getMongoEntityId() {
        return mongoEntityId;
    }
    public void setMongoEnitityId(String mongoEntityId) {
        this.mongoEntityId = mongoEntityId;   
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Long getScore() {
        return score;
    }
    public void setScore(Long score) {
        this.score = score;
        updateBadges();
    }
    public Set<Badge> getBadges() {
        return badges;
    }
    public void setBadges(Set<Badge> badges) {
        this.badges = badges;
    }

    private void updateBadges() {
        if (badges == null) {
            badges = new HashSet<>();
        }

        if (score < 30) {
            addBadge(Badge.CODE_NINJA);
        } else if (score < 60) {
            addBadge(Badge.CODE_CHAMP);
        } else if (score <= 100) {
            addBadge(Badge.CODE_MASTER);
        }
    }

    private void addBadge(Badge badge) {
        if (badges.size() < 3 && !badges.contains(badge)) {
            badges.add(badge);
        }
    }
}