package com.example.hw2_elliptic_curve;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int G;
    private int prime;

    private Button BT_create;
    private Button BT_calculate_public;
    private Button BT_calculate_key;
    private Button BT_auto_bt;

    private TextView TV_A_pointG;
    private TextView TV_A_prime;
    private TextView TV_A_key;
    private TextView TV_A_publicKey;
    private TextView TV_A_matchPoint;

    private int A_key;
    private int A_publicKey;

    private TextView TV_B_pointG;
    private TextView TV_B_prime;
    private TextView TV_B_key;
    private TextView TV_B_publicKey;
    private TextView TV_B_matchPoint;

    private int B_key;
    private int B_publicKey;

    private int[] primes = {11, 13, 17, 19, 23, 29, 31, 37, 41};
    private boolean[] state = {false,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BT_create = findViewById(R.id.create_bt);
        BT_calculate_public = findViewById(R.id.calculate_public_bt);
        BT_calculate_key = findViewById(R.id.calculate_key_bt);
        BT_auto_bt =findViewById(R.id.auto_bt);

        TV_A_pointG = findViewById(R.id.A_pointG_TV);
        TV_A_prime = findViewById(R.id.A_prime_TV);
        TV_A_key = findViewById(R.id.A_Key_TV);
        TV_A_publicKey = findViewById(R.id.A_Public_TV);
        TV_A_matchPoint = findViewById(R.id.A_matchP_TV);

        TV_B_pointG = findViewById(R.id.B_pointG_TV);
        TV_B_prime = findViewById(R.id.B_prime_TV);
        TV_B_key = findViewById(R.id.B_Key_TV);
        TV_B_publicKey = findViewById(R.id.B_Public_TV);
        TV_B_matchPoint = findViewById(R.id.B_matchP_TV);

        BT_auto_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();
                calculate_public();
                calculate_key();
            }
        });
        BT_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();
            }
        });
        BT_calculate_public.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state[0])
                    calculate_public();
                else {
                    Toast.makeText(MainActivity.this,
                            "Must create details", Toast.LENGTH_SHORT).show();
                }
            }
        });
        BT_calculate_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state[1])
                    calculate_key();
                else{
                    Toast.makeText(MainActivity.this,
                            "Must create public key", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void create(){
        clearTV();
        state[0]=true;
        G = (int) (Math.random() * 100);
        prime = primes[(int) (Math.random() * primes.length)];
        A_key = (int) (Math.random() * 10);
        B_key = (int) (Math.random() * 10);

        TV_A_pointG.setText("Point G is: " + G);
        TV_A_prime.setText("Prime K is: " + prime);
        TV_A_key.setText("Key a is: " + A_key);

        TV_B_pointG.setText("Point G is: " + G);
        TV_B_prime.setText("Prime K is: " + prime);
        TV_B_key.setText("Key a is: " + B_key);
    }
    private void calculate_public(){
        state[1]=true;

        A_publicKey = (int) (Math.pow(G, A_key) % prime);
        B_publicKey = (int) (Math.pow(G, B_key) % prime);

        TV_A_publicKey.setText("Public Key is: " + A_publicKey+ "\n"+
                "\nThe math algo:\n(G ^ A_key) mod prime\n"+
                "("+G+"^"+A_key+") mode "+prime);
        TV_B_publicKey.setText("Public Key is: " + B_publicKey+ "\n" +
                "\nThe math algo:\n(G ^ B_key) mod prime\n"+
                "("+G+"^"+B_key+") mode "+prime);
    }
    private void calculate_key(){
        state[2]=true;
        TV_A_matchPoint.setText("secret key is: " + (int) (Math.pow(B_publicKey, A_key) % prime)+"\n" +
                "\nThe math algo:\n(B_publicKey ^ A_key) mod prime\n" +
                "("+B_publicKey+"^"+A_key+") mod "+prime);
        TV_B_matchPoint.setText("secret key is: " + (int) (Math.pow(A_publicKey, B_key) % prime)+"\n" +
                "\nThe math algo:\n(A_publicKey ^ B_key) mod prime\n" +
                "("+A_publicKey+"^"+B_key+") mod "+prime);
    }
    private void clearTV (){
        TV_A_pointG.setText("Point G not exist");
        TV_A_prime.setText("Prime P not exist");
        TV_A_key.setText("Key a not exist");

        TV_B_pointG.setText("Point G not exist");
        TV_B_prime.setText("Prime P not exist");
        TV_B_key.setText("Key a not exist");

        TV_A_publicKey.setText("Public Key not exist");
        TV_B_publicKey.setText("Public Key not exist");

        TV_A_matchPoint.setText("secret key not exist");
        TV_B_matchPoint.setText("secret key not exist");

        state[0]=false;
        state[1]=false;
        state[2]=false;
    }
}