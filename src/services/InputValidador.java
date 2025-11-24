package services;

import java.util.Scanner;

public final class InputValidador {
	public static int readInt(Scanner sc) {
		while (true) {
			try {
				return Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid number! Please type a valid number.");
			}
		}
	}

	public static Double readDouble(Scanner sc) {
		while (true) {
			try {
				return Double.parseDouble(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid value! Please type a valid number.");
			}
		}
	}
}
