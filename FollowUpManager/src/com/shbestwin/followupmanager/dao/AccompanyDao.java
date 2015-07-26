package com.shbestwin.followupmanager.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.shbestwin.followupmanager.model.Accompany;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

public class AccompanyDao extends AbstractDao<Accompany, String> {
	public static final String TABLENAME = "ACCOMPANY_INFO";

	public static class Properties {
		public final static Property AccompanyNO = new Property(0, String.class,"accompanyNO", true, "ACCOMPANYNO");
		public final static Property Idcard = new Property(1, String.class,"idcard", false, "IDCARD");
		public final static Property Name = new Property(2, String.class,"name", false, "NAME");
		public final static Property Gender = new Property(3, String.class,"gender", false, "GENDER");
		public final static Property Birthday = new Property(4, String.class,"birthday", false, "BIRTHDAY");
		public final static Property Ethnic = new Property(5, String.class,"ethnic", false, "ETHNIC");
		public final static Property Telephone = new Property(6, String.class,"telephone", false, "TELEPHONE");
		public final static Property Address = new Property(7, String.class,"address", false, "ADDRESS");
		public final static Property Current_time = new Property(8,String.class, "current_time", false, "CRURRENT_TIME");
		public final static Property Next_time = new Property(9, String.class,"next_time", false, "NEXT_TIME");
		public final static Property Accompany_item = new Property(10,String.class, "accompany_item", false, "ACCOMPANY_ITEM");
		public final static Property CreateTime = new Property(11,String.class, "createTime", false, "CREATE_TIME");
		public final static Property UpdateTime = new Property(12,String.class, "updateTime", false, "UPDATE_TIME");

	};

	public AccompanyDao(DaoConfig config) {
		super(config);
	}

	public AccompanyDao(DaoConfig config, DaoSession daoSession) {
		super(config, daoSession);
	}

	public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
		String constraint = ifNotExists ? "IF NOT EXISTS " : "";
		db.execSQL("CREATE TABLE " + constraint + "'ACCOMPANY_INFO' ("
				+ "'ACCOMPANYNO' TEXT PRIMARY KEY NOT NULL ," 
				+ "'IDCARD' TEXT NOT NULL ,"
				+ "'NAME' TEXT NOT NULL ,"
				+ "'GENDER' TEXT," 
				+ "'BIRTHDAY' TEXT," 
				+ "'ETHNIC' TEXT,"
				+ "'TELEPHONE' TEXT,"
				+ "'ADDRESS' TEXT  ,"
				+ "'CRURRENT_TIME' TEXT," 
				+ "'NEXT_TIME' TEXT ,"
				+ "'ACCOMPANY_ITEM' TEXT  ," 
				+ "'CREATE_TIME' TEXT,"
				+ "'UPDATE_TIME' TEXT);");
	}
	
	public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'ACCOMPANY_INFO'";
        db.execSQL(sql);
    }
	
	 @Override
	    protected void bindValues(SQLiteStatement stmt, Accompany entity) {
	        stmt.clearBindings();
	        stmt.bindString(1, entity.getAccompanyNo());
	        stmt.bindString(2, entity.getIdcard());
	        stmt.bindString(3, entity.getName());
	 
	        String gender = entity.getGender();
	        if (gender != null) {
	            stmt.bindString(4, gender);
	        }
	 
	        String birthday = entity.getBirthday();
	        if (birthday != null) {
	            stmt.bindString(5, birthday);
	        }
	 
	        String ethnic = entity.getEthnic();
	        if (ethnic != null) {
	            stmt.bindString(6, ethnic);
	        }
	 
	        String telephone = entity.getTelephone();
	        if (telephone != null) {
	            stmt.bindString(7, telephone);
	        }
	 
	        String address = entity.getAddress();
	        if (address != null) {
	            stmt.bindString(8, address);
	        }
	 
	        String current_time = entity.getCurrent_time();
	        if (current_time != null) {
	            stmt.bindString(9, current_time);
	        }
	 
	        String next_time = entity.getNext_time();
	        if (next_time != null) {
	            stmt.bindString(10, next_time);
	        }
	 
	        String accompany_item = entity.getAccompany_item();
	        if (accompany_item != null) {
	            stmt.bindString(11, accompany_item);
	        }
	 
	        String createTime = entity.getCreate_time();
	        if (createTime != null) {
	            stmt.bindString(12, createTime);
	        }
	 
	        String updateTime = entity.getUpdate_time();
	        if (updateTime != null) {
	            stmt.bindString(13, updateTime);
	        }
	    }

	 @Override
	    public String readKey(Cursor cursor, int offset) {
	        return cursor.getString(offset + 0);
	    }  
	 
	 @Override
     public Accompany readEntity(Cursor cursor, int offset) {
		 Accompany entity = new Accompany( 
		 cursor.getString(offset + 0), 
	     cursor.getString(offset + 1), 
         cursor.getString(offset + 2), 
         cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), 
         cursor.getString(offset + 4), 
         cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), 
         cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6),
         cursor.getString(offset + 7),
         cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), 
         cursor.getString(offset + 9), 
         cursor.getString(offset + 10), 
         cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), 
         cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12) 
         );
        return entity;
     }
	 
	  @Override
      public void readEntity(Cursor cursor, Accompany entity, int offset) {
		  entity.setIdcard(cursor.getString(offset + 0));
		  entity.setIdcard(cursor.getString(offset + 1));
		  entity.setName(cursor.getString(offset + 2));
		  entity.setGender(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
		  entity.setBirthday(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
		  entity.setEthnic(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
		  entity.setTelephone(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
		  entity.setAddress(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
		  entity.setCurrent_time(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
		  entity.setNext_time(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
		  entity.setAccompany_item(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
		  entity.setCreate_time(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
		  entity.setUpdate_time(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
      }
	  
	  /** @inheritdoc */
	    @Override
	    protected String updateKeyAfterInsert(Accompany entity, long rowId) {
	        return entity.getIdcard();
	    }
	    
	    /** @inheritdoc */
	    @Override
	    public String getKey(Accompany entity) {
	        if(entity != null) {
	            return entity.getIdcard();
	        } else {
	            return null;
	        }
	    }

	    /** @inheritdoc */
	    @Override    
	    protected boolean isEntityUpdateable() {
	        return true;
	    }
}
