package com.ipi.todo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ipi.todo.pojos.Todo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Déclaration des attributs des vues
    private TextView tvTodos;

    //un test d'affichage
    String todos = "Liste de Tâches à faire : ";

    //clé pour le bundle
    public static final String KEY_TODOS = "todos";






    //////////////////////////////////////ZONE ONCREATE/////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Récupérer les éléments de la vue
        tvTodos = findViewById(R.id.tvTodos);


        //On teste si le bundle est utilisé (pour le sauvegarder au besoin)
        if (savedInstanceState != null){
            todos = savedInstanceState.getString(KEY_TODOS);
        }

        //(Démarrage)
        //On affiche le TextView
        tvTodos.setText(todos);
    }
    //////////////////////////////////////////////////////////


    ///////////////////////////////////ZONE BUNDLE////////////
    //Sauvegarde les Todos en cas de changement d'état
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Informations envoyées au bundle pour sauvegarder
        outState.putSerializable(KEY_TODOS, todos);
    }
    //////////////////////////////////////////////////////////


    //////////////////////////////////////ZONE MENU///////////
    //Affichage du menu de cette activité
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Création du menu et de ses items
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    //Action à faire à la sélection de l'item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //traite ce qui doit être fait en fonction de l'item sélectionné
        switch (item.getItemId()){
            //Si on a cliqué sur l'item Add Todo_
            case R.id.addTodo:
                //Création de l'objet intent qui lance l'activité
                Intent intent = new Intent(getApplicationContext(), AddTodoActivity.class);

                //Lancement de la nouvelle activité (avec la méthode startActivityForResult qui permet de récupérer l'objet Todo_)
                //startActivity(intent);//simple test pour afficher l'activité
                startActivityForResult(intent, 1); //le requestCode indique le code de AddTodoActivity (j'ai arbitrairement choisi 1)

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    ///////////////////////////////////////////////////////////


    ///////////////////////////////////////ZONE DE RETOUR ET D'AFFICHAGE
    //On test le retour de l'activité AddToDoActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //On vérifie le numéro de l'activity et le résultCode
        if ((requestCode == 1)&&(resultCode == RESULT_OK)){

            //////////////////////////////////////////////////
        /*/Ici méthode qui fonctionnne avec l'envoi de String
        //On récupère la variable name envoyée par AddTodoActivity
        String name = String.valueOf(data.getSerializableExtra("name"));

        //On récupère la variable urgency envoyée par AddTodoActivity
        String urgency = String.valueOf(data.getSerializableExtra("urgency"));

        //On formate l'affichage
        String autreTodo = name + " / " + urgency;
        todos += " \n " + autreTodo;*/
        //////////////////////////////////////////////////


            //on récupère l'intent envoyé par AddActivity
            //L'intent est dans le data


        //récupérer bundle qui est dans l'intent
        Bundle bundle = data.getExtras();

            //récupérer le todo_
            Todo autretodo = (Todo)bundle.getSerializable(AddTodoActivity.KEY_TODO);

            //On formate l'affichage
            //autreTodo = autretodo.getName() + " / " + autretodo.getUrgency();
            todos += " \n " + autretodo.getName() + " / " + autretodo.getUrgency();


        //On affiche dans le TextView
        //tvTodos.setText(todos);
            //tvTodos.setText(autretodo.getName());
            tvTodos.setText(todos);

        }

    }
    ///////////////////////////////////////////////////////////

}