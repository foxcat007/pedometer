package com.example.pedometer.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.pedometer.db.PedometerDB;
import com.example.pedometer.model.Step;
import com.example.pedometer.model.User;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class AutoSaveService extends Service {
	private PedometerDB pedometerDB;
	private Step step;
	private User user;
	private int userid;
	private String date;
	private Calendar calendar;
	private SimpleDateFormat sdf;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		init();

		
		return super.onStartCommand(intent, flags, startId);

	}

	@SuppressLint("SimpleDateFormat")
	private void init() {
		Log.i("info", "你好啊");
		calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		sdf = new SimpleDateFormat("yyyyMMdd");
		pedometerDB = PedometerDB.getInstance(this);
		
		step = new Step();
		step.setNumber(StepDetector.CURRENT_SETP);
		pedometerDB.updateStep(step);
		
		
		user = pedometerDB.loadUser(1);
		user.setToday_step(0);
		pedometerDB.updateUser(user);
		
		
		step = new Step();
		step.setDate(sdf.format(new Date()));
		step.setNumber(0);
		step.setUserId(1);
		pedometerDB.saveStep(step);

	}

}
