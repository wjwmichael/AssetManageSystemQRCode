package com.example.testdata;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Updatepeople extends Activity{
	private Button searchbutton=null;
	private Button updatebutton=null;
	private Button returnbutton=null;
	private TextView text,title;
	private EditText userid,username,userphonenumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_2);
		inite();
	}
	/**
	 * 初始化组件
	 */
	private void inite (){
		text=(TextView)findViewById(R.id.result);
		title=(TextView)findViewById(R.id.title);
		title.setText("更改用户数据");
		userid=(EditText)findViewById(R.id.id);
		username=(EditText)findViewById(R.id.editText1);
		userphonenumber=(EditText)findViewById(R.id.editText2);
		
		searchbutton = (Button)findViewById(R.id.button1);
		searchbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ownerdatabase database = new ownerdatabase(getApplicationContext());
				try{
				     int id = Integer.parseInt(userid.getText().toString());      
				     text.setText( database.fetchdatafrompeople1(id));			
				     database.close();
				}catch(Exception e)
				{}  
			}
		});
		
		returnbutton = (Button)findViewById(R.id.button2);
		returnbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotopage(EditActivity.class);
			}
		});
		
		updatebutton = (Button)findViewById(R.id.button3);
		updatebutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ownerdatabase database = new ownerdatabase(getApplicationContext());
				try{
				     int id = Integer.parseInt(userid.getText().toString());
				           
				String name=username.getText().toString();
				String phonenumber=userphonenumber.getText().toString();
				database.update_people(id, name, phonenumber);
				Toast.makeText(Updatepeople.this, "清除用户数据成功！", 8000).show();
				text.setText( database.fetchdatafrompeople1(id));	
				database.close();
				}catch(Exception e)
				{}  
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
	 * 跳到指定页
	 * @param goalpage
	 */
	private void gotopage(Class<?> goalpage){
		startActivity(new Intent(Updatepeople.this, goalpage) );
		finish();
	 }


}
