package br.com.home;

import java.util.Arrays;

public enum TypeEnum {

    PDF("pdf"),
    TXT("txt"),
    DOC("doc");

    private final String label;

    TypeEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static TypeEnum findByText(String text) {
        return Arrays.stream(TypeEnum.values())
                .filter(typeEnum -> typeEnum.label.equals(text))
                .findFirst()
                .orElse(null);
    }

}
