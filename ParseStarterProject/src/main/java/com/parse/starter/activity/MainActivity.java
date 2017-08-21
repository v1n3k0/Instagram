/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.adapter.TabsAdapter;
import com.parse.starter.util.SlidingTabLayout;


public class MainActivity extends AppCompatActivity {

  private Toolbar toolbarPrincipal;
  private SlidingTabLayout slidingTabLayout;
  private ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //Configurar toolbar
    toolbarPrincipal = (Toolbar) findViewById(R.id.toolbar_principal);
    toolbarPrincipal.setLogo( R.drawable.instagramlogo );
    setSupportActionBar( toolbarPrincipal );

    //Configurar abas
    slidingTabLayout  = (SlidingTabLayout) findViewById(R.id.sliding_tab_main);
    viewPager         = (ViewPager) findViewById(R.id.view_pager_main);

    //Configurar adapter
    TabsAdapter tabsAdapter = new TabsAdapter( getSupportFragmentManager(), this );
    viewPager.setAdapter( tabsAdapter );
    slidingTabLayout.setCustomTabView( R.layout.tabs_view, R.id.text_item_tab );
    slidingTabLayout.setDistributeEvenly(true);
    slidingTabLayout.setSelectedIndicatorColors(R.color.cinzaEscuro);
    slidingTabLayout.setViewPager( viewPager );

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch ( item.getItemId() ){
      case R.id.action_sair:
        deslogarUsuario();
        return true;
      case R.id.action_condiguracoes:
        return true;
      case R.id.action_compartilhar:
        compartilharFoto();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }

  }

  private void compartilharFoto(){

    Intent intent = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
    startActivityForResult( intent, 1);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    //Testar processo de retorno de dados
    if( requestCode == 1 && resultCode == RESULT_OK && data != null ){



    }

  }

  private void deslogarUsuario(){
    ParseUser.logOut();
    Intent intent = new Intent(this, LoginActivity.class);
    startActivity(intent);
  }

}
