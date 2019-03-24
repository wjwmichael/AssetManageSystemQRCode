package com.example.testdata;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends Activity{
	private Button searchbutton=null;
	private Button returnbutton=null;
	private TextView text,title;
	private EditText username,userphonenumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_1);
		inite();
	}
	/**
	 * 初始化组件
	 */
	private void inite (){
		text=(TextView)findViewById(R.id.result);
		title=(TextView)findViewById(R.id.title);
		title.setText("查找已存在用户");
		username=(EditText)findViewById(R.id.name);
		userphonenumber=(EditText)findViewById(R.id.phonenumber);
		/**
		 * 设置编辑平台按钮属性
		 */
		searchbutton = (Button)findViewById(R.id.button1);
		searchbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ownerdatabase database = new ownerdatabase(getApplicationContext());
				String name = username.getText().toString();
				String phonenumber=userphonenumber.getText().toString();
				text.setText( database.fetchdatafrompeople(name,phonenumber));							
				database.close();
			}
		});
		/**
		 * 设置window phone 平台按钮属性
		 */
		returnbutton = (Button)findViewById(R.id.button2);
		returnbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotopage(EditActivity.class);
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
		startActivity(new Intent(SearchActivity.this, goalpage) );
		finish();
	 }


}
