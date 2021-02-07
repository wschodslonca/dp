package com.project.dp;

import com.project.dp.Tree.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class DpApplication implements CommandLineRunner {

	@Autowired
	Tree tree;

	public static void main(String[] args) {
		SpringApplication.run(DpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		App.start();
	}
}
