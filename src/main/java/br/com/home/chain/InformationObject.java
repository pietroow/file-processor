package br.com.home.chain;

public class InformationObject {

    private Integer specialCharactersAmount;
    private Integer numberOfWords;

    public void setSpecialCharactersAmount(Integer specialCharactersAmount) {
        this.specialCharactersAmount = specialCharactersAmount;
    }

    public void setNumberOfWords(Integer numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    @Override
    public String toString() {
        return "InformationObject{" +
                "specialCharactersAmount=" + specialCharactersAmount +
                ", numberOfWords=" + numberOfWords +
                '}';
    }

}
