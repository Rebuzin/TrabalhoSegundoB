package com.example.trabalhosegundob.controller;

import com.example.trabalhosegundob.strategy.Disciplina;
import com.example.trabalhosegundob.strategy.Mobile;
import com.example.trabalhosegundob.strategy.MontarNotasDisciplina;
import com.example.trabalhosegundob.strategy.POO;
import com.example.trabalhosegundob.strategy.Patterns;
import com.example.trabalhosegundob.strategy.Web;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private static HashMap<Integer, Double> notas = new HashMap();

    // <NO ARRAY "DISCIPLINAS", as posições referenciam os IDs das disciplinas. A seguir a ordem:
    // 1 = MOBILE
    // 2 = PATTERNS
    // 3 = POO
    // 4 = WEB>
    private static ArrayList<Disciplina> disciplinas = new ArrayList<>();
    private static Disciplina disciplina = null;

    //Só monta quando a lista notas tiver tamanho igual a 4;
    public static void montarNotasDisciplina() {
        disciplina = MontarNotasDisciplina
                .montaNotasDisciplina(
                        notas.get(1), notas.get(2), notas.get(3), notas.get(4),
                        disciplina
                );
        disciplinas.add(disciplina);
        limpaDisciplina();
        limpaNotas();
    }

    //Controle por bimestre: Caso a nota com a chave corresponte ao bimestre já existir, retorna false. Caso contrário, adiciona a nota ao bimestre;
    public static boolean addNota(int bimestre, double nota) {
        if (notas.get(bimestre) == null) {
            notas.put(bimestre, nota);
            return true;
        }
        return false;
    }

    public static boolean verificaDisciplinaSeleciona(int IDdisciplina) {
        switch (IDdisciplina) {
            case 1:
                if (disciplina instanceof Mobile) { return false; }
                break;
            case 2:
                if (disciplina instanceof Patterns) { return false; }
                break;
            case 3:
                if (disciplina instanceof POO) { return false; }
                break;
            case 4:
                if (disciplina instanceof Web) { return false; }
                break;
        }
        return true;
    }

    public static void iniciaDisciplina(int id_disciplina) {
        switch (id_disciplina) {
            case 1: //-----------------------MOBILE
                if (disciplina == null) {
                    disciplina = new Mobile();
                }
                break;
            case 2: //-----------------------PATTERNS
                if (disciplina == null) {
                    disciplina = new Patterns();
                }
                break;
            case 3://------------------------POO
                if (disciplina == null) {
                    disciplina = new POO();
                }
                break;
            case 4://------------------------WEB
                if (disciplina == null) {
                    disciplina = new Web();
                }
                break;
        }
    }

    //DEVE SER EXECUTADO APÓS TODA INSERÇÃO DE DISCIPLINA NA LISTA DE DISCIPLINAS
    public static void limpaDisciplina() {
        disciplina = null;
    }

    public static void limpaNotas() {
        notas.clear();
    }


    public static Disciplina getDisciplina() {
        return disciplina;
    }

    public static ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public static void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        Controller.disciplinas = disciplinas;
    }

    public static void setDisciplina(Disciplina disciplina) {
        Controller.disciplina = disciplina;
    }

    public static HashMap<Integer, Double> getNotas() {
        return notas;
    }

}
