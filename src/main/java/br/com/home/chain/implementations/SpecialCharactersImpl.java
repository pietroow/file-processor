package br.com.home.chain.implementations;

import br.com.home.chain.InformationObject;

public class SpecialCharactersImpl implements CreateInformationChain {

    @Override
    public void process(String content, InformationObject informationObject) {
        Integer totalSpecial = checkHasValue(content)
                ? 0
                : count(content);
        informationObject.setSpecialCharactersAmount(totalSpecial);
    }

    private Integer count(String content) {
        String newString = content.replaceAll("[A-Za-z0-9\\s]", "");
        return newString.length();
    }

    private boolean checkHasValue(String content) {
        return content == null || content.trim().isEmpty();
    }

}
