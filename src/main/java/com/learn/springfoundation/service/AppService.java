package com.learn.springfoundation.service;

import com.learn.springfoundation.repository.TstEntity;
import com.learn.springfoundation.repository.AppRepo;
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

    public List<TstEntity> getEntities() {
        List<TstEntity> appEntities = new ArrayList<>();
        appRepo.findAll().forEach(appEntities::add);
        return appEntities;
    }

    public void populateDB(int n) {

        List<TstEntity> entities = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            entities.add(new TstEntity("Value " + i));
        }
        appRepo.saveAll(entities);

    }
}
