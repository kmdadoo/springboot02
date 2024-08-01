package com.study.springboot.bean;

public class PrinterA implements Printer
{
	@Override  // 오타를 예방
	public void print(String message)
	{
		System.out.println("Printer A : " + message);
	}
}
