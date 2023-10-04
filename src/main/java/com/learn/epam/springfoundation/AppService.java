package com.learn.epam.springfoundation;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppService {

    private final DataSource dataSource;
    private final AppRepo appRepo;

    public AppService(DataSource dataSource, AppRepo appRepo) {
        this.dataSource = dataSource;
        this.appRepo = appRepo;
    }


    public String getDataSourceInfo() {
        try (Connection con = dataSource.getConnection()) {
            var dbData = con.getMetaData();
            return "URL: " + dbData.getURL() + " User: " + dbData.getUserName();
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
