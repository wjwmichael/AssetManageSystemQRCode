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
	 * 用于保存信息的数据库
	 * @author ~\(≧▽≦)/~啦啦啦
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
	 * 构造器
	 * @param context
	 */
	public ownerdatabase(Context context){
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		this.context=context;
	}
	/**
	 * 删除整个数据库
	 * @param context
	 * @return
	 */
	 public boolean deleteDatabase(Context context) {
		 return context.deleteDatabase(DATABASE_NAME);
	 }
	 
	 /**
		 * 创建
		 */
	public void onCreate(SQLiteDatabase db) {
		// TODO 自动生成的方法存根
		db.execSQL(DATABASE_CREATE_PEOPLE);
		db.execSQL(DATABASE_CREATE_GOODS);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根
		db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE_PEOPLE);
		db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE_GOODS);
		onCreate(db);
	}
	/**
	 * 物主数据表中插入新数据
	 * @param name 名字
	 * @param phone number 电话
	 * @param photo 照片
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
	 * 物主数据表中更新数据
	 * @param name 名字
	 * @param phone number 电话
	 * @param photo 照片
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
	 * 物品数据表中插入新数据
	 * @param name 名字
	 * @param people_id 物主id
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
	 * 物品数据表中更新数据
	 * name 名字
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
	 * 查找物主信息
	 * @param name 名字
	 * @param phonenumber 电话
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
			infor.append("人id");
			infor.append(":");
			infor.append(id);
			infor.append("\r\n");
			infor.append("姓名");
			infor.append(":");
			infor.append(name);
			infor.append("\r\n");
			infor.append("电话");
			infor.append(":");
			infor.append(phonenumber);
			infor.append("\r\n");
			//infor.append("照片:");
			//infor.append(":");
			//infor.append(bmpout);
			//infor.append("\r\n");
		}while(mCursor.moveToNext());
		return infor.toString();
		
	}
	
	private String getNoresultString(){
			return "找不到你要找的哦!"+"\r\n";
		
	}
	/**
	 * 查询物主信息
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
	 * 通过物主id找物主以及物品信息
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
	 * 通过物品id找物品以及物主信息
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
	 * 通过物主id和物品名字找物品以及物主信息
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
	 * 通过物主id查找物品
	 */
	public String fetchdatafromgoodsbyownerid(int id){        
		Cursor mCursor = getReadableDatabase().query(true, DATABASE_TABLE_GOODS, 
														new String[]{goods_id,
														goods_name,owner_id}, 
														owner_id+"="+id+"",
														null, null, null,null, null);
		if(mCursor!=null) mCursor.moveToFirst();
		if(mCursor==null){
			return "此人没有添加物品";
		}
		mCursor.moveToFirst();
		int size=mCursor.getCount();
		if(size<1)return "此人没有添加物品";
		StringBuilder infor=new StringBuilder("");
		do{
			int idindex=mCursor.getColumnIndex(goods_id);
			int nameindex=mCursor.getColumnIndex(goods_name);
			//int ownerindex=mCursor.getColumnIndex(owner_id);
			String goodsid=mCursor.getString(idindex);
			String name=mCursor.getString(nameindex);
			//int ownerid=mCursor.getInt(ownerindex);
			infor.append("物id");
			infor.append(":");
			infor.append(goodsid);
			infor.append("\r\n");
			infor.append("名字：");
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
			infor.append("物id");
			infor.append(":");
			infor.append(id);
			infor.append("\r\n");
			infor.append("名字：");
			infor.append(name);
			infor.append("\r\n");
			//infor.append("物主id");
			//infor.append(":");
			//infor.append(ownerid);
			infor.append(fetchdatafrompeople(ownerid));
		}while(mCursor.moveToNext());
		return infor.toString();
		
	}
	
	/**
	 * 删除整个表
	 */
	public void DelectAllData(){
		getWritableDatabase().delete(DATABASE_TABLE_GOODS, null, null);
		getWritableDatabase().delete(DATABASE_TABLE_PEOPLE, null, null);
	}
	/**
	 * 删除物品表
	 */
	public void DelectAllGoodsData(){
		getWritableDatabase().delete(DATABASE_TABLE_GOODS, null, null);
	}
	/**
	 * 删除指定id用户数据
	 */
	public void DeletepeopleData(int id){
		getWritableDatabase().delete(DATABASE_TABLE_GOODS, owner_id, null);
		getWritableDatabase().delete(DATABASE_TABLE_PEOPLE, people_id+"="+id, null);
	}
	/**
	 * 删除指定id物品数据
	 */
	public void DeletegoodsData(int id){
		getWritableDatabase().delete(DATABASE_TABLE_GOODS, goods_id+"="+id, null);
	}
	
	
	
	
}