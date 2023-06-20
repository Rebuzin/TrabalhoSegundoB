package com.example.trabalhosegundob.runner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalhosegundob.R;

import java.util.ArrayList;

public class RelacaoNotasAluno extends AppCompatActivity {
    private Spinner spAlunos;
    private ArrayList listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacao_notas_aluno);

        Intent cadastro = getIntent();
        Bundle dados = new Bundle();
        dados = cadastro.getExtras();

        spAlunos = (Spinner) findViewById(R.id.spAlunos);
        listaAlunos = dados.getParcelable("listaAlunos");

        ArrayAdapter adAlunos = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaAlunos);

        spAlunos.setAdapter(adAlunos);
    }
}
