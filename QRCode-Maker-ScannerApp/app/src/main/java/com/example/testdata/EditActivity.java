package com.example.testdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends Activity{
	private Button newuserbutton=null;
	private Button Searchbutton=null;
	private Button Updateuserbutton=null;
	private Button Deleteuserbutton=null;
	private Button Insertgoodsbutton=null;
	private Button Updategoodsbutton=null;
	private Button Deletegoodsbutton=null;
	private Button Deleteallgoodsbutton=null;
	private Button Deletealldatabutton=null;
	private Button returnbutton=null;
	private Button createbutton=null;
	private TextView text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.operation);
		inite();
	}
	/**
	 * ��ʼ�����
	 */
	private void inite (){
		text=(TextView)findViewById(R.id.title);
		text.setText("����ѡ��");
		
		newuserbutton = (Button)findViewById(R.id.buttona);
		newuserbutton.setText("�����û�");
		newuserbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotopage(InsertpeopleActivity.class);
			}
		});
		
		Searchbutton = (Button)findViewById(R.id.buttonb);
		Searchbutton.setText("�����û�");
		Searchbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotopage(SearchActivity.class);
			}
		});
		Updateuserbutton = (Button)findViewById(R.id.buttonc);
		Updateuserbutton.setText("�����û�");
		Updateuserbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotopage(Updatepeople.class);
			}
		});
		Deleteuserbutton = (Button)findViewById(R.id.buttond);
		Deleteuserbutton.setText("ɾ���û�");
		Deleteuserbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotopage(deletepeople.class);
			}
		});
		Insertgoodsbutton = (Button)findViewById(R.id.buttone);
		Insertgoodsbutton.setText("�����Ʒ");
		Insertgoodsbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotopage(InsertgoodsActivity.class);
			}
		});
		Deleteallgoodsbutton= (Button)findViewById(R.id.buttonh);
		Deleteallgoodsbutton.setText("�����Ʒ");
		Deleteallgoodsbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ownerdatabase database = new ownerdatabase(getApplicationContext());
				database.DelectAllGoodsData();
				Toast.makeText(EditActivity.this, "�����Ʒ���ݳɹ���", 8000).show();
			}
		});
		Deletealldatabutton= (Button)findViewById(R.id.buttoni);
		Deletealldatabutton.setText("����������ݣ�");
		Deletealldatabutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ownerdatabase database = new ownerdatabase(getApplicationContext());
				database.DelectAllData();
				Toast.makeText(EditActivity.this, "����������ݳɹ���", 8000).show();
			}
		});
		Updategoodsbutton= (Button)findViewById(R.id.buttonf);
		Updategoodsbutton.setText("������Ʒ");
		Updategoodsbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotopage(Updategoods.class);
			}
		});
		Deletegoodsbutton = (Button)findViewById(R.id.buttong);
		Deletegoodsbutton.setText("ɾ����Ʒ");
		Deletegoodsbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotopage(deletegoods.class);
			}
		});
		returnbutton = (Button)findViewById(R.id.buttonj);
		returnbutton.setText("����");
		returnbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotopage(MainActivity.class);
			}
		});
		createbutton = (Button)findViewById(R.id.buttonk);
		createbutton.setText("���ɶ�ά��");
		createbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotopage(CreateActivity.class);
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
	 * @param gotopage
	 */
	private void gotopage(Class<?> goalpage){
		startActivity(new Intent(EditActivity.this, goalpage) );
		finish();
	 }


}
