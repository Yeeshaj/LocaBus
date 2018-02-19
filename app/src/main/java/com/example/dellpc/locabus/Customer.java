package com.example.dellpc.locabus;

/**
 * Created by DELL PC on 10-02-2018.
 */

public class Customer
{
    Customer()
    {

    }
    public Customer(String customerID, String name, String email, String number) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.number = number;
    }


    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    String customerID,name,email,number;


}
