package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public Spinner subnetMaskSpinner, subnetBitsSpinner, maxSubnetsSpinner, maskBitsSpinner, hostPerSubnetSpinner;
    public List<String> maskList, subnetBitsList, maxSubnetList, maskBitsList, hostList;
    public Calculator calculator = new Calculator("C","128.1.1.1",2);
    public ArrayAdapter<String> subnetBitsAdapter, subnetMaskAdapter, maxSubnetsAdapter, maskBitsAdapter, hostPerSubnetAdapter;
    public TextView fOctetRange, hexIP, wildCard, broadcast, subnetBitmap, hostAddressRange, subnetID;
    public String firstOctetV, hexIPV, wildCardV, broadcastV, subnetBitmapV, hostAddressRangeV, subnetIDV, ipAdressV;
    public EditText ipAdress;
    public RadioButton ClassA, ClassB, ClassC;
    public RadioGroup  classRadioGroup;
    int index;
    private boolean ended = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDec();

        calculator = new Calculator("C","",0);
        spinnerDeclaration();
        setValues();

        ended = true;
        ipAdress.getOnFocusChangeListener();
        subnetMaskSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        changeSubnet(pos);
                        Log.d("PILNE","X");
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
        subnetBitsSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        changeSubnet(pos);
                        Log.d("PILNE","D");
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
        maxSubnetsSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        changeSubnet(pos);
                        Log.d("PILNE","T");
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
        maskBitsSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        changeSubnet(pos);
                        Log.d("PILNE","F");
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
        hostPerSubnetSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        changeSubnet(pos);
                        Log.d("PILNE","H");
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
        ipAdress.setOnFocusChangeListener(
                (view, b) -> calculate()
        );
        classRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {

                   changeSubnet(0);
                   calculate();
                spinnerDeclaration();
            }
        });
    }
    private void calculate(){
        ended = false;
        String klasa = ClassA.isChecked() ? "A" : (ClassB.isChecked() ? "B" : "C");
        calculator.setIPAddress(klasa,ipAdress.getText().toString(),index);
        setValues();
        ended = true;
        Log.d("PILNE","calculate");
    }
    private void setValues(){
        listUpdate();
        ipAdress.setText(calculator.getValue(CalculatorValues.IPAddress));
        fOctetRange.setText(calculator.getValue(CalculatorValues.FirstOctetRange));
        hexIP.setText(calculator.getValue(CalculatorValues.HexIPAddress));
        wildCard.setText(calculator.getValue(CalculatorValues.WildCardMask));
        broadcast.setText( calculator.getValue(CalculatorValues.BroadcastAddress));
        subnetBitmap.setText(calculator.getValue(CalculatorValues.SubnetBitmap));
        hostAddressRange.setText(calculator.getValue(CalculatorValues.HostAddressRange));
        subnetID.setText(calculator.getValue(CalculatorValues.SubnetID));
        Log.d("PILNE","setValues");

    }

    public void onDO(View v){
        //Spinner idView = findViewById(v.getId());
        //changeSubnet(5);
        //Log.d("PILNE",String.valueOf(idView.getSelectedItemPosition()));
    }
    private void changeSubnet(int i) {

        //TODO if ended
        if(!ended) return;
            index = i;
        listUpdate();

        calculator.setSubnetByIndex(index);
        subnetBitsSpinner.setSelection(index,true);
//        subnetMaskSpinner.setSelection(index);
        maskBitsSpinner.setSelection(index,true);
        subnetMaskSpinner.setSelection(index,true);
        maxSubnetsSpinner.setSelection(index,true);
        hostPerSubnetSpinner.setSelection(index,true);
        wildCard.setText(calculator.getValue(CalculatorValues.WildCardMask));
        hostAddressRange.setText(calculator.getValue(CalculatorValues.HostAddressRange));
        broadcast.setText(calculator.getValue(CalculatorValues.BroadcastAddress));
        subnetBitmap.setText(calculator.getValue(CalculatorValues.SubnetBitmap));
        subnetID.setText(calculator.getValue(CalculatorValues.SubnetID));

        /*
        MaximumSubnets.getSelectionModel().select(index);
        HostsPerSubnet.getSelectionModel().select(index);
        WildcardMask.setText(calculator.getValue(CalculatorValues.WildCardMask));
        HostAddressRange.setText(calculator.getValue(CalculatorValues.HostAddressRange));
        BroadcastAddress.setText(calculator.getValue(CalculatorValues.BroadcastAddress));
        SubnetBitmap.setText(calculator.getValue(CalculatorValues.SubnetBitmap));
        SubnetID.setText(calculator.getValue(CalculatorValues.SubnetID));*/
    }
    protected void textViewDec(){
        ClassA = findViewById(R.id.classA);
        ClassB = findViewById(R.id.classB);
        ClassC = findViewById(R.id.classC);
        classRadioGroup = findViewById(R.id.radioGroup);
        fOctetRange = findViewById(R.id.textView15);
        hexIP = findViewById(R.id.textView7);
        wildCard = findViewById(R.id.textView8);
        broadcast = findViewById(R.id.broadcastAddress);
        subnetBitmap = findViewById(R.id.subnetBitmap);
        hostAddressRange = findViewById(R.id.hostAddressRange);
        subnetID = findViewById(R.id.subnetID);
        firstOctetV = calculator.getValue(CalculatorValues.FirstOctetRange);
        hexIPV = calculator.getValue(CalculatorValues.HexIPAddress);
        wildCardV = calculator.getValue(CalculatorValues.WildCardMask);
        subnetBitmapV= calculator.getValue(CalculatorValues.SubnetBitmap);
        broadcastV = calculator.getValue(CalculatorValues.BroadcastAddress);
        hostAddressRangeV = calculator.getValue(CalculatorValues.HostAddressRange);
        subnetIDV = calculator.getValue(CalculatorValues.SubnetID);
        ipAdress = findViewById(R.id.editTextTextPersonName);
        fOctetRange.setText(firstOctetV);
        hexIP.setText(hexIPV);
        wildCard.setText(wildCardV);
        broadcast.setText(broadcastV);
        subnetBitmap.setText(subnetBitmapV);
        hostAddressRange.setText(hostAddressRangeV);
        subnetID.setText(subnetIDV);

    }
    protected void listUpdate(){
        maskList = calculator.getSubnetMaskList();
        subnetBitsList = calculator.getSubnetBitsList();
        maxSubnetList = calculator.getMaximumSubnetsList();
        maskBitsList = calculator.getMaskBitsList();
        hostList = calculator.getHostsPerSubnetList();


    }
    protected  void spinnerDeclaration(){
        listUpdate();
        subnetMaskSpinner = findViewById(R.id.spinnerMask);
        subnetBitsSpinner = findViewById(R.id.spinnerSubBits);
        maxSubnetsSpinner = findViewById(R.id.spinnerMaxSubnet);
        maskBitsSpinner = findViewById(R.id.spinnerMaskBits);
        hostPerSubnetSpinner = findViewById(R.id.spinnerHostSubnet);
        subnetMaskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, maskList);
        subnetMaskSpinner.setAdapter(subnetMaskAdapter);

        subnetBitsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subnetBitsList);
        subnetBitsSpinner.setAdapter(subnetBitsAdapter);

        maxSubnetsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, maxSubnetList);
        maxSubnetsSpinner.setAdapter(maxSubnetsAdapter);

        maskBitsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, maskBitsList);
        maskBitsSpinner.setAdapter(maskBitsAdapter);

        hostPerSubnetAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hostList);
        hostPerSubnetSpinner.setAdapter(hostPerSubnetAdapter);




    }
}