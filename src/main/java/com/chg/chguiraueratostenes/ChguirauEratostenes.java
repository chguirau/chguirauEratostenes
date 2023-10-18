package com.chg.chguiraueratostenes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import java.util.ArrayList; // import the ArrayList class
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.ListIterator;
/**
 *
 * @author carlosherrero
 */
public class ChguirauEratostenes {

    public static void main(String[] args) {
        System.out.println("Criba Eratóstenes");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("Start ... "+timestamp);
        //System.out.println("List size: "+104729);
        System.out.println("List size: "+1299827);
        //int max=104729; //the 10000 first prime numbers, to test
        int max=1299827; //the 100000 first prime numbers, to test
        //System.out.println("List size: "+Integer.MAX_VALUE/8);
        //int max=Integer.MAX_VALUE/8;
        ArrayList<IntElement> criba = new ArrayList<IntElement>();
        criba.add(new IntElement(1));
        criba.add(new IntElement(2));
        criba.add(new IntElement(3));
        boolean even=true;
        //init list without even numbers
        for (int i=4; i<=max; i++)
        {
            if (!even) criba.add(new IntElement(i));
            even=!even;
        }
        ListIterator<IntElement> i1=criba.listIterator();
        ListIterator<IntElement> i2;
        ListIterator<IntElement> i3;
        i1.next();
        i1.next(); //start with 2
        int toRemoveMultiples;
        int toRemoveNumber;
        IntElement e;
        IntElement toRemoveMultiplesEl;
        boolean found;
        boolean limitFound=false;
        boolean done=false;
        while (i1.hasNext() && !done)
        {
            toRemoveMultiplesEl=i1.next();
            if (!toRemoveMultiplesEl.softDeleted)
            {
                //remove multiples for toRemove
                toRemoveMultiples=toRemoveMultiplesEl.value; //starts with 3
                if (toRemoveMultiples*toRemoveMultiples>max) done=true;
                timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("ToRemoveMultiples "+toRemoveMultiples + " "+timestamp);
                i2=criba.listIterator(i1.previousIndex());
                if (!done) limitFound=false;
                while (i2.hasNext() && !limitFound)
                {
                    toRemoveNumber=toRemoveMultiples*i2.next().value;
                    //System.out.println("ToRemoveNumber "+toRemoveNumber);
                    if (toRemoveNumber>max) limitFound=true; //we do not need to remove elements if we reached the end of the list!
                    i3=criba.listIterator(i2.previousIndex());
                    found=false;
                    while (i3.hasNext() && !found)
                    {   
                        e=i3.next();
                        if (!e.softDeleted && e.value==toRemoveNumber) // first condition to fast process, as && is evaluated first
                        //if (e.value==toRemoveNumber) // first condition not necessary as we only have softDeleted in previous entries?
                        {
                            e.softDeleted=true;
                            found=true;
                        }
                    }
                }    
            }
        }
        
        //show list results, and number of elements
        Iterator<IntElement> i4=criba.iterator();
        IntElement ie;
        int count=0;
        while (i4.hasNext())
        {
            ie=i4.next();
            if (!ie.softDeleted)
            {
                count++;
                System.out.println(ie.value + " ;");
            }
        }
        
        System.out.println("Count "+ count);
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("EndInitList ... "+timestamp);
    }
}
