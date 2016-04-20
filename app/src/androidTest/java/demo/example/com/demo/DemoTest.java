package demo.example.com.demo;

/**
 * Created by user on 16-4-19.
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.support.test.InstrumentationRegistry;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class DemoTest {
    private UiDevice mDevice;
    private static final String MM_PACKAGE = "com.tencent.mm";
    private static final int LAUNCH_TIMEOUT = 5000;
    @Before
    public void setUp(){
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();
        try {
            mDevice.wakeUp();
            mDevice.freezeRotation();
            //origin display rotation
            assertEquals(0, mDevice.getDisplayRotation());//landscape 1, vertical 0
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddMe() throws UiObjectNotFoundException {

        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(MM_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        //start mm app by intent and wait for the mm to appear
        context.startActivity(intent);
        mDevice.wait(Until.hasObject(By.pkg(MM_PACKAGE).depth(0)), LAUNCH_TIMEOUT);

        // click me button and wait for new window appear
        UiObject icBtn = mDevice.findObject(new UiSelector().text("\u6211"));
        icBtn.clickAndWaitForNewWindow(3000);

        // click c2c button and wait for new window appear
        UiObject c2cBtn = mDevice.findObject(new UiSelector().resourceId("com.tencent.mm:id/c2c"));
        c2cBtn.clickAndWaitForNewWindow(3000);
        UiObject aizObj = mDevice.findObject(new UiSelector().resourceId("com.tencent.mm:id/aiz"));
        assertTrue(aizObj.exists());
        UiObject aizValue = mDevice.findObject(new UiSelector().resourceId("com.tencent.mm:id/aiz"));
        assertEquals("\"扫一扫上面的二维码图案，加我微信\" not found!" , "扫一扫上面的二维码图案，加我微信", aizValue.getText());

    }

    @After
    public void tearDown(){
        try {
            mDevice.unfreezeRotation();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mDevice.pressHome();

    }
}
