package com.ssume.model;

import java.util.ArrayList;
import java.util.List;

public class ModDescriptor {
    private String id;
    private String name;
    private String author;
    private boolean totalConversion;
    private boolean utility;
    private String version;
    private String description;
    private String gameVersion;
    private List<String> replace = new ArrayList<>();
    private List<String> jars = new ArrayList<>();
    private String modPlugin;
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public boolean isTotalConversion() {
        return totalConversion;
    }
    public void setTotalConversion(boolean totalConversion) {
        this.totalConversion = totalConversion;
    }
    public boolean isUtility() {
        return utility;
    }
    public void setUtility(boolean utility) {
        this.utility = utility;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getGameVersion() {
        return gameVersion;
    }
    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }
    public List<String> getReplace() {
        return replace;
    }
    public void setReplace(List<String> replace) {
        this.replace = replace;
    }
    public List<String> getJars() {
        return jars;
    }
    public void setJars(List<String> jars) {
        this.jars = jars;
    }
    public String getModPlugin() {
        return modPlugin;
    }
    public void setModPlugin(String modPlugin) {
        this.modPlugin = modPlugin;
    }
}
