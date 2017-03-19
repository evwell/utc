package com.tc.core.util;

import java.util.Date;

public class TestEnum {

	public static void main(String[] args) {
		WeekDay weekDay = WeekDay.SUN;
		System.out.println("weekDay = " + weekDay);
		System.out.println("weekDay.nextDay() = " + weekDay.nextDay());
		System.out.println("weekDay.nextDay().nextDay() = " + weekDay.nextDay().nextDay());
		System.out.println("weekDay.name() = " + weekDay.name());
		System.out.println("weekDay.ordinal() = " + weekDay.ordinal());
		System.out.println("WeekDay.valueOf(SUN).toString() = " + WeekDay.valueOf("SUN").toString());
		System.out.println("WeekDay.values().length = " + WeekDay.values().length);
		new Date(300) {
		};
	}

	public enum WeekDay {

		SUN(1){
			public WeekDay nextDay(){System.out.println("SUN.nextDay");return MON;}
		}, MON(2), TUE(3), WED(4), THI(5), FRI(6), SAT(7);
		private WeekDay(int day) {
		}
		
		public WeekDay nextDay(){
			System.out.println("WeekDay.nextDay");
			switch(this){
			case SUN : return MON;
			case MON : return TUE;
			case TUE : return WED;
			case WED : return THI;
			case THI : return FRI;
			case FRI : return SAT;
			case SAT : return SUN;
			default : return null;
			}
		}
	}

	public enum TrafficLamp {
		RED(30) {
			public TrafficLamp nextLamp() {
				return GREEN;
			}
		},
		GREEN(45) {
			public TrafficLamp nextLamp() {
				return YELLOW;
			}
		},
		YELLOW(5) {
			public TrafficLamp nextLamp() {
				return RED;
			}
		};
		public abstract TrafficLamp nextLamp();

		private int time;

		private TrafficLamp(int time) {
			this.time = time;
		}
	}

}
