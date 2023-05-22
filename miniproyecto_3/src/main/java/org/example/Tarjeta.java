package org.example;

public class Tarjeta {
    private String customImage;

    private boolean guessed;

    private int identifier;

    public void Tarjeta(){

        this.customImage = "";
        this.guessed = false;
        this.identifier = 0;
    }

    public void Tarjeta(String customImage, Boolean guessed, int identifier){
        this.customImage = customImage;
        this.guessed = guessed;
        this.identifier = identifier;
    }

    public String getCustomImage() {
        return customImage;
    }

    public void setCustomImage(String customImage) {
        this.customImage = customImage;
    }

    public boolean isGuessed() {
        return guessed;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}
