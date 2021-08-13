package com.example.myappcruds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.myappcruds.complementos.MetodoSW;
import com.example.myappcruds.complementos.ProductoVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivityMostrar extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    ListView listView;
    ArrayList<String> listaDatos;
    ArrayList<ProductoVO> listaClienteVO;
    MetodoSW metodoSW = new MetodoSW();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mostrar);

        listView = findViewById(R.id.lvLitasProducto);
        metodoSW.consultarSW(this, this, this);
    }
    private void resultadoConsultaCompleta(JSONObject response){
        //ClienteVO clienteVO;
        JSONArray jsonArray = response.optJSONArray("tbl_producto");
        listaClienteVO = new ArrayList<>();
        try {
            for(int i=0;i < jsonArray.length(); i++){
                ProductoVO  productoVO = new ProductoVO();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                productoVO.setId(jsonObject.optInt("id_Producto"));
                productoVO.setNombre_Prducto(jsonObject.optString("nombre_producto"));
                productoVO.setDescripcion_producto(jsonObject.optString("descripcion_producto"));
                productoVO.setValor_producto(jsonObject.optInt("valor_producto"));
                productoVO.setEstado_producto(jsonObject.optString("estado_producto"));

                listaClienteVO.add(productoVO);
            }

            listaDatos = new ArrayList<>();
            for(int i=0;i < listaClienteVO.size();i++){
                if(listaClienteVO.get(i).getId() != 0) {
                    listaDatos.add(listaClienteVO.get(i).getId() + ". " + listaClienteVO.get(i).getNombre_Prducto());
                }
                else {
                    Toast.makeText(this, "Lista Vacia", Toast.LENGTH_SHORT).show();
                }
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDatos);
            listView.setAdapter(arrayAdapter);
        }
        catch (Exception e){
            Toast.makeText(this, "Error referente a C", Toast.LENGTH_LONG).show();
            System.err.println("C----- "+e.getCause()+" --- "+e.getMessage());
        }
    }
    @Override
    public void onResponse(JSONObject response) {
        this.resultadoConsultaCompleta(response);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error respuesta a C "+error, Toast.LENGTH_LONG).show();
        System.err.println("C***** "+error);
    }
}


