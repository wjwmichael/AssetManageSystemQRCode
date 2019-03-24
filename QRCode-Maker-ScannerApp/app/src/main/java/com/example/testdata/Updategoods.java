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

public class Updategoods extends Activity{
	private Button searchbutton=null;
	private Button updatebutton=null;
	private Button returnbutton=null;
	private TextView text,title,text1,text2;
	private EditText goodsid,goodsname;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_3);
		inite();
	}
	/**
	 * 初始化组件
	 */
	private void inite (){
		text=(TextView)findViewById(R.id.result);
		text1=(TextView)findViewById(R.id.textView1);
		text2=(TextView)findViewById(R.id.textView2);
		title=(TextView)findViewById(R.id.title);
		title.setText("更新物品数据");
		text1.setText("物品Id");
		text2.setText("新物品名");
		goodsid=(EditText)findViewById(R.id.id);
		goodsname=(EditText)findViewById(R.id.editText1);
		
		searchbutton = (Button)findViewById(R.id.button1);
		searchbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ownerdatabase database = new ownerdatabase(getApplicationContext());
				try{
				     int id = Integer.parseInt(goodsid.getText().toString());      
				     text.setText( database.fetchdatafromgoods(id));			
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
				     int id = Integer.parseInt(goodsid.getText().toString());
				           
				String name=goodsname.getText().toString();
				database.update_goods(id, name);
				Toast.makeText(Updategoods.this, "更新物品数据成功！", 8000).show();
				text.setText( database.fetchdatafromgoods(id));	
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
		startActivity(new Intent(Updategoods.this, goalpage) );
		finish();
	 }


}
