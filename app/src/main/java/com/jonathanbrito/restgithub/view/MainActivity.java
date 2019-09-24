package com.jonathanbrito.restgithub.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jonathanbrito.restgithub.controller.Client;
import com.jonathanbrito.restgithub.controller.ItemAdapter;
import com.jonathanbrito.restgithub.controller.Service;
import com.jonathanbrito.restgithub.model.Item;
import com.jonathanbrito.restgithub.model.ItemResponse;
import com.jonathanbrito.restgithub.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    TextView disconectado;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InicializarUI();

          swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                carregarJson();
                Toast.makeText(MainActivity.this,"Usuarios Atualizados",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void InicializarUI() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setColorSchemeResources(R.color.fundo);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        carregarJson();
    }

    private void carregarJson() {
        disconectado = (TextView) findViewById(R.id.disconnetado);
        try {
            Client Client = new Client();
            Service service = Client.getClient().create(Service.class);
            Call <ItemResponse> call = service.getItems();
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response <ItemResponse> response) {
                    List<Item> items = response.body().getItem();
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(),items));
                    recyclerView.smoothScrollToPosition(0);
                    swipeRefreshLayout.setRefreshing(false);
                }
                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.d("Error",t.getLocalizedMessage());
                    Toast.makeText(MainActivity.this,"Erro ao Buscar os Dados",Toast.LENGTH_SHORT).show();
                    disconectado.setVisibility(View.VISIBLE);                }
            });
        }catch (Exception e){
            Log.d("Erro",e.getMessage());
            Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }


}
