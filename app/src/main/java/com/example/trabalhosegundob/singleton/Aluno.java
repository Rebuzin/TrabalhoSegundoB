package com.example.trabalhosegundob.singleton;

import com.example.trabalhosegundob.Boletim;

public class Aluno {
    private static Aluno aluno = null;
    private String nome;
    private String ra;
    private Boletim boletim;

    public Aluno() {}

    public Aluno(String nome, String ra, Boletim boletim) {
        this.nome = nome;
        this.ra = ra;
        this.boletim = boletim;
    }

    public synchronized static Aluno getInstancia() {
        if (aluno == null) {
            return aluno = new Aluno();
        } else return aluno;
    }

    public Boletim getBoletim() {
        return boletim;
    }

    public void setBoletim(Boletim boletim) {
        this.boletim = boletim;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }
}
