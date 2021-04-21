package com.BestBot.Core;

import com.BestBot.Core.Entity.ItemEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

		ItemEntity item1 = new ItemEntity(1L, "test name");
		ItemEntity item2 = new ItemEntity(2L, "test name");
		ItemEntity item3 = new ItemEntity(3L, "test name");

		System.out.println(item1);
		System.out.println(item2);
		System.out.println(item3);
	}
}
