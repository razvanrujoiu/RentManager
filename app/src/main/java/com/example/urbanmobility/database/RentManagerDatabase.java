package com.example.urbanmobility.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.urbanmobility.database.dao.AddressDao;
import com.example.urbanmobility.database.dao.ResidenceDao;
import com.example.urbanmobility.database.dao.UserDao;
import com.example.urbanmobility.models.Address;
import com.example.urbanmobility.models.Residence;
import com.example.urbanmobility.models.User;

@Database(entities = {Address.class, Residence.class, User.class}, version = 1, exportSchema = false)
public abstract class RentManagerDatabase extends RoomDatabase {

    public abstract AddressDao addressDao();

    public abstract ResidenceDao residenceDao();

    public abstract UserDao userDao();
}
