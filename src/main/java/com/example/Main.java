/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class is in charge of managing the files in heroku.
 * @author Miguel Angel Rodríguez Siachoque
 * @author Heroku
 */
@Controller
@SpringBootApplication
public class Main {
  @Value("${spring.datasource.url}")
  private String dbUrl;
  @Autowired
  private DataSource dataSource;
  /**
   * This main class is in charge of managing the files in heroku.
   * @param args User address entries.
   * @throws Exception Exception user input not found.
   */
  public static void main(String[] args) throws Exception 
  {
    SpringApplication.run(Main.class, args);
  }
  /**
   * This method locates the index website name.
   * @return Website name.
   */
  @RequestMapping("/")
  String index() 
  {
    return "index";
  }
  /**
   * This method locates the picas & famas website name.
   * @return Website name.
   */
  @RequestMapping("/pointsfames")
  String pointsfames() 
  {
    return "pointsfames";
  }
  /**
   * This method is in charge of listing the user inputs.
   * @param model User input data.
   * @return User data entered in the database.
   */
  @RequestMapping("/db")
  String db(Map<String, Object> model) 
  {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) {
        output.add("Read from DB: " + rs.getTimestamp("tick"));
      }
      model.put("records", output);
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }
  /**
   * This method locates the data from the database.
   * @return Database configuration.
   * @throws SQLException Database configuration failure exception.
   */
  @Bean
  public DataSource dataSource() throws SQLException 
  {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }
}