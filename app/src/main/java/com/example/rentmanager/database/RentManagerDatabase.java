package com.example.rentmanager.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rentmanager.database.dao.AddressDao;
import com.example.rentmanager.database.dao.ResidenceDao;
import com.example.rentmanager.database.dao.UserDao;
import com.example.rentmanager.models.Address;
import com.example.rentmanager.models.Residence;
import com.example.rentmanager.models.User;

@Database(entities = {Address.class, Residence.class, User.class}, version = 1, exportSchema = false)
public abstract class RentManagerDatabase extends RoomDatabase {

    public abstract AddressDao addressDao();

    public abstract ResidenceDao residenceDao();

    public abstract UserDao userDao();
}
