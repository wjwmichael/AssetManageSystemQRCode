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

public class InsertpeopleActivity extends Activity{
	private Button insertbutton=null;
	private Button returnbutton=null;
	private TextView text,title;
	private EditText username,userphonenumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_1);
		inite();
	}
	
	private void inite (){
		text=(TextView)findViewById(R.id.result);
		title=(TextView)findViewById(R.id.title);
		title.setText("�������û�");
		username=(EditText)findViewById(R.id.name);
		userphonenumber=(EditText)findViewById(R.id.phonenumber);
		/**
		 * ���ñ༭ƽ̨��ť����
		 */
		insertbutton = (Button)findViewById(R.id.button1);
		insertbutton.setText("���");
		insertbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ownerdatabase database = new ownerdatabase(getApplicationContext());
				String name = username.getText().toString();
				String phonenumber=userphonenumber.getText().toString();
				database.insert_New_people(name, phonenumber);
				Toast.makeText(InsertpeopleActivity.this, "����û����ݳɹ���", 8000).show();
				text.setText( database.fetchdatafrompeople(name,phonenumber));							
				database.close();
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
		startActivity(new Intent(InsertpeopleActivity.this, goalpage) );
		finish();
	 }


}
