package th.ac.pim.picshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splashscreen extends Activity {
	protected boolean _active = true;
    protected int _splashTime = 3000;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);
        
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(100);
                        if(_active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
    				Intent goMain = new Intent(getApplicationContext(), MainActivity.class);
    				startActivity(goMain);
                }
            }
        };
        splashTread.start();
    }
	

}
