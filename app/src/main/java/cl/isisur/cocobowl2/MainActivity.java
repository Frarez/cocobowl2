package cl.isisur.cocobowl2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6;
    private Button button;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("cocobowl2");

        // Inicializar vistas
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        checkBox1 = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        button = findViewById(R.id.button);

        // Configurar el botón de guardar
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });
    }

    private void guardarDatos() {
        // Capturar selección de RadioButtons
        int selectedRadioId = radioGroup.getCheckedRadioButtonId();
        String tipoPedido = "";
        if (selectedRadioId == radioButton1.getId()) {
            tipoPedido = "Para Llevar";
        } else if (selectedRadioId == radioButton2.getId()) {
            tipoPedido = "Entrega";
        }

        // Capturar selección de CheckBoxes
        StringBuilder opcionesBowl = new StringBuilder();
        if (checkBox1.isChecked()) {
            opcionesBowl.append(checkBox1.getText().toString()).append(", ");
        }
        if (checkBox2.isChecked()) {
            opcionesBowl.append(checkBox2.getText().toString()).append(", ");
        }
        if (checkBox3.isChecked()) {
            opcionesBowl.append(checkBox3.getText().toString()).append(", ");
        }
        if (checkBox4.isChecked()) {
            opcionesBowl.append(checkBox4.getText().toString()).append(", ");
        }
        if (checkBox5.isChecked()) {
            opcionesBowl.append(checkBox5.getText().toString()).append(", ");
        }
        if (checkBox6.isChecked()) {
            opcionesBowl.append(checkBox6.getText().toString()).append(", ");
        }

        // Eliminar la última coma y espacio
        if (opcionesBowl.length() > 0) {
            opcionesBowl.setLength(opcionesBowl.length() - 2);
        }

        // Crear un objeto para guardar en Firebase
        Pedido pedido = new Pedido(tipoPedido, opcionesBowl.toString());
        System.out.print(pedido);
        // Guardar en Firebase
        databaseReference.push().setValue(pedido);

        // Mostrar mensaje de confirmación
        Toast.makeText(this, "Pedido guardado", Toast.LENGTH_SHORT).show();
    }

    // Clase para representar el pedido
    public static class Pedido {
        public String tipoPedido;
        public String opcionesBowl;

        public Pedido() {
            // Constructor vacío necesario para Firebase
        }

        public Pedido(String tipoPedido, String opcionesBowl) {
            this.tipoPedido = tipoPedido;
            this.opcionesBowl = opcionesBowl;
        }
    }
}
