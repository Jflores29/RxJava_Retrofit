package com.javier.welcomeui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity_" ;
    private EditText editText;
    List<ResultAPI> mylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.main_et);


    }

    public void giveMeSecondAct(View view) {
        rx.Observable<List<ResultAPI>> resultObservable = RetrofitHelper.Factory.create(editText.getText().toString());

        resultObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ResultAPI>>() {
                    @Override
                    public void onCompleted() {
                        //This call back is called whenever the observable
                        //has emitted all of its items
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                        intent.putExtra("key",(Serializable) mylist);
                        startActivity(intent);
                        Log.d(TAG, "onCompleted: Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError:  Error Occured");
                    }

                    @Override
                    public void onNext(List<ResultAPI> resultAPIs) {
                        //This callback is called per item emitted by the observable
                        mylist = resultAPIs;

//                            Log.d(TAG, "onNext: "+ result.toString());


                    }
                });

    }
}
