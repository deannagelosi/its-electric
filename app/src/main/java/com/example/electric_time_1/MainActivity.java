package com.example.electric_time_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // Declaring Widgets
    EditText editTextNumber;
    TextView time, distance, title, mode, miles;
    Button button;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate Widgets
        time = findViewById(R.id.time);
        editTextNumber = findViewById(R.id.editTextNumber);
        button = findViewById(R.id.button);
        spinner = findViewById(R.id.spinner);
        distance = findViewById(R.id.distance);
        // title = findViewById(R.id.title);
        // mode = findViewById(R.id.mode);
        // miles = findViewById(R.id.miles);

        editTextNumber.setTextColor(Color.WHITE);

        // Set up spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.modes, android.R.layout.simple_spinner_item);

        // Layout for the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        // Add an item selected listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

                String selectedMode = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, selectedMode, Toast.LENGTH_SHORT).show();

                CalculateFromSelection();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){

            };
        });

        // Add a click event for the button (execute the convert method when clicked)
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculateFromSelection();
            }
        });
    }

    private void CalculateFromSelection() {
        // Call the method to convert speed to time given distance

        // Get the current spinner value
        String selectedMode = spinner.getSelectedItem().toString();

        switch(selectedMode) {
            case "Walking":
                ConvertFromSpeedToTime(3.1, 30);
                break;
            case "Boosted Mini S Board":
                ConvertFromSpeedToTime(18, 7);
                break;
            case "Evolve Bamboo GTR 2in1":
                ConvertFromSpeedToTime(24, 31);
                break;
            case "Segway Ninebot S-PLUS":
                ConvertFromSpeedToTime(12, 22);
                break;
            case "Hovertrax Hoverboard":
                ConvertFromSpeedToTime(9, 6);
                break;
            case "OneWheel XR":
                ConvertFromSpeedToTime(19, 18);
                break;
            case "MotoTec Skateboard":
                ConvertFromSpeedToTime(22, 10);
                break;
            case "Segway Ninebot S":
                ConvertFromSpeedToTime(10, 13);
                break;
            case "Razor Scooter":
                ConvertFromSpeedToTime(18, 15);
                break;
            case "GeoBlade 500":
                ConvertFromSpeedToTime(15, 8);
                break;
            default:
                time.setText("Select a mode of transit");
        }
    }

    private void ConvertFromSpeedToTime(double modifier, int range) {
        // This method will convert distance to time

        String distanceEntered = editTextNumber.getText().toString();
        // Log.d("myTag", "Distance entered: " + distanceEntered);
        // Log.d("distanceEntered", distanceEntered);

        if (distanceEntered != null && !distanceEntered.isEmpty()) {
            // Log.d("if", "If statement passed ");
            // Convert string value to number
            double Distance = Double.parseDouble(distanceEntered);

            // Compare distance to the range
            if (range < Distance) {
                DecimalFormat df = new DecimalFormat("###.##");

                time.setTextColor(Color.parseColor("#20FC8F"));
                time.setText(df.format(range) + " miles is max range");

            } else {
                // Convert distance to time
                double Time = (Distance / modifier);
                int Hours = (int) Time;
                int Min = (int) ((Time - Hours) * 60);
                String output = "";

                if (Hours == 1 && Min > 0) {
                    output = Hours + " hour and " + Min + " minutes";
                } else if (Hours > 1) {
                    output = Hours + " hours and " + Min + " minutes";
                } else if (Hours == 1 && Min == 0) {
                    output = Hours + " hour";
                } else if (Hours > 1 && Min == 0) {
                    output = Hours + " hours";
                } else  {
                    output = Min + " minutes";
                }

                // Display the time
                time.setTextColor(distance.getCurrentTextColor());
                time.setText(output);
            }

        } else {
            // Log.d("else", "If statement not passed ");
            time.setText("Enter your distance");
        }

    }
}