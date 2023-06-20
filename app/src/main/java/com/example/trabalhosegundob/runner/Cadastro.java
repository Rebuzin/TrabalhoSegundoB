package com.example.trabalhosegundob.runner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalhosegundob.R;
import com.example.trabalhosegundob.controller.Controller;
import com.example.trabalhosegundob.singleton.Aluno;

import java.util.ArrayList;
import java.util.HashMap;

public class Cadastro extends AppCompatActivity {
    private EditText edRa;
    private EditText edNome;
    private EditText edNota;

    private Spinner spBimestre;
    private Spinner spDisciplina;

    private String[] bimestres = {"Selecionar...", "1º Bim", "2º Bim", "3º Bim", "4º Bim"};
    private ArrayList<String> disciplinas = new ArrayList<>();

    private Aluno aluno = null;
    private HashMap<String, Aluno> listaAlunos = new HashMap();

    private int bimestreSelecionado = 0;
    private int disciplinaSelecionada = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        disciplinas.add("Selecionar...");
        disciplinas.add("Mobile");
        disciplinas.add("Patterns");
        disciplinas.add("POO");
        disciplinas.add("Web");

        edRa = (EditText) findViewById(R.id.edRa);
        edNome = (EditText) findViewById(R.id.edNome);
        edNota = (EditText) findViewById(R.id.edNota);

        spBimestre = (Spinner) findViewById(R.id.spBimestre);
        spDisciplina = (Spinner) findViewById(R.id.spDisciplina);

        ArrayAdapter adBimestres = new ArrayAdapter(this, android.R.layout.simple_list_item_1, bimestres);
        ArrayAdapter adDisciplinas = new ArrayAdapter(this, android.R.layout.simple_list_item_1, disciplinas);

        spBimestre.setAdapter(adBimestres);
        spDisciplina.setAdapter(adDisciplinas);


        spDisciplina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    disciplinaSelecionada = position;
                    Controller.iniciaDisciplina(disciplinaSelecionada);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spBimestre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    bimestreSelecionado = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void btAdicionarOnClick(View view) {
        if (validaCamposVazios()) {
            aluno = Aluno.getInstancia();

            if (Controller.verificaDisciplinaSeleciona(disciplinaSelecionada)) {
                //ADICIONA NOTA
                // QUANDO O BIMESTRE INFORMADO NÃO TIVER NOTA INFORMADA
                if (Controller.addNota(bimestreSelecionado, Double.parseDouble(edNota.getText().toString()))) {
                    Toast.makeText(this, "Nota adicionada!", Toast.LENGTH_SHORT).show();
                    edNota.setText("");
                } else {
                    Toast.makeText(this, "Selecione outro bimestre!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // CADASTRA A DISCIPLINA
                //  QUANDO TODAS AS NOTAS FORAM ADICIONADAS
                if (Controller.getNotas().size() == 4) {
                    Toast.makeText(this, "Todas as notas foram lançadas!", Toast.LENGTH_SHORT).show();
                    Controller.montarNotasDisciplina();
                    spDisciplina.setSelection(0);
                    spBimestre.setSelection(0);
                    return;
                } else {
                    if (Controller.getNotas().size() == 4) {
                        Toast.makeText(this, "Altere a disciplina", Toast.LENGTH_SHORT).show();
                    }
                }

                // SALVA O ALUNO
                //QUANDO CADASTRA TODAS AS DISCIPLINAS ESTÃO CADASTRADAS
                if (Controller.getDisciplinas().size() == 4) {
                    listaAlunos.put(aluno.getRa(), aluno);
                    Toast.makeText(this, "Aluno salvo!", Toast.LENGTH_SHORT).show();
                }
            }
        } else { Toast.makeText(this, "Disciplina já selecionada. Altere a disciplina", Toast.LENGTH_SHORT).show(); }
    }

    public void btVerNotasOnClick(View view) {
        if (!listaAlunos.isEmpty()) {
            Intent relacaoNotas = new Intent(this, RelacaoNotasAluno.class);
            relacaoNotas.putExtra("listaAlunos", listaAlunos);
            startActivity(relacaoNotas);
        }
    }

    public void btVerMediasOnClick(View view) {

    }

    private boolean validaCamposVazios() {
        if (disciplinaSelecionada == 0) {
            Toast.makeText(this, "Selecione uma disciplina!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (bimestreSelecionado == 0) {
            Toast.makeText(this, "Selecione um bimestre!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edNome.getText().equals("")) {
            Toast.makeText(this, "Digite o nome!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edRa.getText().equals("")) {
            Toast.makeText(this, "Digite o RA!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edNota.getText().equals("")) {
            Toast.makeText(this, "Digite a nota!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void limpaCampos() {
        edNome.setText("");
        edNota.setText("");
        edRa.setText("");
        spBimestre.setSelection(0);
        spDisciplina.setSelection(0);
    }

}
