package com.example.testdata;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button editbutton=null;
	private Button scanbutton=null;
	private TextView text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inite();
	}
	/**
	 * ��ʼ�����
	 */
	private void inite (){
		text=(TextView)findViewById(R.id.textView);
		text.setText("welcome");
		/**
		 * ���ñ༭ƽ̨��ť����
		 */
		editbutton = (Button)findViewById(R.id.button1);
		editbutton.setText("���ݲ���");
		editbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotopage(EditActivity.class);
			}
		});
		/**
		 * ����window phone ƽ̨��ť����
		 */
		scanbutton = (Button)findViewById(R.id.button2);
		scanbutton.setText("ɨ���ά�루��û���ϣ�");
		scanbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "�����/(��o��)/~~�����", 8000).show();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	 * ����ָ��ҳ
	 * @param goalpage
	 */
	private void gotopage(Class<?> goalpage){
		startActivity(new Intent(MainActivity.this, goalpage) );
		finish();
	 }

}
