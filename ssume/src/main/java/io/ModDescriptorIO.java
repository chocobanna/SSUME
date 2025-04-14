package com.ssume.io;

import com.ssume.model.ModDescriptor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ModDescriptorIO {

    public static ModDescriptor loadFromFile(File file) throws Exception {
        String content = new String(Files.readAllBytes(file.toPath()));
        JSONObject json = new JSONObject(content);
        
        ModDescriptor md = new ModDescriptor();
        md.setId(json.optString("id", ""));
        md.setName(json.optString("name", ""));
        md.setAuthor(json.optString("author", ""));
        md.setTotalConversion(json.optString("totalConversion", "false").equalsIgnoreCase("true"));
        md.setUtility(json.optString("utility", "false").equalsIgnoreCase("true"));
        md.setVersion(json.optString("version", ""));
        md.setDescription(json.optString("description", ""));
        md.setGameVersion(json.optString("gameVersion", ""));
        
        List<String> replaceList = new ArrayList<>();
        JSONArray replaceArr = json.optJSONArray("replace");
        if (replaceArr != null) {
            for (int i = 0; i < replaceArr.length(); i++) {
                replaceList.add(replaceArr.getString(i));
            }
        }
        md.setReplace(replaceList);
        
        List<String> jarsList = new ArrayList<>();
        JSONArray jarsArr = json.optJSONArray("jars");
        if (jarsArr != null) {
            for (int i = 0; i < jarsArr.length(); i++) {
                jarsList.add(jarsArr.getString(i));
            }
        }
        md.setJars(jarsList);
        
        md.setModPlugin(json.optString("modPlugin", ""));
        
        return md;
    }
    
    public static void saveToFile(ModDescriptor md, File file) throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", md.getId());
        json.put("name", md.getName());
        json.put("author", md.getAuthor());
        json.put("totalConversion", md.isTotalConversion() ? "true" : "false");
        json.put("utility", md.isUtility() ? "true" : "false");
        json.put("version", md.getVersion());
        json.put("description", md.getDescription());
        json.put("gameVersion", md.getGameVersion());
        
        JSONArray replaceArray = new JSONArray();
        for(String s : md.getReplace()) {
            replaceArray.put(s);
        }
        json.put("replace", replaceArray);
        
        JSONArray jarsArray = new JSONArray();
        for(String s : md.getJars()) {
            jarsArray.put(s);
        }
        json.put("jars", jarsArray);
        
        json.put("modPlugin", md.getModPlugin());
        
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(json.toString(4));
        }
    }
}
