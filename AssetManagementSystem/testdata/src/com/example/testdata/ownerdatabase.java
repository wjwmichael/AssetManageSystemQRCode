package com.example.testdata;

import java.io.ByteArrayOutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

public class ownerdatabase extends SQLiteOpenHelper{

	/**
	 * ���ڱ�����Ϣ�����ݿ�
	 * @author ~\(�R���Q)/~������
	 *
	 */
	public static final String people_id="people_id";
	public static final String people_name="people_name";
	public static final String people_phonenumber="phone_number";
	public static final String people_photo="people_photo";
	public static final String goods_id="goods_id";
	public static final String goods_name="goods_name";
	public static final String owner_id="owner_id";

	
	private static final String DATABASE_NAME="ownerdatabase.db";
	private static final int DATABASE_VERSION=1;
	private static final String DATABASE_TABLE_PEOPLE="peopletable";
	private static final String DATABASE_TABLE_GOODS="goodstable";
	private Context context=null;
	
	private static final String DATABASE_CREATE_PEOPLE="create table "+
			DATABASE_TABLE_PEOPLE+"("+people_id+" integer primary key autoincrement, "+
			people_name+" text not null, "+
			people_phonenumber+" text not null); "/*+
			people_photo+" BLOB);"*/;
	private static final String DATABASE_CREATE_GOODS="create table "+
			DATABASE_TABLE_GOODS+"("+goods_id+" integer primary key autoincrement, "+
			goods_name+" text not null, "+
			owner_id+" integer not null"/*+", foreign key("+owner_id+") references "+DATABASE_TABLE_PEOPLE+"("+people_id+")"*/+");";
	/**
	 * ������
	 * @param context
	 */
	public ownerdatabase(Context context){
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		this.context=context;
	}
	/**
	 * ɾ���������ݿ�
	 * @param context
	 * @return
	 */
	 public boolean deleteDatabase(Context context) {
		 return context.deleteDatabase(DATABASE_NAME);
	 }
	 
