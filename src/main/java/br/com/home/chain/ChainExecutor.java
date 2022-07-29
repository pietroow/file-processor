package br.com.home.chain;

import br.com.home.chain.implementations.CreateInformationChain;
import br.com.home.chain.implementations.NumberOfWordsImpl;
import br.com.home.chain.implementations.SpecialCharactersImpl;

import java.util.List;

public class ChainExecutor {

    private ChainExecutor() {
    }

    public static InformationObject execute(String content) {

        InformationObject informationObject = new InformationObject();

        List<CreateInformationChain> chain = List.of(
                new SpecialCharactersImpl(),
                new NumberOfWordsImpl()
        );

        chain.forEach(createInformationChain -> createInformationChain.process(content, informationObject));

        return informationObject;
    }

}
