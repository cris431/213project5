package com.example.a213project5;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
This class contains the methods that organize the Main Activity user inputs
@author Emily Nelson, Cristofer Gomez-Martinez
*/
public class MainActivity extends AppCompatActivity {
    private ImageView orderDeluxeBtn;
    private ImageView orderHawaiianBtn;
    private ImageView orderPepperoniBtn;
    private ImageView reviewOrderBtn;
    private ImageView viewStoreOrdersBtn;
    private Pizza pizza;
    private Order order;
    private StoreOrders storeOrders;

    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                /**
                Receives a pizza as a result
                @param result ActivityResult
                @author Emily Nelson
                */
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 78) {
                        Intent intent = result.getData();

                        if (intent != null) {
                            pizza = (Pizza) intent.getSerializableExtra("pizzaAdded");
                            order.add(pizza);
                        }

                    }
                }
            }
    );

    /**
    Initialiazes the data needed to keep track of the pizza orders
    Defines the layout for MainActivty's user interface
    @param savedInstanceState Bundle
    @author Emily Nelson
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderDeluxeBtn = (ImageView) findViewById(R.id.orderDeluxeBtn);
        orderHawaiianBtn = (ImageView) findViewById(R.id.orderHawaiianBtn);
        orderPepperoniBtn = (ImageView) findViewById(R.id.orderPepperoniBtn);
        reviewOrderBtn = (ImageView) findViewById(R.id.reviewOrderBtn);
        viewStoreOrdersBtn = (ImageView) findViewById(R.id.viewStoreOrdersBtn);

        order = new Order();
        Pizza[] newOrders = new Pizza[10];
        order.setPizzas(newOrders);
        StoreOrders storeOrders = new StoreOrders();

        orderDeluxeBtn.setOnClickListener(new View.OnClickListener() {
            /**
            Calls for the launch of Customize Deluxe Activity when orderDeluxeBtn view has been clicked
            @param v View
            @author Emily Nelson
            */
            @Override
            public void onClick(View v) {
                openCustomizeDeluxeActivity();
            }
        });

        orderHawaiianBtn.setOnClickListener(new View.OnClickListener() {
            /**
            Calls for the launch of Customize Hawaiian Activity when orderHawaiianBtn view has been clicked
            @param v View
            @author Emily Nelson
            */
            @Override
            public void onClick(View v) {
                openCustomizeHawaiianActivity();
            }
        });

        orderPepperoniBtn.setOnClickListener(new View.OnClickListener() {
            /**
            Calls for the launch of Customize Pepperoni Activity when orderPepperoniBtn view has been clicked
            @param v View
            @author Emily Nelson
            */
            @Override
            public void onClick(View v) {
                openCustomizePepperoniActivity();
            }
        });

        reviewOrderBtn.setOnClickListener(new View.OnClickListener() {
            /**
            Calls for the launch of Review Order Activity when reviewOrderBtn view has been clicked
            @param v View
            @author Emily Nelson
            */
            @Override
            public void onClick(View v) {
                openReviewOrderActivity();
            }
        });
    }

    /**
    Launches Customize Deluxe Activity
    @author Emily Nelson
    */
    public void openCustomizeDeluxeActivity() {
        Intent intent = new Intent(MainActivity.this, CustomizeDeluxeActivity.class);
        intent.putExtra("order", order);
        activityLauncher.launch(intent);
    }
    
    /**
    Launches Customize Hawaiian Activity
    @author Emily Nelson
    */
    public void openCustomizeHawaiianActivity() {
        Intent intent = new Intent(MainActivity.this, CustomizeHawaiianActivity.class);
        intent.putExtra("order", order);
        activityLauncher.launch(intent);
    }
    
    /**
    Launches Customize Pepperoni Activity
    @author Emily Nelson
    */
    public void openCustomizePepperoniActivity() {
        Intent intent = new Intent(MainActivity.this, CustomizePepperoniActivity.class);
        intent.putExtra("order", order);
        activityLauncher.launch(intent);
    }

    /**
    Launches Review Order Activity
    @author Emily Nelson
    */
    public void openReviewOrderActivity() {
        Intent intent = new Intent(MainActivity.this, ReviewOrderActivity.class);
        intent.putExtra("order", order);
        activityLauncher.launch(intent);
    }

}
