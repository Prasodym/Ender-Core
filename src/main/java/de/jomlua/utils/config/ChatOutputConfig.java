package de.jomlua.utils.config;

import de.jomlua.utils.ChatOutput;

import java.io.File;

public class ChatOutputConfig extends Config{

    public ChatOutputConfig(File file) {
        super(file);

        for (ChatOutput message : ChatOutput.values()){
            message.setText(
                    translateColor((String) this.setDefault(message.getConfigName(), message.getText()))
            );
        }
    }

    public String getMessage(ChatOutput message){
        String path = message.getConfigName();
        if (this.contains(path)){
            return (String) this.get(path);
        }
        return null;
    }

    public void setMessage(ChatOutput message, String text){
        message.setText(translateColor(text));
        this.set(message.getConfigName(),text);
    }
    private String translateColor(String text){
        return text.replace("&","§");
    }
}
