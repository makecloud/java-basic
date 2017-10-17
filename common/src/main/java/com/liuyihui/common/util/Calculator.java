package com.liuyihui.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 计算器
 * 
 * @author liuyh 2016年9月12日
 */
public class Calculator {
	public static void main(String[] args) throws IOException {

		// 单价数组
		List<Double> pays = new ArrayList<Double>();
		// 总价
		double sum = 0L;
		// 实际付款
		Long realCost = 41L;
		while (true) {
			Scanner scanner = new Scanner(System.in);
			int i = scanner.nextInt();// 阻塞，等待从控制台输入int型数据
			if (i != 0) {
				pays.add((double) i);
				continue;
			}
			for (double pay : pays) {
				sum += pay;
			}
			System.out.println("sum>" + sum);
			for (double pay : pays) {
				double result = pay / sum * realCost;
				System.out.println(">" + result);
			}
			scanner.close();
			break;
		}
	}
}
