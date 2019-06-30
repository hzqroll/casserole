package com.roll.casserole.annotation.dbannotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haozq
 * Date: 2018/8/19 下午2:28
 */
public class TableCreator {
	public static String getConstraints(Constraints constraints) {
		String con = "";
		if (!constraints.allowNull()) {
			con += " NOT NULL ";
		}
		if (constraints.primaryKey()) {
			con += " PRIMARY KEY ";
		}
		if (constraints.unique()) {
			con += " UNIQUE ";
		}
		return con;
	}

	public static void main(String args[]) throws ClassNotFoundException {
		if (args.length < 1) {
			System.out.println("arguments: annotated classes");
			System.exit(0);
		}
		for (String className : args) {
			Class<?> cl = Class.forName(className);
			DBTable dbTable = cl.getAnnotation(DBTable.class);
			if (dbTable == null) {
				System.out.println("NO DBTable annotation in class " + className);
				continue;
			}
			String tableName = dbTable.name();
			// 如果名称为空，使用类名
			if (tableName.length() < 1) {
				tableName = cl.getName().toUpperCase();
			}
			List<String> columDefs = new ArrayList<>();
			for (Field field : cl.getDeclaredFields()) {
				String columnName = null;
				Annotation[] anns = field.getDeclaredAnnotations();
				if (anns.length < 1) {
					continue;
				}
				if (anns[0] instanceof SQLInteger) {
					SQLInteger sqlInteger = (SQLInteger) anns[0];
					if (sqlInteger.name().length() < 1) {
						columnName = field.getName().toUpperCase();
					} else {
						columnName = sqlInteger.name();
					}
					columDefs.add(className + "INT" + getConstraints(sqlInteger.constraints()));
				}
				if (anns[0] instanceof SQLString) {
					SQLString sqlString = (SQLString) anns[0];
					if (sqlString.name().length() < 1) {
						columnName = field.getName().toUpperCase();
					} else {
						columnName = sqlString.name();
					}
					columDefs.add(className + "VARCHAR" + getConstraints(sqlString.constraints()));
				}
				StringBuilder createCommanded = new StringBuilder("CREATE TABLE" + tableName + "(");
				for (String columnDef : columDefs) {
					createCommanded.append("\n   " + columnDef + ",");
				}
				String tableCreate = createCommanded.substring(0, createCommanded.length() - 1) + ")";
				System.out.println("Table Crration QQL for " + className + "is : \n" + tableCreate);
			}
		}
	}
}
