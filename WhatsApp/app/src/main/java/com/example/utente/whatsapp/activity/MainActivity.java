package com.example.utente.whatsapp.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewParent;

import com.example.utente.whatsapp.R;
import com.example.utente.whatsapp.config.ConfiguracaoFirebase;
import com.example.utente.whatsapp.fragment.ContatosFragment;
import com.example.utente.whatsapp.fragment.ConversasFragment;
import com.example.utente.whatsapp.model.Conversa;
import com.google.firebase.auth.FirebaseAuth;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = ConfiguracaoFirebase.getFirebaseAuth();

        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("WhatsApp");
        setSupportActionBar(toolbar);

        //config the tabs
        final FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Conversas", ConversasFragment.class)
                .add("Contatos", ContatosFragment.class)
                .create()
        );

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = findViewById(R.id.viewPagerTab);
        viewPagerTab.setViewPager(viewPager);

        // config search view
        searchView = findViewById(R.id.materialSearchPrincipal);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("evento", "onQueryTextSubmit");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("evento", "onQueryTextChange");

                ConversasFragment fragment = (ConversasFragment) adapter.getPage(0);
                if(!newText.isEmpty() && newText != null){
                    fragment.pesquisarConversas(newText);
                }

                return true;
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        //start the search view from library
        MenuItem item = menu.findItem(R.id.menuPesquisa);
        searchView.setMenuItem(item);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuSair:
                deslogarUsuario();
                finish();
                break;
            case R.id.menuConfiguracoes:
                abrirConfiguracoes();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void deslogarUsuario() {

        try {
            auth.signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void abrirConfiguracoes(){
        Intent intent = new Intent(MainActivity.this, ConfiguracoesActivity.class);
        startActivity(intent);
    }

}