	 /**
		 * ����
		 */
	public void onCreate(SQLiteDatabase db) {
		// TODO �Զ����ɵķ������
		db.execSQL(DATABASE_CREATE_PEOPLE);
		db.execSQL(DATABASE_CREATE_GOODS);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO �Զ����ɵķ������
		db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE_PEOPLE);
		db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE_GOODS);
		onCreate(db);
	}
	/**
	 * �������ݱ��в���������
	 * @param name ����
	 * @param phone number �绰
	 * @param photo ��Ƭ
	 */
	public void insert_New_people(String name,String number/*,String path*/){
		ContentValues values= new ContentValues();
		//Bitmap photo=BitmapFactory.decodeFile(path);
		//ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		values.put(people_name,name);
		values.put(people_phonenumber,number);
		//values.put(people_photo, baos.toByteArray());
		try{
			getWritableDatabase().insert(DATABASE_TABLE_PEOPLE, people_id, values);
		}catch(Exception e){}
	}
	/**
	 * �������ݱ��и�������
	 * @param name ����
	 * @param phone number �绰
	 * @param photo ��Ƭ
	 */
	public void update_people(int id,String name,String number/*,String path*/){
	try{	
		ContentValues values=new ContentValues();
		values.put(people_name,name);
		values.put(people_phonenumber, number);
		//Bitmap photo=BitmapFactory.decodeFile(path);
		//ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		//values.put(people_photo, baos.toByteArray());
		getWritableDatabase().update(DATABASE_TABLE_PEOPLE, values, people_id+"="+id, null);
		}catch(Exception e){
			
		}
	}


	/**
	 * ��Ʒ���ݱ��в���������
	 * @param name ����
	 * @param people_id ����id
	 */
	public void insert_New_goods(String name,int id){
		ContentValues values= new ContentValues();
		values.put(goods_name,name);
		values.put(owner_id,id);
		try{
			getWritableDatabase().insert(DATABASE_TABLE_GOODS,goods_id,values);
		}catch(Exception e){}
	}
	/**
	 * ��Ʒ���ݱ��и�������
	 * name ����
	 */
	public void update_goods(int id,String name){
		try{	
			ContentValues values=new ContentValues();
			values.put(goods_name,name);
			getWritableDatabase().update(DATABASE_TABLE_GOODS, values, goods_id+"="+id, null);
			}catch(Exception e){
				
			}
		}
	
	
	
	
	
	/**
	 * ����������Ϣ
	 * @param name ����
	 * @param phonenumber �绰
	 * @return
	 */
	public String fetchdatafrompeople (String name, String phonenumber){      
		Cursor mCursor=null;
		try{
				mCursor = getReadableDatabase().query(true, DATABASE_TABLE_PEOPLE, 
														new String[]{people_id,
														people_name,people_phonenumber/*,
														people_photo*/}, 
														people_name+"='"+name+"'"+" OR "+people_phonenumber+"='"+phonenumber+"'",
														null, null, null,null, null);
		}catch(Exception e){
			mCursor=null;
		}
		String result;
		result=result_of_people(mCursor);
		return result;
	}
	public String result_of_people(Cursor mCursor)
	{
		if(mCursor==null){
			return getNoresultString();
		}
		mCursor.moveToFirst();
		int size=mCursor.getCount();
		if(size<1){
			return getNoresultString();
		}
		StringBuilder infor=new StringBuilder("");
		do{
			int idindex=mCursor.getColumnIndex(people_id);
			int nameindex=mCursor.getColumnIndex(people_name);
			int phonenumberindex=mCursor.getColumnIndex(people_phonenumber);
			//int photoidex=mCursor.getColumnIndex(people_photo);
			String id=mCursor.getString(idindex);
			String name=mCursor.getString(nameindex);
			String phonenumber=mCursor.getString(phonenumberindex);
			//byte[] in=mCursor.getBlob(photoidex);
			//Bitmap bmpout=(BitmapFactory.decodeByteArray(in,0, in.length));
			infor.append("��id");
			infor.append(":");
			infor.append(id);
			infor.append("\r\n");
			infor.append("����");
			infor.append(":");
			infor.append(name);
			infor.append("\r\n");
			infor.append("�绰");
			infor.append(":");
			infor.append(phonenumber);
			infor.append("\r\n");
			//infor.append("��Ƭ:");
			//infor.append(":");
			//infor.append(bmpout);
			//infor.append("\r\n");
		}while(mCursor.moveToNext());
		return infor.toString();
		
	}
	
	private String getNoresultString(){
			return "�Ҳ�����Ҫ�ҵ�Ŷ!"+"\r\n";
		
	}
	/**
	 * ��ѯ������Ϣ
	 * @param id
	 * @return
	 */
	public String fetchdatafrompeople(int id){        
		Cursor mCursor = getReadableDatabase().query(true, DATABASE_TABLE_PEOPLE, 
														new String[]{people_id,
														people_name,people_phonenumber/*,
														people_photo*/}, 
														people_id+"="+id,
														null, null, null,null, null);
		if(mCursor!=null) mCursor.moveToFirst();
		String result;
		result=result_of_people(mCursor);
		return result;
	}
	/**
	 * ͨ������id�������Լ���Ʒ��Ϣ
	 */
	public String fetchdatafrompeople1(int id){        
		Cursor mCursor = getReadableDatabase().query(true, DATABASE_TABLE_PEOPLE, 
														new String[]{people_id,
														people_name,people_phonenumber/*,
														people_photo*/}, 
														people_id+"="+id,
														null, null, null,null, null);
		if(mCursor!=null) mCursor.moveToFirst();
		String result;
		result=result_of_people(mCursor)+fetchdatafromgoodsbyownerid(id);
		return result;
	}
	/**
	 * ͨ����Ʒid����Ʒ�Լ�������Ϣ
	 */
	public String fetchdatafromgoods(int id){        
		Cursor mCursor = getReadableDatabase().query(true, DATABASE_TABLE_GOODS, 
														new String[]{goods_id,
														goods_name,owner_id}, 
														goods_id+"="+id+"",
														null, null, null,null, null);
		if(mCursor!=null) mCursor.moveToFirst();
		String result;
		result=result_of_goods(mCursor);
		return result;
	}
	/**
	 * ͨ������id����Ʒ��������Ʒ�Լ�������Ϣ
	 */
	public String fetchdatafromgoods(String name,int id){        
		Cursor mCursor = getReadableDatabase().query(true, DATABASE_TABLE_GOODS, 
														new String[]{goods_id,
														goods_name,owner_id}, 
														owner_id+"="+id+" AND "+goods_name+"='"+name+"'",
														null, null, null,null, null);
		if(mCursor!=null) mCursor.moveToFirst();
		String result;
		result=result_of_goods(mCursor);
		return result;
	}
	/**
	 * ͨ������id������Ʒ
	 */
	public String fetchdatafromgoodsbyownerid(int id){        
		Cursor mCursor = getReadableDatabase().query(true, DATABASE_TABLE_GOODS, 
														new String[]{goods_id,
														goods_name,owner_id}, 
														owner_id+"="+id+"",
														null, null, null,null, null);
		if(mCursor!=null) mCursor.moveToFirst();
		if(mCursor==null){
			return "����û�������Ʒ";
		}
		mCursor.moveToFirst();
		int size=mCursor.getCount();
		if(size<1)return "����û�������Ʒ";
		StringBuilder infor=new StringBuilder("");
		do{
			int idindex=mCursor.getColumnIndex(goods_id);
			int nameindex=mCursor.getColumnIndex(goods_name);
			//int ownerindex=mCursor.getColumnIndex(owner_id);
			String goodsid=mCursor.getString(idindex);
			String name=mCursor.getString(nameindex);
			//int ownerid=mCursor.getInt(ownerindex);
			infor.append("��id");
			infor.append(":");
			infor.append(goodsid);
			infor.append("\r\n");
			infor.append("���֣�");
			infor.append(name);
			infor.append("\r\n");
		}while(mCursor.moveToNext());
		return infor.toString();
		
	}
	public String result_of_goods(Cursor mCursor)
	{
		if(mCursor==null){
			return getNoresultString();
		}
		mCursor.moveToFirst();
		int size=mCursor.getCount();
		if(size<1)return getNoresultString();
		StringBuilder infor=new StringBuilder("");
		do{
			int idindex=mCursor.getColumnIndex(goods_id);
			int nameindex=mCursor.getColumnIndex(goods_name);
			int ownerindex=mCursor.getColumnIndex(owner_id);
			String id=mCursor.getString(idindex);
			String name=mCursor.getString(nameindex);
			int ownerid=mCursor.getInt(ownerindex);
			infor.append("��id");
			infor.append(":");
			infor.append(id);
			infor.append("\r\n");
			infor.append("���֣�");
			infor.append(name);
			infor.append("\r\n");
			//infor.append("����id");
			//infor.append(":");
			//infor.append(ownerid);
			infor.append(fetchdatafrompeople(ownerid));
		}while(mCursor.moveToNext());
		return infor.toString();
		
	}
	
	/**
	 * ɾ��������
	 */
	public void DelectAllData(){
		getWritableDatabase().delete(DATABASE_TABLE_GOODS, null, null);
		getWritableDatabase().delete(DATABASE_TABLE_PEOPLE, null, null);
	}
	/**
	 * ɾ����Ʒ��
	 */
	public void DelectAllGoodsData(){
		getWritableDatabase().delete(DATABASE_TABLE_GOODS, null, null);
	}
	/**
	 * ɾ��ָ��id�û�����
	 */
	public void DeletepeopleData(int id){
		getWritableDatabase().delete(DATABASE_TABLE_GOODS, owner_id, null);
		getWritableDatabase().delete(DATABASE_TABLE_PEOPLE, people_id+"="+id, null);
	}
	/**
	 * ɾ��ָ��id��Ʒ����
	 */
	public void DeletegoodsData(int id){
		getWritableDatabase().delete(DATABASE_TABLE_GOODS, goods_id+"="+id, null);
	}
	
	
	
	
}