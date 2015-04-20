package com.example.guadalupe.basededatos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText id_producto, nombre_Producto, Tipo_producto,descripcion_producto,precio,cantidad_existente,color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id_producto = (EditText) findViewById(R.id.idProducto);
        nombre_Producto= (EditText) findViewById(R.id.nombPro);
        Tipo_producto= (EditText) findViewById(R.id.tipoPro);
        descripcion_producto = (EditText) findViewById(R.id.Descrip);
        precio= (EditText) findViewById(R.id.precio);
        cantidad_existente = (EditText) findViewById(R.id.cantidad);
        color= (EditText) findViewById(R.id.color);

    }

    public void alta (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "producto", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String idproducto = id_producto.getText().toString();
        String  nombre_p= nombre_Producto.getText().toString();
        String Tipo_p = Tipo_producto.getText().toString();
        String descripcion = descripcion_producto.getText().toString();
        String pre = precio.getText().toString();
        String catidad = cantidad_existente.getText().toString();
        String col = color.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("id_producto", idproducto);
        registro.put("nombre_producto", nombre_p);
        registro.put("Tipo_producto", Tipo_p);
        registro.put("descripcion_producto", descripcion);
        registro.put("precio", pre);
        registro.put("cantidad_existente", catidad);
        registro.put("color", col);

        bd.insert("producto", null, registro);
        bd.close();

        id_producto.setText("");
        nombre_Producto.setText("");
        Tipo_producto.setText("");
        descripcion_producto.setText("");
        precio.setText("");
        cantidad_existente.setText("");
        color.setText("");


        Toast.makeText(this,"Se agrego un nuevo producto",Toast.LENGTH_SHORT).show();

    }

    public void limpiar (View v) {
        id_producto.setText("");
        nombre_Producto.setText("");
        Tipo_producto.setText("");
        descripcion_producto.setText("");
        precio.setText("");
        cantidad_existente.setText("");
        color.setText("");
    }

    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "producto", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String idproducto = id_producto.getText().toString();
        Cursor fila = bd.rawQuery("select nombre_producto,Tipo_producto,descripcion_producto,precio,cantidad_existente,color from producto where id_producto=" + idproducto, null);
        if (fila.moveToFirst()) {
            nombre_Producto.setText(fila.getString(0));
            Tipo_producto.setText(fila.getString(1));
            descripcion_producto.setText(fila.getString(2));
            precio.setText(fila.getString(3));
            cantidad_existente.setText(fila.getString(4));
            color.setText(fila.getString(5));


        } else {
            Toast.makeText(this,"No existe el producto",Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "producto", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String idproducto = id_producto.getText().toString();
        int cant = bd.delete("producto", "id_producto=" + idproducto, null);
        bd.close();

        id_producto.setText("");
        nombre_Producto.setText("");
        Tipo_producto.setText("");
        descripcion_producto.setText("");
        precio.setText("");
        cantidad_existente.setText("");
        color.setText("");

        if (cant == 1) {
            Toast.makeText(this, "Se borró el producto",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el producto",Toast.LENGTH_SHORT).show();
        }
    }

    public void modificacion (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "producto", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String idproducto = id_producto.getText().toString();
        String  nombre_p= nombre_Producto.getText().toString();
        String Tipo_p = Tipo_producto.getText().toString();
        String descripcion = descripcion_producto.getText().toString();
        String pre = precio.getText().toString();
        String catidad = cantidad_existente.getText().toString();
        String col = color.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("id_producto", idproducto);
        registro.put("nombre_producto", nombre_p);
        registro.put("Tipo_producto", Tipo_p);
        registro.put("descripcion_producto", descripcion);
        registro.put("precio", pre);
        registro.put("cantidad_existente", catidad);
        registro.put("color", col);

        int cant = bd.update("producto", registro, "id_producto=" + idproducto, null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "Se modificaron los datos del producto",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el producto",Toast.LENGTH_SHORT).show();
        }

    }

    public void limpia (View v){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


