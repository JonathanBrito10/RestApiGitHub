package com.jonathanbrito.restgithub.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import com.bumptech.glide.Glide;
import com.jonathanbrito.restgithub.R;

public class DetalhesActivity extends AppCompatActivity {
    TextView link,nome;
    Toolbar nActionBar;
    ImageView imageView;
    String avatarUrl;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView = (ImageView)findViewById(R.id.foto);
        nome = (TextView)findViewById(R.id.nome);
        link = (TextView)findViewById(R.id.gitLink);

        String nomeUsuario = getIntent().getExtras().getString("login");
        avatarUrl = getIntent().getExtras().getString("avatar_url");
        String mlink = getIntent().getExtras().getString("html_url");
        link.setText(mlink);
        Linkify.addLinks(link, Linkify.WEB_URLS);
        nome.setText(nomeUsuario);

        carregarImagem();

        getSupportActionBar().setTitle("Detalhes do Usuário");
    }

    //Função de compartilhamento
    private Intent compartilhar(){
        String nomeUsuario =  getIntent().getExtras().getString("login");
        String mLink = getIntent().getExtras().getString("link");
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("CheckOut")
                .getIntent();
        return  shareIntent;
    }

    //Carregando Imagem usando Api Glide e exibindo no imageview
    void carregarImagem(){

        Glide.with(this)
                .load(avatarUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menudetalhe,menu);
        MenuItem menuItem= menu.findItem(R.id.mShare);
        menuItem.setIntent(compartilhar());

        return true;
    }
}
