package com.parse.starter.util;

import java.util.HashMap;

/**
 * Created by v1n1c on 31/07/2017.
 */

public class ParseErros {

    private HashMap<Integer, String> erros;

    public ParseErros() {
        this.erros = new HashMap<>();
        this.erros.put(202, "Usuário já existe!");
        this.erros.put(201, "A senha não foi preenchida");
    }

    public String getErro(int codErro){
        return this.erros.get( codErro );
    }

}
