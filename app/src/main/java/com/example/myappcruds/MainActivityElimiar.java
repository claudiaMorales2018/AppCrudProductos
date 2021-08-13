package com.example.myappcruds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.myappcruds.complementos.MetodoSW;
import com.example.myappcruds.complementos.ProductoVO;

import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivityElimiar  extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    EditText editText;
    TextView textViewNombre, textViewDescripcion, textViewValor, textViewEstado;
    MetodoSW metodoSW = new MetodoSW();


    // @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_elimiar2);

        editText = findViewById(R.id.edtBuscarProductoEliminar);
        textViewNombre = findViewById(R.id.txtNombreProductoEliminar);
        textViewDescripcion = findViewById(R.id.txtDescripcionProductoEliminar);
        textViewValor = findViewById(R.id.txtValorProductoEliminar);
        textViewEstado = findViewById(R.id.txtEstadoProductoEliminar);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBuscarEliminar:
                this.buscarID();
                break;
            case R.id.btnEliminarProducto:
                this.eliminar();
                break;
        }
    }

    private void eliminar() {
        if (!editText.getText().toString().isEmpty()) {
            metodoSW.eliminarSW(this, Integer.parseInt(editText.getText().toString()), this, this);
            editText.setText("");
            textViewNombre.setText("...");
            textViewDescripcion.setText("...");
            textViewValor.setText("...");
            textViewEstado.setText("...");
            Toast.makeText(this, "Datos Eliminados Correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debe de llenar el campo", Toast.LENGTH_SHORT).show();
        }
    }

    private void buscarID() {
        if (!editText.getText().toString().isEmpty()) {
            metodoSW.buscarIDSW(this, Integer.parseInt(editText.getText().toString()), this, this);
        } else {
            Toast.makeText(this, "Debe de llenar el campo", Toast.LENGTH_SHORT).show();
        }
    }

    private void resultadoBusqueda(JSONObject response) {
        ProductoVO productoVO = new ProductoVO();
        JSONArray jsonArray = response.optJSONArray("tbl_producto");
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            productoVO.setNombre_Prducto(jsonObject.optString("nombre_producto"));
            productoVO.setDescripcion_producto(jsonObject.optString("descripcion_producto"));
            productoVO.setValor_producto(jsonObject.optInt("valor_producto"));
            productoVO.setEstado_producto(jsonObject.optString("estado_producto"));
            String dato = productoVO.getNombre_Prducto();
            if (!dato.equals("...")) {
                textViewNombre.setText(productoVO.getEstado_producto());
                textViewDescripcion.setText(productoVO.getDescripcion_producto());
                textViewValor.setText(String.valueOf(productoVO.getValor_producto()));
                textViewEstado.setText(productoVO.getEstado_producto());
            } else {
                textViewNombre.setText("...");
                textViewDescripcion.setText("...");
                textViewValor.setText("...");
                textViewEstado.setText("...");
                Toast.makeText(this, "Datos no Encontrados", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error referente a B " + e, Toast.LENGTH_LONG).show();
            System.err.println("B----- " + e.getCause() + " --- " + e.getMessage());
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        this.resultadoBusqueda(response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error respuesta a E " + error, Toast.LENGTH_LONG).show();
        System.err.println("E***** " + error);

    }
}