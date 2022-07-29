package br.com.home.chain.implementations;

import br.com.home.chain.InformationObject;

public class NumberOfWordsImpl implements CreateInformationChain {

    @Override
    public void process(String content, InformationObject informationObject) {
        String trim = content.trim();

        Integer totalWords = trim.isEmpty()
                ? 0
                : trim.split("\\s+").length;

        informationObject.setNumberOfWords(totalWords);
    }

}
