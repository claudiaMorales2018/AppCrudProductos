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

public class MainActivityBuscar extends AppCompatActivity  implements Response.Listener<JSONObject>, Response.ErrorListener{

    EditText editText;
    TextView textViewNombre, textViewDescripcion, textViewValor, textViewEstado;
    MetodoSW metodosSW = new MetodoSW();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_buscar);

        editText = findViewById(R.id.edtBuscarProducto);
        textViewNombre = findViewById(R.id.txtNombreProducto);
        textViewDescripcion = findViewById(R.id.txtDescripcionProducto);
        textViewValor = findViewById(R.id.txtValorProducto);
        textViewEstado = findViewById(R.id.txtDescripcionProducto);

    }

    public void onClick(View view) {
        this.buscarID();
    }

    private void buscarID(){
        if(!editText.getText().toString().isEmpty()){
            metodosSW.buscarIDSW(this,Integer.parseInt(editText.getText().toString()), this, this);
        }
        else {
            Toast.makeText(this, "Debe de llenar el campo", Toast.LENGTH_LONG).show();
        }
    }
    private void resultadoConsulta(JSONObject response){
        ProductoVO productoVo = new ProductoVO();
        JSONArray jsonArray = response.optJSONArray("tbl_producto");
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            productoVo.setNombre_Prducto(jsonObject.optString("nombre_producto"));
            productoVo.setDescripcion_producto(jsonObject.optString("descripcion_producto"));
            productoVo.setValor_producto(jsonObject.optInt("valor_producto"));
            productoVo.setEstado_producto(jsonObject.optString("estado_producto"));

            String dato = productoVo.getNombre_Prducto();
            if(!dato.equals("...")) {
                textViewNombre.setText(productoVo.getEstado_producto());
                textViewDescripcion.setText(productoVo.getDescripcion_producto());
                textViewValor.setText(String.valueOf(productoVo.getValor_producto()));
                textViewEstado.setText(productoVo.getEstado_producto());
            }
            else{
                textViewNombre.setText("...");
                textViewDescripcion.setText("...");
                textViewValor.setText("...");
                textViewEstado.setText("...");
                Toast.makeText(this, "Datos no Encontrados", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, "Error referente a B "+e, Toast.LENGTH_LONG).show();
            System.err.println("B----- "+e.getCause()+" --- "+e.getMessage());
        }
    }
    @Override
    public void onResponse(JSONObject response) {
        this.resultadoConsulta(response);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error respuesta a B "+error, Toast.LENGTH_LONG).show();
        System.err.println("B***** "+error);
    }

}