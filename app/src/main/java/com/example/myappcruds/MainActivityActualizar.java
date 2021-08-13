package com.example.myappcruds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.myappcruds.complementos.MetodoSW;
import com.example.myappcruds.complementos.ProductoVO;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivityActualizar extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    EditText editTextBuscar, editTextNombre, editTextDescripcion, editTextValor, editTextEstado;
    MetodoSW metodoSW = new MetodoSW();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_actualizar);

        editTextBuscar = findViewById(R.id.edtEstadoProducto);
        editTextNombre = findViewById(R.id.edtNombreProducto);
        editTextDescripcion = findViewById(R.id.edtDescripcionProducto);
        editTextValor = findViewById(R.id.edtValorProducto);
        editTextEstado = findViewById(R.id.edtEstadoProducto);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnActualizarProducto:
                this.actualizar();
                break;
            case R.id.imgBuscarActualizar:
                this.buscarID();
                break;
        }
    }

    private void actualizar() {
        if (!editTextBuscar.getText().toString().isEmpty() && !editTextNombre.getText().toString().isEmpty() &&
                !editTextDescripcion.getText().toString().isEmpty() && !editTextValor.getText().toString().isEmpty() &&
                !editTextEstado.getText().toString().isEmpty()) {

            metodoSW.actualizarSW(this, Integer.parseInt(editTextBuscar.getText().toString()),
                    editTextNombre.getText().toString(), editTextDescripcion.getText().toString(),
                    Integer.parseInt(editTextValor.getText().toString()), editTextEstado.getText().toString(),
                    this, this);

            editTextBuscar.setText("");
            editTextNombre.setText("...");
            editTextDescripcion.setText("...");
            editTextValor.setText("...");
            editTextEstado.setText("...");
            Toast.makeText(this, "Datos Actualizados Correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debe de llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void buscarID() {
        if (!editTextBuscar.getText().toString().isEmpty()) {
            metodoSW.buscarIDSW(this, Integer.parseInt(editTextBuscar.getText().toString()), this, this);
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
                editTextNombre.setText(productoVO.getNombre_Prducto());
                editTextDescripcion.setText(productoVO.getDescripcion_producto());
                editTextValor.setText(String.valueOf(productoVO.getValor_producto()));
                editTextEstado.setText(productoVO.getEstado_producto());
            } else {
                editTextNombre.setText("...");
                editTextDescripcion.setText("...");
                editTextValor.setText("...");
                editTextEstado.setText("...");
                Toast.makeText(this, "Datos no Encontrados", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error referente a B " + e, Toast.LENGTH_LONG).show();
            System.err.println("B----- " + e.getCause() + " --- " + e.getMessage());
        }


    }

        @Override
        public void onResponse(JSONObject response) { this.resultadoBusqueda(response);
        }

        @Override
        public void onErrorResponse(VolleyError error) {

        }
}