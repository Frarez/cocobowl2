package cl.isisur.cocobowl2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Menu extends AppCompatActivity {

    private Button menuButton;
    private Button coordenadasButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuButton = findViewById(R.id.button4);
        coordenadasButton = findViewById(R.id.button5);


        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenu = new Intent(Menu.this, MainActivity.class);
                startActivity(intentMenu);
            }
        });

        coordenadasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCoordenadas = new Intent(Menu.this, coordenadas.class);
                startActivity(intentCoordenadas);
            }
        });
    }
}