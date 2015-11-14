package com.shareablee.users;
import com.google.gson.Gson;


public class JsonConverter<T> {

	/**
	 * Convert an object to Json String
	 * @param obj
	 * @return
	 */
	public static<T> String getJsonString(T obj){
		Gson gson = new Gson();
		return (gson.toJson(obj));
	}


	/**
	 * Get a class object from a Json String
	 * @param jsonString
	 * @param objectClass
	 * @return 
	 */
	public static<T> T getObjectFromJson(final String jsonString, final Class<T> objectClass) {  
		Gson gson = new Gson();  
		return gson.fromJson(jsonString, objectClass);
	}


	/* Function to test the functions*/
	/*public static void test(String args[]){

		student a = new student("Madhuri",13,5);
		student b = new student("Ravi",15,7);
		student c = new student("Manvi",17,9);
		System.out.println(gson.toJson(d));;

		student e =
			    gson.fromJson("{'name':'Chinks','age':13,'level':5}",
			    student.class);
		System.out.println(d.toString());
		System.out.println(e.toString());

	}*/

	
}


/*
import java.util.ArrayList;
import java.util.HashMap;

class student {
	String name;
	int age;
	int level;
	ArrayList<Integer> listVal;
	HashMap<Integer,String> hm;
	
	public student(String a,int b, int c){
		name = a;
		age = b;
		level = c;
		listVal = new ArrayList<Integer>();
		listVal.add(100);
		listVal.add(200);
		listVal.add(300);
		hm = new HashMap<Integer,String>();
		hm.put(1,"M");
		hm.put(2, "G");
	
	}
	
	public student(String a,int b, int c,int size){
		name = a;
		age = b;
		level = c;
		listVal = new ArrayList<Integer>();
		listVal.add(100);
		listVal.add(200);
		listVal.add(300);
		//hm = new HashMap<Integer,String>();
	}
	
	public String toString(){
		return name; 
	}
}
*/

