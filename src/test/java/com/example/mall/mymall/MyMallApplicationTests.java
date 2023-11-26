package com.example.mall.mymall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

@SpringBootTest
class MyMallApplicationTests {

	@Test
	void contextLoads() {
	}

	// @Autowired
	// private DataSource defaultDataSource;

	// @Test
	// public void datasourceTest() throws SQLException {
	// 	Connection connection = defaultDataSource.getConnection();
	// 	System.out.println("Get Connection:");
	// 	System.out.println(connection != null);
	// }

}
