package com.example.trabalhosegundob;

import com.example.trabalhosegundob.strategy.Disciplina;

import java.util.ArrayList;

public class Boletim {
    private ArrayList<Disciplina> notasBimestraisPorDisciplina = new ArrayList();

    public Boletim(Disciplina mobile, Disciplina patterns, Disciplina poo, Disciplina web) {
        notasBimestraisPorDisciplina.add(mobile);
        notasBimestraisPorDisciplina.add(patterns);
        notasBimestraisPorDisciplina.add(poo);
        notasBimestraisPorDisciplina.add(web);
    }
}
