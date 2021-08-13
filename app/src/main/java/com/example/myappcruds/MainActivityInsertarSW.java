package com.example.myappcruds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.myappcruds.complementos.MetodoSW;

import org.json.JSONObject;

public class MainActivityInsertarSW extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    EditText editTextNombre_producto, editTextDescripcion_producto,editTextValor_producto,editTextEstado_producto;
    MetodoSW metodoSW = new MetodoSW();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_insertar_sw);


        editTextNombre_producto=findViewById(R.id.edtNombreProducto);
        editTextDescripcion_producto=findViewById(R.id.edtDescripcionProducto);
        editTextValor_producto=findViewById(R.id.edtValorProducto);
        editTextEstado_producto=findViewById(R.id.edtEstadoProducto);

    }

    public void OnClick(View view) {
        this.insertar();
    }
    private void insertar() {
        if (!editTextNombre_producto.getText().toString().isEmpty() && !editTextDescripcion_producto.getText().toString().isEmpty() &&
                !editTextValor_producto.getText().toString().isEmpty() && !editTextEstado_producto.getText().toString().isEmpty()) {

            //utilzamos el metodo respectivo de la clase MetodosSW
            metodoSW.insertarSW(this, editTextNombre_producto.getText().toString(), editTextDescripcion_producto.getText().toString(),
                    Integer.parseInt(editTextValor_producto.getText().toString()), editTextEstado_producto.getText().toString(),
                    this, this);

            editTextNombre_producto.setText("");
            editTextDescripcion_producto.setText("");
            editTextValor_producto.setText("");
            editTextEstado_producto.setText("");
        } else {
            Toast.makeText(this, "Debe de llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error referente a Insertar "+error, Toast.LENGTH_SHORT).show();
        System.err.println("I***** "+ error);
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "Datos Insertados Correctamente", Toast.LENGTH_SHORT).show();
    }
}