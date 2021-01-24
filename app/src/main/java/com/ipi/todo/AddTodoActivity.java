package com.ipi.todo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ipi.todo.pojos.Todo;

import java.util.ArrayList;
import java.util.List;

public class AddTodoActivity extends AppCompatActivity {

    //Variable d'identification de AddTodoActivity
    private static final int REQUESTE_CODE = 1;

    //clé pour envoyer le TODO_
    public static final String KEY_TODO = "todo";


    //Déclaration variables
    private EditText etName;
    private Button btnAdd;
    private Button btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        //Initialisation des variables
        etName = findViewById(R.id.etName);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);


        ////////////////////////////////Zone GO/BACK////////////////
        //!!récupère la barre d'action
        ActionBar actionBar = getSupportActionBar();

        //!!affiche le go/back (flèche) dans la barre d'action
        actionBar.setDisplayHomeAsUpEnabled(true);

        ////////////////////////////////////////////////////////////


        ////////////////////////////////Zone Spinner///////////////
        //Création de la liste
        List<String> itemSpinner = new ArrayList<String>();

        //Ajout dans la liste
        itemSpinner.add("Low Urgency");
        itemSpinner.add("Medium Urgency");
        itemSpinner.add("Hight Urgency");

        //Initialisation de l'adapteur pour le spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, itemSpinner);

        //Ajout de la liste à l'adapteur
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        //Ajout de l'adapteur au spinner
        spinner.setAdapter(spinnerArrayAdapter);

        ////////////////////////////////////////////////////////////


        /////////////////////////////////Zone bouton Add////////////
        //Le bouton Add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Récupération du name et mise en String
                String name = etName.getText().toString();

                //Récupération de l'urgency de la sélection du spinner
                String urgency = spinner.getSelectedItem().toString();

                //Initialisation du Contexte
                Context context = getApplicationContext();

                //Vérification de la longueur du Name
                if (name.length() < 3){
                    //On affiche un toast pour indiquer que la longueur est insuffisante
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, "Veuillez saisir au moins 3 caractères", duration);
                    toast.show();

                }else {
                    //On récupère l'intent qui a servi à créer cette activité
                    Intent resultIntent = getIntent();

                    //On créer un objet Todo_ et on le remplit
                    Todo todo = new Todo();
                    todo.setId(1);
                    todo.setName(name);
                    todo.setUrgency(urgency);

                    //créer bundle
                    Bundle bundle = new Bundle();

                    //ajouter le todo_ au bundle
                    bundle.putSerializable(KEY_TODO, todo);

                    //ajout bundle à l'intent
                    resultIntent.putExtras(bundle);

                    //////////////////////////////////////////////
                    //Méthode sans utiliser le POJO
                    /*On envoie des String
                    //On ajout les valeurs récupérées plus haut
                    resultIntent.putExtra("name", name);
                    resultIntent.putExtra("urgency", urgency);*//*

                    resultIntent.putExtra("name", todo_.getName());
                    resultIntent.putExtra("urgency", todo_.getUrgency());*/
                    //////////////////////////////////////////////////////

                    //On ajout la variable d'identification de l'activité
                    resultIntent.putExtra("requestCode", REQUESTE_CODE);

                    //On envoie le résultat OK à MainActivity et l'intent
                    setResult(RESULT_OK, resultIntent);

                    //fin de l'activité
                    finish();
                }
            }
        });
        ///////////////////////////////////////////////////////////////


        ///////////////////////////////////Zone bouton Cancel//////////
        //Le bouton Cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On annule
                Intent resultIntent = new Intent();

                //On ajout la variable d'identification de l'activité
                resultIntent.putExtra("requestCode", REQUESTE_CODE);

                //On envoie le résultat CANCEL à MainActivity
                setResult(RESULT_CANCELED, resultIntent);
                finish();
            }
        });
        ///////////////////////////////////////////////////////////////

    }

    ////////////////////////////////Zone GO/BACK////////////////
    //quand on clique sur le bouton Go/back, on retourne juste sur la MainActivity
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    /////////////////////////////////////////////////////////////

}