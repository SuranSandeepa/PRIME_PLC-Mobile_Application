package com.example.ead_procument;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.ead_procument.services.Calculation;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.ead_procument", appContext.getPackageName());
    }

    @Test
    public void testTotalAmountPositive(){
        int testQty = 10;
        String unitPrice = "1000";

        Log.d("test expected", String.valueOf(Float.parseFloat("10000")));
        Log.d("test atual", String.valueOf(Calculation.totalAmountCal(testQty,unitPrice)));

        assertEquals(Float.parseFloat("10000"),Calculation.totalAmountCal(testQty,unitPrice),0.01);


    }

    @Test
    public void testTotalAmountNegative(){
        int testQty = 10;
        String unitPrice = "1000";

        assertNotEquals(Float.parseFloat("1000"),Calculation.totalAmountCal(testQty,unitPrice),0.01);

    }

    @Test
    public void testDelQtyPositive(){
        String already ="9";
        int now = 1;

        assertEquals(10,Calculation.deliveredQrt(already,now));
    }

    @Test
    public void testDelQtyNegative(){
        String already ="9";
        int now = 1;

        assertNotEquals(100,Calculation.deliveredQrt(already,now));
    }

    @Test
    public void testRetQtyPositive(){
        String unit = "100";
        int del = 29;

        assertEquals(71, Calculation.retrievedQty(unit,del));
    }

    @Test
    public void testRetQtyNegative(){
        String unit = "100";
        int del = 29;

        assertNotEquals(72, Calculation.retrievedQty(unit,del));
    }
}