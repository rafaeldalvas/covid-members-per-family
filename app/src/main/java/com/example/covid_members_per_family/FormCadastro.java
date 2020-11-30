package com.example.covid_members_per_family;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.covid_members_per_family.BDHelper.FamiliasDB;
import com.example.covid_members_per_family.model.Familias;

public class FormCadastro extends AppCompatActivity {
    EditText editText_sobrenome, editText_familiares, editText_infectados;
    Button btn_salvar;
    Familias editarFamilia, familia;
    FamiliasDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        familia = new Familias();
        dbHelper = new FamiliasDB(FormCadastro.this);

        Intent intent = getIntent();
        editarFamilia = (Familias) intent.getSerializableExtra("familia-escolhida");

        editText_sobrenome = (EditText) findViewById(R.id.editText_email);
        editText_familiares = (EditText) findViewById(R.id.editText_nome);
        editText_infectados = (EditText) findViewById(R.id.editText_bairro);

        btn_salvar = (Button) findViewById(R.id.btn_salvar);

        if (editarFamilia != null){
            btn_salvar.setText("Modificar");
            editText_sobrenome.setText(editarFamilia.getSobrenome());
            editText_familiares.setText(editarFamilia.getNumFamiliares()+"");
            editText_infectados.setText(editarFamilia.getNumInfectados()+"");
            familia.setId(editarFamilia.getId());
        }else{
            btn_salvar.setText("Cadastrar");
        }

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                familia.setSobrenome(editText_sobrenome.getText().toString());
                familia.setNumFamiliares(editText_familiares.getText().toString());
                familia.setNumInfectados(editText_infectados.getText().toString());

                if(btn_salvar.getText().toString().equals("Cadastrar")){
                    dbHelper.salvarFamilia(familia);
                    dbHelper.close();
                }else{
                    dbHelper.alterarFamilia(familia);
                    dbHelper.close();
                }
            }
        });

    }
}