package com.learn.epam.springfoundation.service;

import com.learn.epam.springfoundation.entity.AppEntity;
import com.learn.epam.springfoundation.repository.AppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppService {

    private DataSource dataSource;
    private AppRepo appRepo;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setAppRepo(AppRepo appRepo) {
        this.appRepo = appRepo;
    }

    public String getDataSourceInfo() {
        try (Connection con = dataSource.getConnection()) {
            var dbData = con.getMetaData();
            return dbData.getDatabaseProductName() + " URL: " + dbData.getURL() + " User: " + dbData.getUserName();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<AppEntity> getEntities() {
        List<AppEntity> appEntities = new ArrayList<>();
        appRepo.findAll().forEach(appEntities::add);
        return appEntities;
    }

    public void populateDB(int n) {

        List<AppEntity> entities = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            entities.add(new AppEntity("Value " + i));
        }
        appRepo.saveAll(entities);

    }
}
