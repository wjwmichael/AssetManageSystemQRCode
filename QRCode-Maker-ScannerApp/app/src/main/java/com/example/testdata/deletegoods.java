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

public class deletegoods extends Activity{
	private Button searchbutton=null;
	private Button deletebutton=null;
	private Button returnbutton=null;
	private TextView text,title,text1;
	private EditText goodsid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_1);
		inite();
	}
	/**
	 * ��ʼ�����
	 */
	private void inite (){
		text=(TextView)findViewById(R.id.result);
		text1=(TextView)findViewById(R.id.textView1);
		title=(TextView)findViewById(R.id.title);
		title.setText("ɾ����Ʒ����");
		text1.setText("��ƷId:");
		goodsid=(EditText)findViewById(R.id.id);
		
		
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
		
		deletebutton = (Button)findViewById(R.id.button3);
		deletebutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ownerdatabase database = new ownerdatabase(getApplicationContext());
				try{
				     int id = Integer.parseInt(goodsid.getText().toString());
				database.DeletegoodsData(id);
				Toast.makeText(deletegoods.this, "�����Ʒ���ݳɹ���", 8000).show();
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
	 * ����ָ��ҳ
	 * @param goalpage
	 */
	private void gotopage(Class<?> goalpage){
		startActivity(new Intent(deletegoods.this, goalpage) );
		finish();
	 }


}

